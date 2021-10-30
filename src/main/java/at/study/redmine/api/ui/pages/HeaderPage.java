package at.study.redmine.api.ui.pages;

import at.study.redmine.api.ui.BrowserManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HeaderPage extends Page{

    @FindBy(xpath = "//div[@id='top-menu']//a[@class='home']")
    public WebElement homepage;

    @FindBy(xpath = "//div[@id='top-menu']//a[@class='my-page']")
    public WebElement myPage;

    @FindBy(xpath = "//div[@id='top-menu']//a[@class='projects']")
    public WebElement projects;

    @FindBy(xpath = "//div[@id='top-menu']//a[@class='administration']")
    public WebElement administration;

    @FindBy(xpath = "//div[@id='top-menu']//a[@class='help']")
    public WebElement help;

    @FindBy(xpath = "//div[@id='top-menu']//div[@id='loggedas']")
    public WebElement loggedAs;

    @FindBy(xpath = "//div[@id='top-menu']//a[@class='user active']")
    public WebElement userPage;

    @FindBy(xpath = "//div[@id='account']//a[@class='login']")
    public WebElement login;

    @FindBy(xpath = "//div[@id='top-menu']//a[@class='register']")
    public WebElement accountRegister;

    @FindBy(xpath = "//div[@id='top-menu']//a[@class='my-account']")
    public WebElement myAccount;

    @FindBy(xpath = "//div[@id='top-menu']//a[@class='logout']")
    public WebElement exit;

    @FindBy(xpath = "//div[@id='header']//input[@id='q']")
    public WebElement searchField;

    @FindBy(xpath = "//div[@id='header']//label[@for='q']")
    public WebElement searchLink;

    public HeaderPage() {
        WebDriver driver  = BrowserManager.getBrowser().getDriver();
        PageFactory.initElements(driver, this);
    }
}
