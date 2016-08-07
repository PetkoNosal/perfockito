package org.perfockito.nota.report.model;

public class SimpleReport {
    private String name;
    private long repetitions;
    private long averageTime;
    private long minTime;
    private long maxTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getRepetitions() {
        return repetitions;
    }

    public void setRepetitions(long repetitions) {
        this.repetitions = repetitions;
    }

    public long getAverageTime() {
        return averageTime;
    }

    public void setAverageTime(long averageTime) {
        this.averageTime = averageTime;
    }

    public long getMinTime() {
        return minTime;
    }

    public void setMinTime(long minTime) {
        this.minTime = minTime;
    }

    public long getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(long maxTime) {
        this.maxTime = maxTime;
    }

    public SimpleReport() {
    }
}
