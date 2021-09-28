package at.study.redmine.model.role;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum UsersVisibility {
    ALL("Все активные пользователи"),
    MEMBERS_OF_VISIBLE_PROJECTS("Участники видимых проектов");

    private final String description;
}
