package at.study.redmine.api.ui.pages;

import at.study.redmine.model.Email;
import at.study.redmine.model.User;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.nio.file.WatchEvent;

public class NewUserPage extends Page {

    @FindBy(xpath = "//div[@id='content']//input[@id='user_login']")
    public WebElement login;

    @FindBy(xpath = "//div[@id='content']//input[@id='user_firstname']")
    public WebElement firstname;

    @FindBy(xpath = "//div[@id='content']//input[@id='user_lastname']")
    public WebElement lastname;

    @FindBy(xpath = "//div[@id='content']//input[@id='user_mail']")
    public WebElement email;

    @FindBy(xpath = "//div[@id='content']//input[@id='user_generate_password']")
    public WebElement isPasswordGenerated;

    @FindBy(xpath = "//div[@id='content']//input[@name='commit']")
    public WebElement createButton;

    @FindBy(xpath = "//div[@id='content']//div[@class='flash notice']")
    public WebElement notice;

    @FindBy(xpath = "//div[@id='content']//div[@class='flash notice']/a[contains(@href,'/users/')]")
    public WebElement newUserUrl;

    public void createRandomUser(){
        createUser(new User());
    }

    public void createUser(User user){
        login.sendKeys(user.getLogin());
        firstname.sendKeys(user.getFirstName());
        lastname.sendKeys(user.getLastName());
        email.sendKeys(user.getEmails().stream().filter(Email::getIsDefault).findFirst().orElse(new Email(user)).getAddress());
        isPasswordGenerated.click();
        createButton.click();
    }
}
