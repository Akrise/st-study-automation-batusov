package at.study.redmine.ui.pages;

import at.study.redmine.cucumber.ElementName;
import at.study.redmine.cucumber.PageName;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.AfterMethod;

import java.util.List;

@PageName("Пользователи")
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class UserTable extends Page {

    @ElementName("Новый пользователь")
    @FindBy(xpath = "//div[@id='content']//a[@class='icon icon-add']")
    public WebElement newUser;

    @ElementName("Столбец Пользователь")
    @FindBy(xpath = "//table[@class = 'list users']//td[@class='username']")
    public List<WebElement> users;

    @ElementName("Столбец Имя")
    @FindBy(xpath = "//table[@class = 'list users']//td[@class='firstname']")
    public List<WebElement> firstNames;

    @ElementName("Столбец Фамилия")
    @FindBy(xpath = "//table[@class = 'list users']//td[@class='lastname']")
    public List<WebElement> lastNames;

}
