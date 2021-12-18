package at.study.redmine.ui.pages;

import at.study.redmine.cucumber.ElementName;
import at.study.redmine.cucumber.PageName;
import at.study.redmine.model.Email;
import at.study.redmine.model.User;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@PageName("Создание пользователя")
public class NewUserPage extends Page {

    @ElementName("Пользователь")
    @FindBy(xpath = "//div[@id='content']//input[@id='user_login']")
    public WebElement login;

    @ElementName("Имя")
    @FindBy(xpath = "//div[@id='content']//input[@id='user_firstname']")
    public WebElement firstname;

    @ElementName("Фамилия")
    @FindBy(xpath = "//div[@id='content']//input[@id='user_lastname']")
    public WebElement lastname;

    @ElementName("Email")
    @FindBy(xpath = "//div[@id='content']//input[@id='user_mail']")
    public WebElement email;

    @ElementName("Создание пароля")
    @FindBy(xpath = "//div[@id='content']//input[@id='user_generate_password']")
    public WebElement isPasswordGenerated;

    @ElementName("Создать")
    @FindBy(xpath = "//div[@id='content']//input[@name='commit']")
    public WebElement createButton;

    @ElementName("Уведомления по email")
    @FindBy(xpath = "//div[@id='content']//div[@class='flash notice']")
    public WebElement notice;

    @FindBy(xpath = "//div[@id='content']//div[@class='flash notice']/a[contains(@href,'/users/')]")
    public WebElement newUserUrl;

    public void createRandomUser() {
        createUser(new User());
    }

    @Step("Заполнить поля на странице создания пользователя и нажать кнопку Создать")
    public void createUser(User user) {
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
