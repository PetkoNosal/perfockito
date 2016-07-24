package org.perfockito.api;

import com.sun.istack.internal.NotNull;

public interface Perfockito {

    void init(int repetitions, int cycle);

    void addInstanceMethodToTest(@NotNull String methodName, Object objectOn, Object... methodArgs);

    void addStaticMethodToTest(@NotNull String methodName, Class clazz, Object... methodArgs);

    void runTest();

    PerfockitoReport getReport();
}
