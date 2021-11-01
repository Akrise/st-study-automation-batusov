package at.tests.selenium_tests;

import at.study.redmine.api.ui.Browser;
import at.study.redmine.api.ui.BrowserManager;
import at.study.redmine.api.ui.pages.*;
import org.testng.annotations.AfterMethod;

public class BaseUITest {
    protected Browser browser;
    protected HeaderPage headerPage;
    protected LoginPage loginPage;
    protected Administration administration;
    protected UserTable userTable;
    protected ProjectsPage projectsPage;
    protected NewUserPage newUserPage;

    protected void openBrowser(){
        browser = BrowserManager.getBrowser();
        userTable = Page.getPage(UserTable.class);
        administration = Page.getPage(Administration.class);
        loginPage = Page.getPage(LoginPage.class);
        headerPage = Page.getPage(HeaderPage.class);
        projectsPage = Page.getPage(ProjectsPage.class);
        newUserPage = Page.getPage(NewUserPage.class);
    }

    @AfterMethod
    public void afterMethod(){
        browser.getDriver().quit();
        browser=null;
    }
}
