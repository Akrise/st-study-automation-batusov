package steps;

import at.study.redmine.allure.asserts.AllureAssert;
import at.study.redmine.cucumber.PageObjectHelper;
import at.study.redmine.model.Project;
import at.study.redmine.ui.BrowserManager;
import at.study.redmine.ui.BrowserUtils;
import at.study.redmine.ui.pages.HeaderPage;
import at.study.redmine.ui.pages.LoginPage;
import at.study.redmine.ui.pages.Page;
import at.study.redmine.context.Context;
import at.study.redmine.model.User;
import cucumber.api.java.ru.И;
import cucumber.api.java.ru.Но;
import cucumber.api.java.ru.То;
import org.openqa.selenium.WebElement;

public class AssertionsSteps {

    @То("Открыта домашняя страница")
    public void homepageOpened() {
        AllureAssert.assertEquals(
                BrowserManager.getBrowser().getDriver().getCurrentUrl(),
                "http://edu-at.dfu.i-teco.ru/",
                "Открыта домашняя страница");
    }

    @То("В заголовке отображается текст Вошли как <логин пользователя (.*)>")
    public void loggedAsUser(String stashID) {
        AllureAssert.assertEquals(
                Page.getPage(HeaderPage.class).loggedAs.getText(),
                "Вошли как " + Context.getStash().get(stashID, User.class).getLogin(),
                "Проверка отображения текста \"Вошли как " + Context.getStash().get(stashID, User.class).getLogin() + "\"");
    }

    @То("В заголовке отображается элемент Домашняя страница")
    public void homepageIsDisplayed() {
        AllureAssert.assertTrue(Page.getPage(HeaderPage.class).homepage.isDisplayed(), "В заголовке отображается элемент \"Домашняя страница\"");
    }


    @Но("На странице авторизации отображается ошибка с текстом (.*)")
    public void authorisationErrorText(String errorDescription) {
        AllureAssert.assertEquals(
                Page.getPage(LoginPage.class).error.getText(),
                errorDescription,
                "Отображается ошибка с текстом \"" + errorDescription + "\"");
    }

    @То("Открыта страница (.*)")
    public void currentUrlIs(String url) {
        AllureAssert.assertEquals(
                BrowserManager.getBrowser().getDriver().getCurrentUrl(),
                url,
                "Отображается страница " + url);

    }

    @То("Отображается страница (.*)")
    public void currentPageName(String pageName) {
        AllureAssert.assertEquals(
                PageObjectHelper.findElement(pageName, "Имя страницы").getText(),
                pageName,
                "Отображается страница " + pageName
        );
    }

    @И("На странице (.*) в множестве (.*) отображается проект (.*)")
    public void listContainsEntity(String pageName, String listName, String stashID) {
        String expectedProjectName = ((Project) Context.getStash().get(stashID)).getName();
        AllureAssert.assertTrue(
                PageObjectHelper.findElementsList(pageName, listName)
                        .stream()
                        .map(WebElement::getText)
                        .anyMatch(projectName -> projectName.equals(expectedProjectName)),
                "На странице отображается проект " + stashID
        );
    }

    @И("На странице (.*) в множестве (.*) отсутствует проект (.*)")
    public void listNotContainsEntity(String pageName, String listName, String stashID) {
        String expectedProjectName = ((Project) Context.getStash().get(stashID)).getName();
        AllureAssert.assertFalse(
                PageObjectHelper.findElementsList(pageName, listName)
                        .stream()
                        .map(WebElement::getText)
                        .anyMatch(projectName -> projectName.equals(expectedProjectName)),
                "На странице отображается проект " + stashID
        );
    }
}
