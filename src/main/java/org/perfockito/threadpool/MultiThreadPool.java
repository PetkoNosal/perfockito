package org.perfockito.threadpool;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import org.perfockito.api.PerfockitoThreadContainer;
import org.perfockito.api.PerfockitoThreadPool;
import org.perfockito.thread.container.PerfockitoThreadContainerFactory;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.lang.Thread.State.TERMINATED;

final class MultiThreadPool implements PerfockitoThreadPool {

    private final Set<PerfockitoThreadContainer> threadPool;

    MultiThreadPool(int cycles) {
        this.threadPool = new HashSet<>(cycles);
        for (int i = 0; i < cycles; i++) {
            this.threadPool.add(PerfockitoThreadContainerFactory.createThreadContainer());
        }
    }

    @Override
    public void initThreadPool(@NotNull Method methodForTest, @Nullable Object objectOnTest, @Nullable Object... args) {
        threadPool.forEach(container -> container.initThread(methodForTest, objectOnTest, args));
    }

    @Override
    public void startThreads() {
        threadPool.forEach(PerfockitoThreadContainer::startThread);
    }

    @Override
    public boolean areAllThreadsTerminated() {
        final boolean[] allTerminated = {true};
        threadPool.forEach(container -> {
            if (container.getThreadState() != TERMINATED) {
                allTerminated[0] = false;
            }
        });
        return allTerminated[0];
    }

    @Override
    public double getAvgUserTime() {
        final List<Long> userTimes = new ArrayList<>(threadPool.size());
        threadPool.forEach(container -> userTimes.add(container.getUserTime()));

        return computeAvgTime(userTimes);
    }

    @Override
    public double getAvgCPUTime() {
        final List<Long> cpuTimes = new ArrayList<>(threadPool.size());
        threadPool.forEach(container -> cpuTimes.add(container.getCPUTime()));

        return computeAvgTime(cpuTimes);
    }

    private static double computeAvgTime(List<Long> times) {
        long sumTime = 0;
        for(Long time: times){
            sumTime += time;
        }

        return ((double) sumTime / (double) times.size());
    }
}
