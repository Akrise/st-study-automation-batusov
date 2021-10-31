package at.tests.selenium_tests;

import at.study.redmine.model.Project;
import at.study.redmine.model.Role;
import at.study.redmine.model.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static at.study.redmine.api.ui.BrowserUtils.getElementsText;

public class ProjectsVisibilityForUserTests extends BaseUITest{

    User user;
    Role role;
    Project publicProject;
    Project privateProject;
    Project privateUsersProject;

    @BeforeMethod
    public void prepareFixtures(){
        role = new Role().create();
        user = new User().create();
        publicProject = new Project().create();
        privateProject = new Project(){{
            setIsPublic(false);
        }}.create();
        privateUsersProject = new Project(){{
            setIsPublic(false);
            addUser(user, Collections.singleton(role));
        }}.create();
        openBrowser();
    }

    @Test
    public void ProjectsVisibilityForUserTest(){
        headerPage.login.click();
        loginPage.login(user);
        //1. Отображается домашняя страница
        Assert.assertEquals(browser.getDriver().getCurrentUrl(), "http://edu-at.dfu.i-teco.ru/");
        //1. Отображается страница "Проекты"
        headerPage.projects.click();
        Assert.assertEquals(projectsPage.pageName.getText(), "Проекты");
        //2. Отображается  публичный проект (№ 1)
        List<String> projectNames = getElementsText(projectsPage.titles);
        Assert.assertTrue(projectNames.contains(publicProject.getName()));
        //3. Не отображается приватный проект (№ 2)
        Assert.assertFalse(projectNames.contains(privateProject.getName()));
        //4. Отображается приватный проект (№ 3)
        Assert.assertTrue(projectNames.contains(privateUsersProject.getName()));
    }
}
