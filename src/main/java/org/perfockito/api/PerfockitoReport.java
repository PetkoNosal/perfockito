package org.perfockito.api;

public interface PerfockitoReport {

    void setHeader(String nameOfTest, int repetitions, int cycles);
    void addReferenceInfo(int numberOfRun, double avgTime);
    void addMethodToReport(String methodName, double avgTime);
    void appendToBody(String methodName, double avgTime);

    void print();

}
