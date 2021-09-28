package at.study.redmine.model;

import at.study.redmine.model.project.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
public class Project extends Entity implements Creatable<Project> {
    private String name;
    private String description;
    private String homepage;
    private boolean isPublic;
    private int parentID;
    private String identifier;
    private Status status = Status.OPEN;
    private int lft;
    private int rgt;
    private boolean inherit_members;
    private int default_version_id;
    private int default_assigned_to_id;
    private Map<User, Set<Role>> members;


    /**
     * Функция для добавления участника с заданными ролями в проект
     * @param user Пользователь для добавления, уникальное значение
     * @param roles Список ролей пользователя
     */
    public void addUser(User user, Set<Role> roles) {
        if (members.containsKey(user)) {
            throw new IllegalArgumentException("Нельзя дважды добавить пользователя в проект");
        }
        if (roles.size() == 0) {
            throw new IllegalArgumentException("Нельзя добавить пользователя без указания роли");
        }
        members.put(user, roles);
    }

    @Override
    public Project create() {
        return null;
    }
}
