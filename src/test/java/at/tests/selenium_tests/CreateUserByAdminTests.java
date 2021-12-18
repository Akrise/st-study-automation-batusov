package at.tests.selenium_tests;

import at.study.redmine.allure.asserts.AllureAssert;
import at.study.redmine.db.requests.UserRequests;
import at.study.redmine.model.User;
import io.qameta.allure.Allure;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CreateUserByAdminTests extends BaseUITest {

    User admin;
    User user;
    User userFromDB;

    @BeforeMethod(description = "1. Создан пользователь в системе с правами администратора\n" +
            "2. Открыт Браузер на главной старнице.")
    public void prepareFixtures() {
        admin = new User() {{
            setIsAdmin(true);
        }}.create();
        user = new User();
        openBrowser();
    }

    @Test
    public void CreateUserTest() {
        Allure.step("Нажать на кнопку \"Вход\"");
        headerPage.login.click();
        //1. Отображается домашняя страница
        loginPage.login(admin);
        Assert.assertEquals(
                browser.getDriver().getCurrentUrl(),
                "http://edu-at.dfu.i-teco.ru/",
                "Отображается домашняя страница");
        //1. Отображается страница "Администрирование"
        Allure.step("На главной странице нажать \"Администрирование\"");
        headerPage.administration.click();
        Assert.assertEquals(
                administrationPage.pageName.getText(),
                "Администрирование",
                "Отображается страница \"Администрирование\"");
        //1. Отображается страница "Пользователи >> Новый пользователь"
        Allure.step("Нажать на кнопку \"Пользователи\" в меню Администрирования");
        administrationPage.users.click();
        Allure.step("Нажать \"Новый пользователь\"");
        userTable.newUser.click();
        AllureAssert.assertEquals(
                newUserPage.pageName.getText(),
                "Пользователи » Новый пользователь",
                "Отображается страница \"Пользователи >> Новый пользователь\"");
        newUserPage.createUser(user);
        //1. Отображается сообщение "Пользователь <логин> создан."
        AllureAssert.assertEquals(
                newUserPage.notice.getText(),
                "Пользователь " + user.getLogin() + " создан.",
                "Отображается сообщение \"Пользователь " + user.getLogin() + " создан.\"");
        //2. В базе данных, в таблице users, появилась запись с данными пользователя
        Integer userID = Integer.parseInt(newUserPage.newUserUrl.getAttribute("href").replaceAll("[^0-9]", ""));
        userFromDB = new UserRequests().read(userID);
        Allure.step("В базе данных, в таблице users, появилась запись с данными пользователя");
        AllureAssert.assertEquals(
                userFromDB.getLogin(),
                user.getLogin(),
                "Логин пользователя в БД совпадает с выбранным при создании");
        AllureAssert.assertEquals(
                userFromDB.getFirstName(),
                user.getFirstName(),
                "Имя пользователя в БД совпадает с выбранным при создании");
        AllureAssert.assertEquals(
                userFromDB.getLastName(),
                user.getLastName(),
                "Фамилия пользователя в БД совпадает с выбранной при создании");
        AllureAssert.assertEquals(
                userFromDB.getEmails().get(0).getAddress(),
                user.getEmails().get(0).getAddress(),
                "Email пользователя в БД совпадает с выбранным при создании");
    }
}
