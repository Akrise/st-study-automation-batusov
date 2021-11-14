package at.tests.selenium_tests;

import at.study.redmine.allure.asserts.AllureAssert;
import at.study.redmine.api.ui.BrowserUtils;
import at.study.redmine.model.User;
import at.study.redmine.model.user.Status;
import io.qameta.allure.Allure;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginAsUnacceptedUserTests extends BaseUITest {

    User user;

    @BeforeMethod(description = "1. Заведен пользователь в системе.\n" +
            "2. Пользователю установлен статус Неподтвержден\n" +
            "3. Браузер открыт на домашней странице")
    public void prepareFixtures() {
        user = new User() {{
            setStatus(Status.UNACCEPTED);
        }}.create();
        openBrowser();
    }

    @Test(description = "Авторизация неподтвержденным пользователем")
    public void LoginAsUnacceptedUserTest() {
        Allure.step("Нажать на кнопку \"Вход\"");
        headerPage.login.click();
        loginPage.login(user);
        //1. Отображается домашняя страница
        AllureAssert.assertEquals(
                browser.getDriver().getCurrentUrl(),
                "http://edu-at.dfu.i-teco.ru/login",
                "Отображается домашняя страница");
        //2. Отображается ошибка с текстом "Ваша учётная запись создана и ожидает подтверждения администратора."
        String expectedError = "Your account was created and is now pending administrator approval.";
        AllureAssert.assertEquals(
                loginPage.error.getText(),
                expectedError,
                "Отображается ошибка с текстом \"Ваша учётная запись создана и ожидает подтверждения администратора.\"");
        //3. В заголовке страницы не отображаются элементы "Моя страница"
        AllureAssert.assertFalse(BrowserUtils.isElementPresent(headerPage.myPage), "В заголовке страницы отображаются элементы \"Моя страница\"");
        //4. В заголовке страницы отображаются элементы "Войти", "Регистрация"
        AllureAssert.assertTrue(BrowserUtils.isElementPresent(headerPage.login), "В заголовке страницы отображается элемент \"Войти\"");
        AllureAssert.assertTrue(BrowserUtils.isElementPresent(headerPage.accountRegister), "В заголовке страницы отображается элемент \"Регистрация\"");
    }
}
