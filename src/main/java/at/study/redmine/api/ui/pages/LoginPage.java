package at.study.redmine.api.ui.pages;

import at.study.redmine.api.ui.BrowserManager;
import at.study.redmine.model.User;
import io.qameta.allure.Step;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class LoginPage extends Page {

    @FindBy(xpath = "//input[@id='username']")
    public WebElement username;

    @FindBy(xpath = "//input[@id='password']")
    public WebElement password;

    @FindBy(xpath = "//input[@id='login-submit']")
    public WebElement loginButton;

    @FindBy(xpath = "//div[@id='main']//div[@id='flash_error']")
    public WebElement error;

    public void login(User user) {
        login(user.getLogin(), user.getPassword());
    }

    @Step("Авторизация на сайте с логином {0} и паролем {1}")
    public void login(String login, String pass) {
        username.sendKeys(login);
        password.sendKeys(pass);
        loginButton.click();
    }
}
