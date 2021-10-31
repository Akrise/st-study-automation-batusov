package at.study.redmine.api.ui.pages;

import at.study.redmine.api.ui.BrowserManager;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class Administration extends Page {

    @FindBy(xpath = "//div[@id='content']//h2")
    public WebElement pageName;

    @FindBy(xpath = "//div[@id='admin-menu']//a[contains(@class, 'projects')]")
    public WebElement projects;

    @FindBy(xpath = "//div[@id='admin-menu']//a[contains(@class, 'users')]")
    public WebElement users;

    @FindBy(xpath = "//div[@id='admin-menu']//a[contains(@class, 'groups')]")
    public WebElement groups;

    @FindBy(xpath = "//div[@id='admin-menu']//a[contains(@class, 'roles')]")
    public WebElement roles;

    @FindBy(xpath = "//div[@id='admin-menu']//a[contains(@class, 'trackers')]")
    public WebElement trackers;

    @FindBy(xpath = "//div[@id='admin-menu']//a[contains(@class, 'issue-statuses')]")
    public WebElement issueStatuses;

    @FindBy(xpath = "//div[@id='admin-menu']//a[contains(@class, 'workflows')]")
    public WebElement workflows;

    @FindBy(xpath = "//div[@id='admin-menu']//a[contains(@class, 'custom-fields')]")
    public WebElement customFields;

    @FindBy(xpath = "//div[@id='admin-menu']//a[contains(@class, 'enumerations')]")
    public WebElement enumerations;

    @FindBy(xpath = "//div[@id='admin-menu']//a[contains(@class, 'settings')]")
    public WebElement settings;

    @FindBy(xpath = "//div[@id='admin-menu']//a[contains(@class, 'ldap-authentification')]")
    public WebElement ldapAuthentification;

    @FindBy(xpath = "//div[@id='admin-menu']//a[contains(@class, 'plugins')]")
    public WebElement modules;

    @FindBy(xpath = "//div[@id='admin-menu']//a[contains(@class, 'info')]")
    public WebElement info;
}
