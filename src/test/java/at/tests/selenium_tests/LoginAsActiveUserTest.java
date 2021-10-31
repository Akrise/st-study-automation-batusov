package at.tests.selenium_tests;

import at.study.redmine.api.ui.BrowserUtils;
import at.study.redmine.model.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginAsActiveUserTest extends BaseUITest{

    private User user;

    @BeforeMethod
    public void prepareFixtures(){
        user = new User().create();
        openBrowser();
    }

    @Test
    public void loginAsActiveUserTest(){
        headerPage.login.click();
        loginPage.login(user);
        //1. Отображается домашняя страница
        Assert.assertEquals(browser.getDriver().getCurrentUrl(), "http://edu-at.dfu.i-teco.ru/");
        //2. Отображается "Вошли как <логин пользователя>"
        Assert.assertEquals(headerPage.loggedAs.getText(), "Вошли как " + user.getLogin());
        Assert.assertEquals(headerPage.userPage.getText(), user.getLogin());
        //3. В заголовке страницы отображаются элементы: "Домашняя страница", "Моя страница", "Проекты", "Администрирование", "Помощь", "Моя учётная запись", "Выйти"
        Assert.assertTrue(headerPage.homepage.isDisplayed());
        Assert.assertTrue(headerPage.myPage.isDisplayed());
        Assert.assertTrue(headerPage.projects.isDisplayed());
        Assert.assertTrue(headerPage.help.isDisplayed());
        Assert.assertTrue(headerPage.myAccount.isDisplayed());
        Assert.assertTrue(headerPage.exit.isDisplayed());
        //4. В заголовке страницы не отображаются элементы "Войти", "Регистрация"
        Assert.assertFalse(BrowserUtils.isElementPresent(headerPage.login));
        Assert.assertFalse(BrowserUtils.isElementPresent(headerPage.accountRegister));
        Assert.assertFalse(BrowserUtils.isElementPresent(headerPage.administration));
        //5. Отображается элемент "Поиск"
        Assert.assertTrue(headerPage.searchField.isDisplayed());
        Assert.assertTrue(headerPage.searchLink.isDisplayed());
    }

}
