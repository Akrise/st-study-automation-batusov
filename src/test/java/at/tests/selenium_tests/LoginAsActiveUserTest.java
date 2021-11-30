package at.tests.selenium_tests;

import at.study.redmine.allure.asserts.AllureAssert;
import at.study.redmine.ui.BrowserUtils;
import at.study.redmine.model.User;
import io.qameta.allure.Allure;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginAsActiveUserTest extends BaseUITest {

    private User user;

    @BeforeMethod(description = "1. Заведен пользователь в системе.\n" +
            "2. Пользователь подтвержден администратором и не заблокирован\n" +
            "3. Браузер открыт на домашней странице")
    public void prepareFixtures() {
        user = new User().create();
        openBrowser();
    }

    @Test(description = "Авторизация подтвержденным пользователем")
    public void loginAsActiveUserTest() {
        Allure.step("Нажать на кнопку \"Войти\"");
        headerPage.login.click();
        loginPage.login(user);
        //1. Отображается домашняя страница
        AllureAssert.assertEquals(
                browser.getDriver().getCurrentUrl(),
                "http://edu-at.dfu.i-teco.ru/",
                "Отображается домашняя страница");
        //2. Отображается "Вошли как <логин пользователя>"
        AllureAssert.assertEquals(
                headerPage.loggedAs.getText(),
                "Вошли как " + user.getLogin(),
                "Отображается \"Вошли как " + user.getLogin() + "\"");
        //3. В заголовке страницы отображаются элементы: "Домашняя страница", "Моя страница", "Проекты", "Помощь", "Моя учётная запись", "Выйти"
        AllureAssert.assertTrue(headerPage.homepage.isDisplayed(), "Отображается элемент Домашняя страница");
        AllureAssert.assertTrue(headerPage.myPage.isDisplayed(), "Отображается элемент Моя страница");
        AllureAssert.assertTrue(headerPage.projects.isDisplayed(), "Отображается элемент Проекты");
        AllureAssert.assertTrue(headerPage.help.isDisplayed(), "Отображается элемент Помощь");
        AllureAssert.assertTrue(headerPage.myAccount.isDisplayed(), "Отображается элемент Моя учётная запись");
        AllureAssert.assertTrue(headerPage.exit.isDisplayed(), "Отображается элемент Выйти");
        //4. В заголовке страницы не отображаются элементы "Войти", "Регистрация", "Администрирование"
        AllureAssert.assertFalse(BrowserUtils.isElementPresent(headerPage.login), "Отображается элемент Войти");
        AllureAssert.assertFalse(BrowserUtils.isElementPresent(headerPage.accountRegister), "Отображается элемент Регистрация");
        AllureAssert.assertFalse(BrowserUtils.isElementPresent(headerPage.administration), "Отображается элемент Администрирование");
        //5. Отображается элемент "Поиск"
        AllureAssert.assertTrue(headerPage.searchField.isDisplayed(), "Отображается поле поиска");
        AllureAssert.assertTrue(headerPage.searchLink.isDisplayed(), "Отображается элемент Поиск:");
    }

}
