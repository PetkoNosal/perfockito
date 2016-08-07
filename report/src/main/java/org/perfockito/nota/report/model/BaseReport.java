package org.perfockito.nota.report.model;

import java.util.HashSet;
import org.perfockito.nota.report.API.PerfockitoReport;

public class BaseReport implements PerfockitoReport {
    private HashSet<Group> groups;

    public BaseReport() {
        this.groups = new HashSet<>();
    }

    public HashSet<Group> getGroups() {
        return groups;
    }

    public void setGroups(HashSet<Group> groups) {
        this.groups = groups;
    }

    public boolean containsGroup(Group group) {
        for(Group gr: this.groups) {
            if(gr.getName().equals(group.getName())) return true;
        }
        return false;
    }

    public Group getGroup(String name) {
        for(Group gr: this.groups) {
            if(gr.getName().equals(name)) return gr;
        }
        return null;
    }

    @Override
    public void print() {
        StringBuilder builder = new StringBuilder();
        builder.append("***********************************************").append(System.lineSeparator());
        builder.append("*****          PERFOCKITO REPORT          *****").append(System.lineSeparator());
        builder.append("***********************************************").append(System.lineSeparator());

        for(Group group : groups){
            builder.append("*** GROUP: ").append(group.getName()).append(System.lineSeparator());
            for(SimpleReport report : group.getSimpleReports()){
                builder.append("*** MARKER: ").append(report.getName()).append(System.lineSeparator())
                        .append("**** AVG: ").append(report.getAverageTime()).append(", ")
                        .append("MIN: ").append(report.getMinTime()).append(", ")
                        .append("MAX: ").append(report.getMaxTime())
                        .append(System.lineSeparator())
                        .append("**** REPS: ").append(report.getRepetitions()).append(System.lineSeparator());
            }
        }
        builder.append("***********************************************").append(System.lineSeparator());

        System.out.println(builder);
    }
}
