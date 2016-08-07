package org.perfockito.nota.processor;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.perfockito.nota.API.PerfockitoProcessor;

public class ProcessorsHolder {

    private static final Logger LOGGER = Logger.getLogger(ProcessorsHolder.class.getName());
    private final static Map<Class, PerfockitoProcessor> PROCESSORS = new HashMap<>();

    public static PerfockitoProcessor retrieveRuntimeProcessor() {
        if (!PROCESSORS.containsKey(RuntimeProcessor.class)) {
            PROCESSORS.put(RuntimeProcessor.class, new RuntimeProcessor());
        }
        return PROCESSORS.get(RuntimeProcessor.class);
    }

    public static PerfockitoProcessor copyOf(final PerfockitoProcessor processor) {
        try {
            return processor.getClass().newInstance();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "copyOf processor", e);
        }
        throw new UnknownError("Unknown error happened at processor copying stage");
    }

    private ProcessorsHolder() {
        throw new UnsupportedOperationException("This is holder for singleton instances, do not instantiate this");
    }
}
