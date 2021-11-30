package at.study.redmine.ui.pages;

import at.study.redmine.cucumber.ElementName;
import at.study.redmine.cucumber.PageName;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@PageName("Заголовок")
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class HeaderPage extends Page {

    @ElementName("Домашняя страница")
    @FindBy(xpath = "//div[@id='top-menu']//a[@class='home']")
    public WebElement homepage;

    @ElementName("Моя страница")
    @FindBy(xpath = "//div[@id='top-menu']//a[@class='my-page']")
    public WebElement myPage;

    @ElementName("Проекты")
    @FindBy(xpath = "//div[@id='top-menu']//a[@class='projects']")
    public WebElement projects;

    @ElementName("Администрирование")
    @FindBy(xpath = "//div[@id='top-menu']//a[@class='administration']")
    public WebElement administration;

    @ElementName("Помощь")
    @FindBy(xpath = "//div[@id='top-menu']//a[@class='help']")
    public WebElement help;

    @ElementName("Вошли как")
    @FindBy(xpath = "//div[@id='top-menu']//div[@id='loggedas']")
    public WebElement loggedAs;

    @ElementName("Имя пользователя")
    @FindBy(xpath = "//div[@id='top-menu']//a[@class='user active']")
    public WebElement userPage;

    @ElementName("Войти")
    @FindBy(xpath = "//div[@id='account']//a[@class='login']")
    public WebElement login;

    @ElementName("Регистрация")
    @FindBy(xpath = "//div[@id='top-menu']//a[@class='register']")
    public WebElement accountRegister;

    @ElementName("Моя учетная запись")
    @FindBy(xpath = "//div[@id='top-menu']//a[@class='my-account']")
    public WebElement myAccount;

    @ElementName("Выйти")
    @FindBy(xpath = "//div[@id='top-menu']//a[@class='logout']")
    public WebElement exit;

    @ElementName("Поле поиска")
    @FindBy(xpath = "//div[@id='header']//input[@id='q']")
    public WebElement searchField;

    @ElementName("Поиск:")
    @FindBy(xpath = "//div[@id='header']//label[@for='q']")
    public WebElement searchLink;

}
