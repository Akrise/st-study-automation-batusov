package at.tests.selenium_tests;

import at.study.redmine.allure.asserts.AllureAssert;
import at.study.redmine.model.User;
import at.study.redmine.utils.CompareUtils;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static at.study.redmine.api.ui.BrowserUtils.getElementsText;
import static at.study.redmine.utils.CompareUtils.*;

public class AdministrationUserListSortTests extends BaseUITest {

    private User admin;
    private User fisrtUser;
    private User secondUser;


    @BeforeMethod(description = "1. Заведен пользователь в системе с правами администратора\n" +
            "2. Заведено несколько пользователей в системе\n" +
            "3. Открыт браузер")
    public void prepareFixtures() {
        admin = new User() {{
            setIsAdmin(true);
        }}.create();
        fisrtUser = new User();
        secondUser = new User();
        openBrowser();
    }

    @Test(description = "Администрирование. Сортировка списка пользователей по пользователю")
    public void SortUserListByLoginTest() throws InterruptedException {
        Allure.step("Нажать на кнопку \"Вход\"");
        headerPage.login.click();
        //1. Отображается домашняя страница
        loginPage.login(admin);
        AllureAssert.assertEquals(
                browser.getDriver().getCurrentUrl(),
                "http://edu-at.dfu.i-teco.ru/",
                "Отображается домашняя страница");
        //1. Отображается страница "Администрирование"
        Allure.step("На главной странице нажать \"Администрирование\"");
        headerPage.administration.click();
        AllureAssert.assertEquals(
                administration.pageName.getText(),
                "Администрирование",
                "Отображается страница \"Администрирование\"");
        //1. Отображается таблица с пользователями
        Allure.step("Нажать на кнопку \"Пользователи\" в меню Администрирования");
        administration.users.click();
        AllureAssert.assertEquals(
                userTable.pageName.getText(),
                "Пользователи",
                "Отображается таблица с пользователями");
        //2. Таблица с пользователями отсортирована по логину пользователей по возрастанию
        List<String> users = getElementsText(userTable.users);
        AllureAssert.assertTrue(CompareUtils.isListSortedByAsc(users), "Таблица с пользователями отсортирована по логину пользователей по возрастанию");
        //1. Таблица с пользователями отсортирована по логину пользователей по убыванию
        Allure.step("В шапке таблицы нажать на кнопку \"Пользователь\"");
        userTable.button("Пользователь").click();
        users = getElementsText(userTable.users);
        AllureAssert.assertTrue(CompareUtils.isListSortedByDesc(users), "Таблица с пользователями отсортирована по логину пользователей по убыванию");
    }

    @Test(description = "Администрирование. Сортировка списка пользователей по имени и фамилии")
    public void sortUserListByNameAndSurnameTest(){
        Allure.step("Нажать на кнопку \"Вход\"");
        headerPage.login.click();
        //1. Отображается домашняя страница
        loginPage.login(admin);
        AllureAssert.assertEquals(browser.getDriver().getCurrentUrl(), "http://edu-at.dfu.i-teco.ru/", "Отображается домашняя страница" );
        //1. Отображается страница "Администрирование"
        Allure.step("На главной странице нажать \"Администрирование\"");
        headerPage.administration.click();
        AllureAssert.assertEquals(administration.pageName.getText(), "Администрирование", "Отображается страница \"Администрирование\"");
        //1. Отображается таблица с пользователями
        Allure.step("Нажать на кнопку \"Пользователи\" в меню Администрирования");
        administration.users.click();
        AllureAssert.assertEquals(userTable.pageName.getText(), "Пользователи", "\"Отображается таблица с пользователями\"");
        //2. Таблица с пользователями не отсортирована по фамилии
        AllureAssert.assertFalse(isWebListSorted((userTable.lastNames)), "Таблица с пользователями отсортирована по фамилии");
        //3. Таблица с пользователями не отсортирована по имени
        AllureAssert.assertFalse(isWebListSorted(userTable.firstNames), "Таблица с пользователями отсортирована по имени");
        //1. Таблица с пользователями отсортирована по фамилии по возрастанию (не учитывается регистр)
        Allure.step("В шапке таблицы нажать на \"Фамилия\"");
        userTable.button("Фамилия").click();
        AllureAssert.assertTrue(isWebListSortedByAsc(userTable.lastNames), "Таблица с пользователями отсортирована по фамилии по возрастанию (не учитывается регистр)");
        //2. Таблица с пользователями не отсортирована по имени
        AllureAssert.assertFalse(isWebListSorted(userTable.firstNames), "Таблица с пользователями отсортирована по имени");
        //1. Таблица с пользователями отсортирована по фамилии по убыванию (не учитывается регистр)
        Allure.step("В шапке таблицы нажать на \"Фамилия\"");
        userTable.button("Фамилия").click();
        AllureAssert.assertTrue(isWebListSortedByDesc(userTable.lastNames), " Таблица с пользователями отсортирована по фамилии по убыванию (не учитывается регистр)");
        //2. Таблица с пользователями не отсортирована по имени
        AllureAssert.assertFalse(isWebListSorted(userTable.firstNames), "Таблица с пользователями отсортирована по имени");
        //1. Таблица с пользователями не отсортирована по фамилии
        Allure.step("В шапке таблицы нажать на \"Имя\"");
        userTable.button("Имя").click();
        AllureAssert.assertFalse(isWebListSorted(userTable.lastNames), "Таблица с пользователями отсортирована по фамилии");
        //2. Таблица с пользователями отсортирована по имени по возрастанию (не учитывается регистр)
        AllureAssert.assertTrue(isWebListSortedByAsc(userTable.firstNames), "Таблица с пользователями отсортирована по имени по возрастанию (не учитывается регистр)");
        //1. Таблица с пользователями не отсортирована по фамилии
        Allure.step("В шапке таблицы нажать на \"Имя\"");
        userTable.button("Имя").click();
        AllureAssert.assertFalse(isWebListSorted(userTable.lastNames), "Таблица с пользователями отсортирована по фамилии");
        //2. Таблица с пользователями отсортирована по имени по убыванию (не учитывается регистр)
        AllureAssert.assertTrue(isWebListSortedByDesc(userTable.firstNames), "Таблица с пользователями отсортирована по имени по убыванию (не учитывается регистр)");
    }
}
