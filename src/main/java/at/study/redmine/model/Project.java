package at.study.redmine.model;

import at.study.redmine.db.requests.ProjectRequests;
import at.study.redmine.model.project.Status;
import at.study.redmine.utils.StringUtils;
import io.qameta.allure.Step;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Project extends CreatableEntity implements Creatable<Project>, Readable<Project>, Updatable<Project>, Deletable<Project> {
    private String name = "BATProject" + StringUtils.randomEnglishString(7);
    private String description = "Description" + StringUtils.randomEnglishString(10);
    private String homepage = StringUtils.randomEnglishString(10);
    private Boolean isPublic = true;
    private Integer parentID;
    private String identifier = StringUtils.randomHexString(10);
    private Status status = Status.OPEN;
    private Integer lft = 1;
    private Integer rgt = 1;
    private Boolean inherit_members = true;
    private Integer default_version_id;
    private Integer default_assigned_to_id;
    private Map<User, Set<Role>> members = new HashMap<>();


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

    /**
     * Функция для создания в БД нового проекта со значениями по умолчанию
     * @return Созданный проект
     */
    @Override
    @Step("Создать новый проект в БД")
    public Project create() {
        new ProjectRequests().create(this);
        return this;
    }

    public Boolean getInheritMembers() {
        return this.inherit_members;
    }

    /**
     * Функция для удаления проекта из БД.
     */
    @Override
    public void delete() {
        new ProjectRequests().delete(id);
    }

    /**
     * Функция для чтения проекта из БД
     * @return Проект, прочитанный из БД
     */
    @Override
    public Project read() {
        new ProjectRequests().read(id);
        return this;
    }

    /**
     * Функция для обновления значений проекта в БД
     * @param project Новые занчения для обновления
     */
    @Override
    public void update(Project project) {
        new ProjectRequests().update(id, project);
    }
}
