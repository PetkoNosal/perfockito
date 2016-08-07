package org.perfockito.nota.API;

import org.perfockito.nota.report.API.PerfockitoReport;

public interface PerfockitoProcessor {

    PerfockitoProcessor registerInstance(Class clazz, Object instance);
    PerfockitoProcessor registerClasses(Class... clazzes);

    PerfockitoReport run();
}
