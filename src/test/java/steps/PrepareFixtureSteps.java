package steps;

import at.study.redmine.context.Context;
import at.study.redmine.cucumber.validators.UserParametersValidator;
import at.study.redmine.model.*;

import at.study.redmine.model.user.Status;
import cucumber.api.java.mn.Харин;
import cucumber.api.java.ru.И;
import cucumber.api.java.ru.Пусть;
import io.cucumber.datatable.DataTable;

import java.util.*;

public class PrepareFixtureSteps {

    @Пусть("Создан список E-mail адресов (.*):")
    public void createEmailList(String stashId, DataTable dataTable) {
        List<Map<String, String>> maps = dataTable.asMaps();
        List<Email> emails = new ArrayList<>();
        maps.forEach(map -> {
            String address = map.get("Адрес");
            Boolean isDefault = Boolean.parseBoolean(map.get("По умолчанию"));
            Boolean notify = Boolean.parseBoolean(map.get("Уведомления"));
            emails.add(new Email()
                    .setAddress(address)
                    .setIsDefault(isDefault)
                    .setNotify(notify));
        });

        Context.getStash().put(stashId, emails);
    }

    @Пусть("В системе создан пользователь (.*) со следующими параметрами:")
    public void createAdmin(String stashID, Map<String, String> parameters) {
        UserParametersValidator.validateUserParameters(parameters.keySet());
        User user = new User();
        user.setEmails(Collections.singletonList(new Email(user)));
        //TODO расширить список возможных параметров
        if (parameters.containsKey("Администратор")) {
            user.setIsAdmin(Boolean.parseBoolean(parameters.get("Администратор")));
        }
        if (parameters.containsKey("Статус")) {
            user.setStatus(Status.getValue(parameters.get("Статус")));
        }
        if (parameters.containsKey("E-mail")) {
            user.setEmails(Context.getStash().get(parameters.get("E-mail"), List.class));
        }
        if (parameters.containsKey("API key") && Boolean.parseBoolean(parameters.get("API key"))){
            List<Token> tokens = new ArrayList<>();
            tokens.add(new Token(user));
            user.setTokens(tokens);
        }
        user.create();
        Context.getStash().put(stashID, user);
    }

    @Пусть("Подготовлен к созданию пользователь (.*) со следующими параметрами:")
    public void userToCreateFunction(String stashID, Map<String, String> parameters) {
        UserParametersValidator.validateUserParameters(parameters.keySet());
        User user = new User();
        user.setEmails(Collections.singletonList(new Email(user)));
        if (parameters.containsKey("Администратор")) {
            user.setIsAdmin(Boolean.parseBoolean(parameters.get("Администратор")));
        }
        if (parameters.containsKey("Статус")) {
            user.setStatus(Status.getValue(parameters.get("Статус")));
        }
        if (parameters.containsKey("E-mail")) {
            user.setEmails(Context.getStash().get(parameters.get("E-mail"), List.class));
        }
        if (parameters.containsKey("API key") && Boolean.parseBoolean(parameters.get("API key"))){
            List<Token> tokens = new ArrayList<>();
            tokens.add(new Token(user));
            user.setTokens(tokens);
        }
        Context.getStash().put(stashID, user);
    }

    @Пусть("В системе создан пользователь с параметрами по умолчанию (.*)")
    public void createDefaultUser(String stashID) {
       createAdmin(stashID, Collections.EMPTY_MAP);
    }

    @И("В системе создан проект (.*) со следующими параметрами:")
    public void createProject(String stashID, Map<String, String> parameters) {
        //TODO сделать валидатор
        Project project = new Project();
        if (parameters.containsKey("Публичный")) {
            project.setIsPublic(Boolean.parseBoolean(parameters.get("Публичный")));
        }
        project.create();
        Context.getStash().put(stashID, project);
    }

    @Пусть("В системе создана роль (.*)")
    public void createDefaultRole(String stashID) {
        Role role = new Role().create();
        Context.getStash().put(stashID, role);
    }

    @И("Добавить в проекты пользователей со списоком ролей, с параметрами:")
    public void addUsersAndRolesToProjects(DataTable dataTable) {
        List<Map<String, String>> parametersList = dataTable.asMaps();
        for (Map<String, String> parameters : parametersList) {
            if (parameters.containsKey("Проект") && parameters.containsKey("Имя пользователя") && parameters.containsKey("Список ролей")) {
                Project project = Context.getStash().get(parameters.get("Проект"), Project.class);
                User user = Context.getStash().get(parameters.get("Имя пользователя"), User.class);
                Set<Role> roleSet = (Set<Role>) Context.getStash().get(parameters.get("Список ролей"), List.class);
                project.addUser(user, roleSet);
                project.update(project);
                Context.getStash().put(parameters.get("Проект"), project);
            }
        }
    }

    @И("Создано множество ролей (.*):")
    public void createRolesList(String stashID, DataTable dataTable) {
        List<String> stringRolesList = dataTable.asList(String.class);
        Set<Role> rolesList = new HashSet<>();
        for (String role : stringRolesList) {
            rolesList.add(Context.getStash().get(role, Role.class));
        }
        Context.getStash().put(stashID, rolesList);
    }

    @И("Изменить параметры пользователя (.*):")
    public void changeUserParams(String username, Map<String, String> parameters){
        UserParametersValidator.validateUserParameters(parameters.keySet());
        User user = (User)Context.getStash().get(username);
        if(parameters.containsKey("E-mail")){
            Email email = new Email();
            email.setIsDefault(true);
            email.setAddress(parameters.get("E-mail"));
            email.setUserId(user.getId());
            user.setEmails(Collections.singletonList(email));
        }
        if(parameters.containsKey("Password")){
            user.setPassword(parameters.get("Password"));
        }
        Context.getStash().put(username, user);
    }

}
