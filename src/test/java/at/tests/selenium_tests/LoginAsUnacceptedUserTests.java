package at.tests.selenium_tests;

import at.study.redmine.api.ui.BrowserUtils;
import at.study.redmine.model.User;
import at.study.redmine.model.user.Status;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginAsUnacceptedUserTests extends BaseUITest {

    User user;

    @BeforeMethod
    public void prepareFixtures(){
        user = new User(){{
            setStatus(Status.UNACCEPTED);
        }}.create();
        openBrowser();
    }

    @Test
    public void  LoginAsUnacceptedUserTest(){
        headerPage.login.click();
        loginPage.login(user);
        //1. Отображается домашняя страница
        Assert.assertEquals(browser.getDriver().getCurrentUrl(), "http://edu-at.dfu.i-teco.ru/login");
        //2. Отображается ошибка с текстом "Ваша учётная запись создана и ожидает подтверждения администратора."
        String expectedError = "Your account was created and is now pending administrator approval.";
        Assert.assertEquals(loginPage.error.getText(), expectedError);
        //3. В заголовке страницы не отображаются элементы "Моя страница"
        Assert.assertFalse(BrowserUtils.isElementPresent(headerPage.myPage));
        //4. В заголовке страницы отображаются элементы "Войти", "Регистрация"
        Assert.assertTrue(BrowserUtils.isElementPresent(headerPage.login));
        Assert.assertTrue(BrowserUtils.isElementPresent(headerPage.accountRegister));
    }
}
