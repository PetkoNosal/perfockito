package org.perfockito.threadpool;

import com.sun.istack.internal.NotNull;
import org.perfockito.api.PerfockitoThreadPool;

public final class PerfockitoThreadPoolFactory {

    @NotNull
    public static PerfockitoThreadPool create(int threadCount) {
        return new MultiThreadPool(threadCount);
    }
}