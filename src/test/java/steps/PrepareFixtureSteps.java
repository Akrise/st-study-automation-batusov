package steps;

import at.study.redmine.context.Context;
import at.study.redmine.cucumber.validators.UserParametersValidator;
import at.study.redmine.model.Email;
import at.study.redmine.model.Project;
import at.study.redmine.model.User;

import at.study.redmine.model.user.Status;
import cucumber.api.java.mn.Харин;
import cucumber.api.java.ru.И;
import cucumber.api.java.ru.Пусть;
import io.cucumber.datatable.DataTable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        user.create();
        Context.getStash().put(stashID, user);
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
}