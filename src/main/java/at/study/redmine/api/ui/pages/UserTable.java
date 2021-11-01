package at.study.redmine.api.ui.pages;

import at.study.redmine.api.ui.BrowserManager;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class UserTable extends Page {

    @FindBy(xpath = "//div[@id='content']//a[@class='icon icon-add']")
    public WebElement newUser;

    @FindBy(xpath = "//table[@class = 'list users']//td[@class='username']")
    public List<WebElement> users;

    @FindBy(xpath = "//table[@class = 'list users']//td[@class='firstname']")
    public List<WebElement> firstNames;

    @FindBy(xpath = "//table[@class = 'list users']//td[@class='lastname']")
    public List<WebElement> lastNames;


}
