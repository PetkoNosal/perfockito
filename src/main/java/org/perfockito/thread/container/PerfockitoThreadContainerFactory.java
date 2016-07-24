package org.perfockito.thread.container;

import com.sun.istack.internal.NotNull;
import org.perfockito.api.PerfockitoThreadContainer;

public final class PerfockitoThreadContainerFactory {

    @NotNull
    public static PerfockitoThreadContainer createThreadContainer() {
        return new PerfockitoCPUThreadContainer();
    }
}
