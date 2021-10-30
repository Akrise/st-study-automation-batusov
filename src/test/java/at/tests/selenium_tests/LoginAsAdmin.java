package at.tests.selenium_tests;

import at.study.redmine.api.ui.pages.HeaderPage;
import at.study.redmine.model.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginAsAdmin extends BaseUITest{

    private User admin;

    @BeforeMethod
    public void beforeTest(){
        admin = new User(){{
            setIsAdmin(true);
        }}.create();
        openBrowser();
    }

    @Test
    public void loginAsAdmin() {
        headerPage.login.click();
        loginPage.login(admin);
        Assert.assertEquals(browser.getDriver().getCurrentUrl(), "http://edu-at.dfu.i-teco.ru/");

        Assert.assertEquals(headerPage.loggedAs.getText(), "Вошли как " + admin.getLogin());
        Assert.assertEquals(headerPage.userPage.getText(),  admin.getLogin());

        Assert.assertTrue(headerPage.homepage.isDisplayed());
        Assert.assertTrue(headerPage.myPage.isDisplayed());
        Assert.assertTrue(headerPage.projects.isDisplayed());
        Assert.assertTrue(headerPage.administration.isDisplayed());
        Assert.assertTrue(headerPage.help.isDisplayed());
        Assert.assertTrue(headerPage.myAccount.isDisplayed());
        Assert.assertTrue(headerPage.exit.isDisplayed());

        //TODO переделать через PageUtils
       // Assert.assertFalse(headerPage.login.isDisplayed());
        //Assert.assertFalse(headerPage.accountRegister.isDisplayed());

        Assert.assertTrue(headerPage.searchField.isDisplayed());
        Assert.assertTrue(headerPage.searchLink.isDisplayed());
    }

}
