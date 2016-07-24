package org.perfockito.thread;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import org.perfockito.api.PerfockitoThread;

import java.lang.reflect.Method;

abstract class AbstractThread implements PerfockitoThread{

    Method methodForTest;
    Object objectOn;
    Object[] args;

    long time;

    @Override
    public void init(@NotNull Method methodForTest, @Nullable Object objectOn, @Nullable Object... args) {
        this.methodForTest = methodForTest;
        this.methodForTest.setAccessible(true);
        this.objectOn = objectOn;
        this.args = args;
    }

    @Override
    public long getTime(){
        return time;
    }
}
