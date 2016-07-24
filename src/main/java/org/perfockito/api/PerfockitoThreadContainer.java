package org.perfockito.api;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import java.lang.Thread.State;
import java.lang.reflect.Method;

public interface PerfockitoThreadContainer {

    void initThread(@NotNull Method methodForTest, @Nullable Object objectOnTest, @Nullable Object... args);

    void startThread();

    State getThreadState();

    long getUserTime();

    long getCPUTime();
}
