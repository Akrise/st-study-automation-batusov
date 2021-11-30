package at.study.redmine.ui.pages;

import at.study.redmine.cucumber.ElementName;
import at.study.redmine.cucumber.PageName;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@PageName("Администрирование")
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class Administration extends Page {

    @ElementName("Проекты")
    @FindBy(xpath = "//div[@id='admin-menu']//a[contains(@class, 'projects')]")
    public WebElement projects;

    @ElementName("Пользователи")
    @FindBy(xpath = "//div[@id='admin-menu']//a[contains(@class, 'users')]")
    public WebElement users;

    @ElementName("Группы")
    @FindBy(xpath = "//div[@id='admin-menu']//a[contains(@class, 'groups')]")
    public WebElement groups;

    @ElementName("Роли и права доступа")
    @FindBy(xpath = "//div[@id='admin-menu']//a[contains(@class, 'roles')]")
    public WebElement roles;

    @ElementName("Трекеры")
    @FindBy(xpath = "//div[@id='admin-menu']//a[contains(@class, 'trackers')]")
    public WebElement trackers;

    @ElementName("Статусы задач")
    @FindBy(xpath = "//div[@id='admin-menu']//a[contains(@class, 'issue-statuses')]")
    public WebElement issueStatuses;

    @ElementName("Последовательность действий")
    @FindBy(xpath = "//div[@id='admin-menu']//a[contains(@class, 'workflows')]")
    public WebElement workflows;

    @ElementName("Настраиваемые поля")
    @FindBy(xpath = "//div[@id='admin-menu']//a[contains(@class, 'custom-fields')]")
    public WebElement customFields;

    @ElementName("Списки значений")
    @FindBy(xpath = "//div[@id='admin-menu']//a[contains(@class, 'enumerations')]")
    public WebElement enumerations;

    @ElementName("Настройки")
    @FindBy(xpath = "//div[@id='admin-menu']//a[contains(@class, 'settings')]")
    public WebElement settings;

    @ElementName("Авторизация с помощью LDAP")
    @FindBy(xpath = "//div[@id='admin-menu']//a[contains(@class, 'ldap-authentification')]")
    public WebElement ldapAuthentification;

    @ElementName("Модули")
    @FindBy(xpath = "//div[@id='admin-menu']//a[contains(@class, 'plugins')]")
    public WebElement modules;

    @ElementName("Информация")
    @FindBy(xpath = "//div[@id='admin-menu']//a[contains(@class, 'info')]")
    public WebElement info;
}
