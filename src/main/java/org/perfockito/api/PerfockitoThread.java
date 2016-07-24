package org.perfockito.api;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import java.lang.reflect.Method;

public interface PerfockitoThread extends Runnable {

    void init(@NotNull Method methodForTest, @Nullable Object objectOnTest, @Nullable Object... args);

    long getTime();
}