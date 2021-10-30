package at.tests.selenium_tests;

import at.study.redmine.model.User;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

public class AdministrationUserListSort extends BaseUITest{

    private User admin;

    @BeforeMethod
    public void prepareFixtures(){
        admin = new User(){{
            setIsAdmin(true);
        }}.create();

        openBrowser();
        headerPage.login.click();
        loginPage.login(admin);
        headerPage.administration.click();
        administration.users.click();

    }
}
