package steps;

import at.study.redmine.api.ui.pages.LoginPage;
import at.study.redmine.api.ui.pages.Page;
import at.study.redmine.context.Context;
import at.study.redmine.model.User;
import cucumber.api.java.ru.И;

public class AuthorisationSteps {

    @И("Войти в систему пользователем (.*)")
    public void loginAs(String stashID){
        Page.getPage(LoginPage.class).login(Context.getStash().get(stashID, User.class));
    }

    @И("Войти в систему c логином (.*) и паролем (.*)")
    public void loginAs(String login, String password){
        Page.getPage(LoginPage.class).login(login, password);
    }
}
