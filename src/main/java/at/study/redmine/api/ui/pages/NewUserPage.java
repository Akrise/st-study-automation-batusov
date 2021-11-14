package at.study.redmine.api.ui.pages;

import at.study.redmine.model.Email;
import at.study.redmine.model.User;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
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

    @Step("Заполнить поля на странице создания пользователя и нажать кнопку Создать")
    public void createUser(User user){
        Allure.step("Заполнить поле Логин");
        login.sendKeys(user.getLogin());
        Allure.step("Заполнить поле Имя");
        firstname.sendKeys(user.getFirstName());
        Allure.step("Заполнить поле Фамилия");
        lastname.sendKeys(user.getLastName());
        Allure.step("Заполнить поле Email");
        email.sendKeys(user.getEmails().stream().filter(Email::getIsDefault).findFirst().orElse(new Email(user)).getAddress());
        Allure.step("Установить чекбокс Создание пароля");
        isPasswordGenerated.click();
        createButton.click();
    }
}
