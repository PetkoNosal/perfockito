package org.perfockito.nota.API;

import org.perfockito.nota.processor.ProcessorsHolder;

public interface PerfockitoEngine {

    enum ProcessorType {
        RUNTIME_PROCESSOR
    }

    static PerfockitoProcessor getLocalProcessor(ProcessorType processorType) {
        return ProcessorsHolder.copyOf(getGlobalProcessor(processorType));
    }

    static PerfockitoProcessor getGlobalProcessor(ProcessorType processorType) {
        switch (processorType) {
            case RUNTIME_PROCESSOR: return ProcessorsHolder.retrieveRuntimeProcessor();
            default: throw new UnknownError("Unimplemented processor type");
        }
    }
}
