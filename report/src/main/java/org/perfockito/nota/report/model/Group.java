package org.perfockito.nota.report.model;

import java.util.HashSet;
import java.util.Set;

public class Group {

    private String name;
    private Set<SimpleReport> simpleReports;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<SimpleReport> getSimpleReports() {
        return simpleReports;
    }

    public void setSimpleReports(Set<SimpleReport> simpleReports) {
        this.simpleReports = simpleReports;
    }

    public Group() {
        this.simpleReports = new HashSet<>();
    }

    public static Group getDefaultGroup() {
        Group group = new Group();
        group.setName("DEFAULT GROUP");
        return group;
    }
}
