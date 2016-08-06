package org.perfockito.nota.processor;

public class PerockitoProcessor {

    private final static PerockitoProcessor INSTANCE = new PerockitoProcessor();

    public static PerockitoProcessor getProcessor() {
        return INSTANCE;
    }

    private PerockitoProcessor() {
        
    }
}
