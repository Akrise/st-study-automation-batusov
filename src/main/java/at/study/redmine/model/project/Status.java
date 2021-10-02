package at.study.redmine.model.project;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Status {
    OPEN("1"),
    CLOSE("0");

    public final String description;
}
