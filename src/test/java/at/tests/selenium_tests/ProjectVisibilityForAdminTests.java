package at.tests.selenium_tests;

import at.study.redmine.allure.asserts.AllureAssert;
import at.study.redmine.api.ui.BrowserUtils;
import at.study.redmine.model.Project;
import at.study.redmine.model.User;
import io.qameta.allure.Allure;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static at.study.redmine.api.ui.BrowserUtils.*;

public class ProjectVisibilityForAdminTests extends BaseUITest {

    User admin;
    Project project;

    @BeforeMethod(description = "1. Заведен пользователь в системе с правами администратора\n" +
            "2. Существует приватный проект, не привязанный к пользователю\n" +
            "3. Браузер открыт на домашней странице")
    public void prepareFixtures() {
        admin = new User() {{
            setIsAdmin(true);
        }}.create();
        project = new Project() {{
            setIsPublic(false);
        }}.create();
        openBrowser();
    }

    @Test(description = "Видимость проекта. Приватный проект. Администратор")
    public void adminSeesPrivateProject() {
        Allure.step("Нажать на кнопку \"Вход\"", () -> {
            headerPage.login.click();
        });
        loginPage.login(admin);
        //1. Отображается домашняя страница
        AllureAssert.assertEquals(
                browser.getDriver().getCurrentUrl(),
                "http://edu-at.dfu.i-teco.ru/",
                "Отображается домашняя страница");
        Allure.step("На главной странице нажать \"Проекты\"", () -> {
            headerPage.projects.click();
        });
        //1. Отображается страница "Проекты"
        String expectedPageName = "Проекты";
        AllureAssert.assertEquals(
                projectsPage.pageName.getText(),
                expectedPageName,
                "Отображается страница \"Проекты\"");
        //2. На странице отображается проект из предусловия
        List<String> projectNames = getElementsText(projectsPage.titles);
        AllureAssert.assertTrue(projectNames.contains(project.getName()), "На странице отображается проект из предусловия");
    }
}
