package org.perfockito.thread.container;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import org.perfockito.api.PerfockitoThread;
import org.perfockito.api.PerfockitoThreadContainer;
import org.perfockito.thread.PerfockitoThreadInstanceFactory;

import java.lang.Thread.State;
import java.lang.reflect.Method;

import static java.lang.management.ManagementFactory.getThreadMXBean;

final class PerfockitoCPUThreadContainer implements PerfockitoThreadContainer {
    private Thread owner;
    private final PerfockitoThread executor;

    PerfockitoCPUThreadContainer() {
        this.executor = PerfockitoThreadInstanceFactory.createThread();
        this.owner = new Thread(this.executor);
    }

    @Override
    public void initThread(@NotNull Method methodForTest, @Nullable Object objectOnTest, @Nullable Object... args) {
        this.executor.init(methodForTest, objectOnTest, args);
    }

    @Override
    public void startThread() {
        if(getThreadState() == State.TERMINATED) this.owner = new Thread(this.executor);
        this.owner.start();
    }

    @Override
    public State getThreadState() {
        return this.owner.getState();
    }

    @Override
    public long getUserTime() {
        return executor.getTime();
    }

    @Override
    public long getCPUTime() {
        return getThreadMXBean().getThreadCpuTime(this.owner.getId());
    }
}
