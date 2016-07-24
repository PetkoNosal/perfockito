package org.perfockito.engine;

import com.sun.istack.internal.Nullable;

public class PerfockitoEngineFactory {

    public static PerfockitoEngine create(@Nullable String name) {
        return new PerfockitoEngine(name);
    }
}
