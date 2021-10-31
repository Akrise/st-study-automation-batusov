package at.tests.selenium_tests;

import at.study.redmine.api.ui.BrowserUtils;
import at.study.redmine.model.Project;
import at.study.redmine.model.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static at.study.redmine.api.ui.BrowserUtils.*;

public class ProjectVisibilityForAdminTests extends BaseUITest {

    User admin;
    Project project;

    @BeforeMethod
    public void prepareFixtures() {
        admin = new User() {{
            setIsAdmin(true);
        }}.create();
        project = new Project() {{
            setIsPublic(false);
        }}.create();
        openBrowser();
    }

    @Test
    public void adminSeesPrivateProject() {
        headerPage.login.click();
        loginPage.login(admin);
        //1. Отображается домашняя страница
        Assert.assertEquals(browser.getDriver().getCurrentUrl(), "http://edu-at.dfu.i-teco.ru/");
        headerPage.projects.click();
        //1. Отображается страница "Проекты"
        String expectedPageName = "Проекты";
        Assert.assertEquals(projectsPage.pageName.getText(), expectedPageName);
        //2. На странице отображается проект из предусловия
        List<String> projectNames = getElementsText(projectsPage.titles);
        Assert.assertTrue(projectNames.contains(project.getName()));
    }

    @Test
    public void projectsVisibilityForUser() {

    }
}
