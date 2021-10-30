package at.tests.selenium_tests;

import at.study.redmine.api.ui.Browser;
import at.study.redmine.api.ui.BrowserManager;
import at.study.redmine.api.ui.pages.HeaderPage;
import at.study.redmine.api.ui.pages.LoginPage;
import org.testng.annotations.AfterMethod;

public class BaseUITest {
    protected Browser browser;
    protected HeaderPage headerPage = new HeaderPage();
    protected LoginPage loginPage = new LoginPage();

    protected void openBrowser(){
        browser = BrowserManager.getBrowser();
    }

    @AfterMethod
    public void afterMethod(){
        browser.getDriver().quit();
    }
}
