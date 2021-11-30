package at.study.redmine.ui.pages;

import at.study.redmine.cucumber.ElementName;
import at.study.redmine.cucumber.PageName;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@PageName("Проекты")
public class ProjectsPage extends Page{

    @ElementName("Все названия проектов")
    @FindBy(xpath = "//div[@id='projects-index']//a[contains(@class, 'project root')]")
    public List<WebElement> titles;

    @ElementName("Все описания проектов")
    @FindBy(xpath = "//div[@id='projects-index']//div[@class='root']/div[@class='wiki description']")
    public List<WebElement> descriptions;


}
