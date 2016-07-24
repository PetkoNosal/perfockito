package org.perfockito.report;

import org.perfockito.api.PerfockitoReport;

class SimpleReport implements PerfockitoReport {

    private final StringBuilder MAIN_BUILDER = new StringBuilder();
    private final StringBuilder HEADER_BUILDER = new StringBuilder();
    private final StringBuilder REFERENCE_BUILDER = new StringBuilder();
    private final StringBuilder METHODS_BUILDER = new StringBuilder();
    private final StringBuilder DETAIL_BUILDER = new StringBuilder();

    @Override
    public void setHeader(String nameOfTest, int repetitions, int cycles) {
        HEADER_BUILDER.append("TEST NAME: ").append(nameOfTest).append("\n");
        HEADER_BUILDER.append("TEST REPETITIONS: ").append(repetitions).append("\n");
        HEADER_BUILDER.append("NUMBER OF CYCLES IN ONE REPETITION: ").append(cycles).append("\n");
    }

    @Override
    public void addReferenceInfo(int numberOfRun, double avgTime) {
        REFERENCE_BUILDER.append("ONE SECOND REFERENCE INFO:").append("\n");
        REFERENCE_BUILDER.append("NUMBER OF RUN: ").append(numberOfRun).append("\n");
        REFERENCE_BUILDER.append("AVERAGE EXECUTION TIME: ").append(avgTime / 1000000000.0).append("\n");
    }

    @Override
    public void addMethodToReport(String methodName, double avgTime) {
        METHODS_BUILDER.append("METHOD NAME: ").append(methodName).append(", ");
        METHODS_BUILDER.append("AVERAGE EXECUTION TIME: ").append(avgTime / 1000000000.0).append("\n");
    }

    @Override
    public void appendToBody(String methodName, double avgTime) {
        DETAIL_BUILDER.append("METHOD NAME: ").append(methodName).append(", ");
        DETAIL_BUILDER.append("AVERAGE EXECUTION TIME: ").append(avgTime / 1000000000.0).append("\n");
    }

    @Override
    public void print() {
        MAIN_BUILDER.append("*********************************************").append("\n");
        MAIN_BUILDER.append(HEADER_BUILDER).append("\n");
        MAIN_BUILDER.append("*********************************************").append("\n");
        MAIN_BUILDER.append(REFERENCE_BUILDER).append("\n");
        MAIN_BUILDER.append("*********************************************").append("\n");
        MAIN_BUILDER.append(METHODS_BUILDER).append("\n");
        MAIN_BUILDER.append("*********************************************").append("\n");
        MAIN_BUILDER.append("*********************************************").append("\n");
        MAIN_BUILDER.append(DETAIL_BUILDER).append("\n");
        MAIN_BUILDER.append("*********************************************").append("\n");
        System.out.println(MAIN_BUILDER);
    }
}
