package at.study.redmine.api.ui.pages;

import at.study.redmine.api.ui.BrowserManager;
import at.study.redmine.model.User;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends Page{

    @FindBy(xpath = "//input[@id='username']")
    private WebElement username;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement password;

    @FindBy(xpath = "//input[@id='login-submit']")
    private WebElement loginButton;

    public LoginPage() {
        PageFactory.initElements(BrowserManager.getBrowser().getDriver(), this);
    }

    public void login(User user){
        login(user.getLogin(), user.getPassword());
    }

    public void login(String login, String pass){
        username.sendKeys(login);
        password.sendKeys(pass);
        loginButton.click();
    }
}
