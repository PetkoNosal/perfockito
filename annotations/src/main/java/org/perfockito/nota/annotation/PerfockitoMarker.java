package org.perfockito.nota.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.METHOD, ElementType.CONSTRUCTOR })
@Retention(RetentionPolicy.RUNTIME)
public @interface PerfockitoMarker {

    enum MarkerType {
        SIMPLE,
        THROW_EXCEPTION_ON_TIMEOUT
    }

    MarkerType type() default MarkerType.SIMPLE;

    String name() default "";
    String group() default "";

    long repetitions() default 1L;
    long timeout() default 0L;
}
