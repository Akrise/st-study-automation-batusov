package at.study.redmine.api.ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProjectsPage extends Page{

    @FindBy(xpath = "//div[@id='projects-index']//a[contains(@class, 'project root')]")
    public List<WebElement> titles;

    @FindBy(xpath = "//div[@id='projects-index']//div[@class='root']/div[@class='wiki description']")
    public List<WebElement> descriptions;


}
