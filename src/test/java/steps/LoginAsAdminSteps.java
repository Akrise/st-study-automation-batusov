package steps;

import at.study.redmine.api.ui.BrowserManager;
import at.study.redmine.api.ui.pages.HeaderPage;
import at.study.redmine.api.ui.pages.LoginPage;
import at.study.redmine.api.ui.pages.Page;
import at.study.redmine.model.User;
import cucumber.api.java.ru.И;

public class LoginAsAdminSteps {

    private User user;

//    @И("В системе заведен пользователь с правами администратора")
//    public void createAdmin(){
//        user = new User() {{
//            setIsAdmin(true);
//        }}.create();
//    }
//
//    @И("Браузер открыт на домашней странице")
//    public void openBrowser(){
//        BrowserManager.getBrowser();
//    }
//
//    @И("Нажать на кнопку Войти")
//    public void pressLoginButton(){
//        Page.getPage(HeaderPage.class).login.click();
//    }
//
//    @И("Войти в систему созданным пользователем (.*)")
//    public void loginAs(User user){
//        Page.getPage(LoginPage.class).login(user);
//    }
//}
}