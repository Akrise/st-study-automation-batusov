package steps;

import at.study.redmine.api.ui.pages.HeaderPage;
import at.study.redmine.api.ui.pages.Page;
import cucumber.api.java.ru.И;

public class UISteps {

    @И("Нажать на кнопку Войти")
    public void pressLoginButton(){
        Page.getPage(HeaderPage.class).login.click();
    }
}
