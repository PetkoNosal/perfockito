package org.perfockito.report;

import org.perfockito.api.PerfockitoReport;

public class PerfockitoReportFactory {

    public static PerfockitoReport create() {
        return new SimpleReport();
    }
}
