package at.tests.selenium_tests;

import at.study.redmine.db.requests.UserRequests;
import at.study.redmine.model.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CreateUserByAdminTests extends BaseUITest {

    User admin;
    User user;
    User userFromDB;

    @BeforeMethod
    public void prepareFixtures(){
        admin = new User(){{
            setIsAdmin(true);
        }}.create();
        user = new User();
        openBrowser();
    }

    @Test
    public void CreateUserTest(){
        headerPage.login.click();
        //1. Отображается домашняя страница
        loginPage.login(admin);
        Assert.assertEquals(browser.getDriver().getCurrentUrl(), "http://edu-at.dfu.i-teco.ru/");
        //1. Отображается страница "Администрирование"
        headerPage.administration.click();
        Assert.assertEquals(administration.pageName.getText(), "Администрирование");
        //1. Отображается страница "Пользователи >> Новый пользователь"
        administration.users.click();
        userTable.newUser.click();
        Assert.assertEquals(newUserPage.pageName.getText(), "Пользователи » Новый пользователь");
        newUserPage.createUser(user);
        //1. Отображается сообщение "Пользователь <логин> создан."
        Assert.assertEquals(newUserPage.notice.getText(), "Пользователь "+ user.getLogin() +" создан.");
        //2. В базе данных, в таблице users, появилась запись с данными пользователя
        Integer userID = Integer.parseInt(newUserPage.newUserUrl.getAttribute("href").replaceAll("[^0-9]", ""));
        userFromDB = new UserRequests().read(userID);
        Assert.assertEquals(userFromDB.getLogin(), user.getLogin());
        Assert.assertEquals(userFromDB.getFirstName(), user.getFirstName());
        Assert.assertEquals(userFromDB.getLastName(), user.getLastName());
        Assert.assertEquals(userFromDB.getPassword(), user.getPassword());
        Assert.assertEquals(userFromDB.getEmails().get(0).getAddress(), user.getEmails().get(0).getAddress());
    }
}
