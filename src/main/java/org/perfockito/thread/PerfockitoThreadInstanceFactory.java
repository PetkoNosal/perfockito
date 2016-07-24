package org.perfockito.thread;

import com.sun.istack.internal.NotNull;
import org.perfockito.api.PerfockitoThread;

public final class PerfockitoThreadInstanceFactory {

    @NotNull
    public static PerfockitoThread createThread() {
        return new SystemTimeBasedThread();
//        return ManagementFactory.getThreadMXBean().isThreadCpuTimeSupported() ?
//                new CPUTimeBasedThread() : new SystemTimeBasedThread();
    }
}
