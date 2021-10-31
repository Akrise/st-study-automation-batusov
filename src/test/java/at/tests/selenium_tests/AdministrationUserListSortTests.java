package at.tests.selenium_tests;

import at.study.redmine.model.User;
import at.study.redmine.utils.CompareUtils;
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


    @BeforeMethod
    public void prepareFixtures() {
        admin = new User() {{
            setIsAdmin(true);
        }}.create();
        fisrtUser = new User();
        secondUser = new User();
        openBrowser();
    }

    @Test
    public void SortUserListByLoginTest() throws InterruptedException {
        headerPage.login.click();
        //1. Отображается домашняя страница
        loginPage.login(admin);
        Assert.assertEquals(browser.getDriver().getCurrentUrl(), "http://edu-at.dfu.i-teco.ru/");
        //1. Отображается страница "Администрирование"
        headerPage.administration.click();
        Assert.assertEquals(administration.pageName.getText(), "Администрирование");
        //1. Отображается таблица с пользователями
        administration.users.click();
        Assert.assertEquals(userTable.pageName.getText(), "Пользователи");
        //2. Таблица с пользователями отсортирована по логину пользователей по возрастанию
        List<String> users = getElementsText(userTable.users);
        Assert.assertTrue(CompareUtils.isListSortedByAsc(users));
        //1. Таблица с пользователями отсортирована по логину пользователей по убыванию
        userTable.button("Пользователь").click();
        users = getElementsText(userTable.users);
        Assert.assertTrue(CompareUtils.isListSortedByDesc(users));
    }

    @Test
    public void sortUserListByNameAndSurnameTest(){
        headerPage.login.click();
        //1. Отображается домашняя страница
        loginPage.login(admin);
        Assert.assertEquals(browser.getDriver().getCurrentUrl(), "http://edu-at.dfu.i-teco.ru/");
        //1. Отображается страница "Администрирование"
        headerPage.administration.click();
        Assert.assertEquals(administration.pageName.getText(), "Администрирование");
        //1. Отображается таблица с пользователями
        administration.users.click();
        Assert.assertEquals(userTable.pageName.getText(), "Пользователи");
        //2. Таблица с пользователями не отсортирована по фамилии
        Assert.assertFalse(isWebListSorted((userTable.lastNames)));
        //3. Таблица с пользователями не отсортирована по имени
        Assert.assertFalse(isWebListSorted(userTable.firstNames));
        //1. Таблица с пользователями отсортирована по фамилии по возрастанию (не учитывается регистр)
        userTable.button("Фамилия").click();
        Assert.assertTrue(isWebListSortedByAsc(userTable.lastNames));
        //2. Таблица с пользователями не отсортирована по имени
        Assert.assertFalse(isWebListSorted(userTable.firstNames));
        //1. Таблица с пользователями отсортирована по фамилии по убыванию (не учитывается регистр)
        userTable.button("Фамилия").click();
        Assert.assertTrue(isWebListSortedByDesc(userTable.lastNames));
        //2. Таблица с пользователями не отсортирована по имени
        Assert.assertFalse(isWebListSorted(userTable.firstNames));
        //1. Таблица с пользователями не отсортирована по фамилии
        userTable.button("Имя").click();
        Assert.assertFalse(isWebListSorted(userTable.lastNames));
        //2. Таблица с пользователями отсортирована по имени по возрастанию (не учитывается регистр)
        Assert.assertTrue(isWebListSortedByAsc(userTable.firstNames));
        //1. Таблица с пользователями не отсортирована по фамилии
        userTable.button("Имя").click();
        Assert.assertFalse(isWebListSorted(userTable.lastNames));
        //2. Таблица с пользователями отсортирована по имени по убыванию (не учитывается регистр)
        Assert.assertTrue(isWebListSortedByDesc(userTable.firstNames));
    }
}
