package at.tests.selenium_tests;

import at.study.redmine.allure.asserts.AllureAssert;
import at.study.redmine.api.ui.BrowserUtils;
import at.study.redmine.api.ui.pages.HeaderPage;
import at.study.redmine.model.User;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginAsAdmin extends BaseUITest {

    private User admin;

    @BeforeMethod(description = "В системе заведен пользователь с правами администратора. Открыт Браузер на главной старнице.")
    public void beforeTest() {
        admin = new User() {{
            setIsAdmin(true);
        }}.create();
        openBrowser();
    }

    @Test(description = "Вход администратором. Проверка наличия элемента \"Моя учетная запись\".")
    @Severity(SeverityLevel.BLOCKER)
    @Owner("Батусов Вячеслав Игоревич")
    public void loginAsAdmin() {
        headerPage.login.click();
        loginPage.login(admin);
        //1. Отображается домашняя страница
        AllureAssert.assertEquals(
                browser.getDriver().getCurrentUrl(),
                "http://edu-at.dfu.i-teco.ru/",
                "Открыта домашняя страница");
        //2. Отображается "Вошли как <логин пользователя>"
        //  Assert.assertEquals(headerPage.loggedAs.getText(), "Вошли как " + admin.getLogin());
        AllureAssert.assertEquals(
                headerPage.loggedAs.getText(),
                "Вошли как " + admin.getLogin(),
                "Проверка отображения текста \"Вошли как <логин пользователя>\"");
        //3. В заголовке страницы отображаются элементы: "Домашняя страница", "Моя страница", "Проекты", "Администрирование", "Помощь", "Моя учётная запись", "Выйти"
        AllureAssert.assertTrue(headerPage.homepage.isDisplayed(), "Отображается элемент \"Домашняя страница\"");
        AllureAssert.assertTrue(headerPage.myPage.isDisplayed(), "Отображается элемент \"Моя страница\"");
        AllureAssert.assertTrue(headerPage.projects.isDisplayed(), "Отображается элемент \"Проекты\"");
        AllureAssert.assertTrue(headerPage.administration.isDisplayed(), "Отображается элемент \"Администрирование\"");
        AllureAssert.assertTrue(headerPage.help.isDisplayed(), "Отображается элемент \"Помощь\"");
        AllureAssert.assertTrue(headerPage.myAccount.isDisplayed(), "Отображается элемент \"Моя учётная запись\"");
        AllureAssert.assertTrue(headerPage.exit.isDisplayed(), "Отображается элемент \"Выйти\"");
        //4. В заголовке страницы не отображаются элементы "Войти", "Регистрация"
        AllureAssert.assertFalse(BrowserUtils.isElementPresent(headerPage.login), "Не отображается элемент \"Войти\"");
        AllureAssert.assertFalse(BrowserUtils.isElementPresent(headerPage.accountRegister), "Не отображается элемент \"Регистрация\"");
        //5. Отображается элемент "Поиск"
        AllureAssert.assertTrue(headerPage.searchField.isDisplayed(), "Отображается поле для поиска");
        AllureAssert.assertTrue(headerPage.searchLink.isDisplayed(), "Отображается элемент \"Поиск:\"");
    }

}
