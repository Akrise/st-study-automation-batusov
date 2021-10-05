package at.study.redmine.model.role;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum IssuesVisibility {
    ALL("Все задачи"),
    OWN("Задачи созданные или назначенные пользователю"),
    DEFAULT("Только общие задачи");

    public final String description;
}
