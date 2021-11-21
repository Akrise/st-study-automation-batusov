package steps;

import at.study.redmine.context.Context;
import at.study.redmine.model.User;

import cucumber.api.java.ru.Пусть;

import java.util.Map;

public class PrepareFixtureSteps {

    @Пусть("В системе создан пользователь (.*) со следующими параметрами:")
    public void createAdmin(String stashID, Map<String, String> parameters){

        User user = new User() {{
            //TODO расширить список возможных параметров
            if(parameters.containsKey("Администратор")){
                setIsAdmin(Boolean.parseBoolean(parameters.get("Администратор")));
            }
            if(parameters.containsKey("Статус")){
                setIsAdmin(Boolean.parseBoolean(parameters.get("Администратор")));
            }

        }}.create();
        Context.getStash().put(stashID, user);
    }

}
