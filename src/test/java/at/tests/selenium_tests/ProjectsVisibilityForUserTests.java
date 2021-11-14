package at.tests.selenium_tests;

import at.study.redmine.allure.asserts.AllureAssert;
import at.study.redmine.model.Project;
import at.study.redmine.model.Role;
import at.study.redmine.model.User;
import io.qameta.allure.Allure;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static at.study.redmine.api.ui.BrowserUtils.getElementsText;

public class ProjectsVisibilityForUserTests extends BaseUITest {

    User user;
    Role role;
    Project publicProject;
    Project privateProject;
    Project privateUsersProject;

    @BeforeMethod(description = "1. Заведен пользователь в системе.\n" +
            "2. Пользователь подтвержден администратором и не заблокирован\n" +
            "3. В системе заведена Роль пользователя с правами на просмотр задач\n" +
            "4. В системе заведен публичный проект (№ 1)\n" +
            "5. В системе заведен приватный проект (№ 2)\n" +
            "6. В системе заведен приватный проект (№ 3)\n" +
            "7. У пользователя нет доступа к проектам №1, №2\n" +
            "8. У пользователя есть доступ к проекту №3 c ролью из п.3 предусловия\n" +
            "9. Браузер открыт на домашней странице"    )
    public void prepareFixtures() {
        role = new Role().create();
        user = new User().create();
        publicProject = new Project().create();
        privateProject = new Project() {{
            setIsPublic(false);
        }}.create();
        privateUsersProject = new Project() {{
            setIsPublic(false);
            addUser(user, Collections.singleton(role));
        }}.create();
        openBrowser();
    }

    @Test(description = "Видимость проектов. Пользователь")
    public void ProjectsVisibilityForUserTest() {
        Allure.step("Нажать на кнопку \"Вход\"", () -> {
            headerPage.login.click();
        });
        loginPage.login(user);
        //1. Отображается домашняя страница
        AllureAssert.assertEquals(browser.getDriver().getCurrentUrl(), "http://edu-at.dfu.i-teco.ru/", "Отображается домашняя страница");
        //1. Отображается страница "Проекты"
        Allure.step("На главной странице нажать \"Проекты\"", () -> {
            headerPage.projects.click();
        });
        AllureAssert.assertEquals(projectsPage.pageName.getText(), "Проекты", "Отображается страница \"Проекты\"");
        //2. Отображается  публичный проект (№ 1)
        List<String> projectNames = getElementsText(projectsPage.titles);
        AllureAssert.assertTrue(projectNames.contains(publicProject.getName()), "На странице отображается  публичный проект (№ 1");
        //3. Не отображается приватный проект (№ 2)
        AllureAssert.assertFalse(projectNames.contains(privateProject.getName()), "На странице отображается  приватный проект (№ 2");
        //4. Отображается приватный проект (№ 3)
        AllureAssert.assertTrue(projectNames.contains(privateUsersProject.getName()), "На странице отображается  приватный проект (№ 3");
    }
}
