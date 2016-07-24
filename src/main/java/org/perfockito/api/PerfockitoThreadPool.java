package org.perfockito.api;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import java.lang.reflect.Method;

public interface PerfockitoThreadPool {

    void initThreadPool(@NotNull Method methodForTest, @Nullable Object objectOnTest, @Nullable Object... args);

    void startThreads();

    boolean areAllThreadsTerminated();

    @NotNull
    double getAvgUserTime();

    @NotNull
    double getAvgCPUTime();
}
