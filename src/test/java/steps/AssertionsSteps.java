package steps;

import at.study.redmine.allure.asserts.AllureAssert;
import at.study.redmine.api.ui.Browser;
import at.study.redmine.api.ui.BrowserManager;
import at.study.redmine.api.ui.BrowserUtils;
import at.study.redmine.api.ui.pages.HeaderPage;
import at.study.redmine.api.ui.pages.Page;
import at.study.redmine.context.Context;
import at.study.redmine.context.Stash;
import at.study.redmine.model.User;
import cucumber.api.java.ru.Но;
import cucumber.api.java.ru.То;

public class AssertionsSteps {

    @То("Открыта домашняя страница")
    public void homepageOpened(){
        AllureAssert.assertEquals(
                BrowserManager.getBrowser().getDriver().getCurrentUrl(),
                "http://edu-at.dfu.i-teco.ru/",
                "Открыта домашняя страница");
    }

    @То("В заголовке отображается текст Вошли как <логин пользователя (.*)>")
    public void loggedAsUser(String stashID){
        AllureAssert.assertEquals(
                Page.getPage(HeaderPage.class).loggedAs.getText(),
                "Вошли как " + Context.getStash().get(stashID, User.class).getLogin(),
                "Проверка отображения текста \"Вошли как <логин пользователя>\"");
    }

    @То("В заголовке отображается элемент Домашняя страница")
    public void homepageIsDisplayed() {
        AllureAssert.assertTrue(Page.getPage(HeaderPage.class).homepage.isDisplayed(), "В заголовке отображается элемент \"Домашняя страница\"");
    }

    @То("В заголовке отображается элемент Моя страница")
    public void myPageIsDisplayed() {
        AllureAssert.assertTrue(Page.getPage(HeaderPage.class).myPage.isDisplayed(), "В заголовке отображается элемент \"Моя страница\"");
    }

    @То("В заголовке отображается элемент Проекты")
    public void projectsIsDisplayed() {
        AllureAssert.assertTrue(Page.getPage(HeaderPage.class).projects.isDisplayed(), "В заголовке отображается элемент \"Проекты\"");
    }

    @То("В заголовке отображается элемент Администрирование")
    public void administrationIsDisplayed() {
        AllureAssert.assertTrue(Page.getPage(HeaderPage.class).administration.isDisplayed(), "В заголовке отображается элемент \"Администрирование\"");
    }

    @То("В заголовке отображается элемент Помощь")
    public void helpIsDisplayed() {
        AllureAssert.assertTrue(Page.getPage(HeaderPage.class).help.isDisplayed(), "В заголовке отображается элемент \"Помощь\"");
    }

    @То("В заголовке отображается элемент Моя учётная запись")
    public void myAccountIsDisplayed() {
        AllureAssert.assertTrue(Page.getPage(HeaderPage.class).myAccount.isDisplayed(), "В заголовке отображается элемент \"Моя учётная запись\"");
    }

    @То("В заголовке отображается элемент Выйти")
    public void exitIsDisplayed() {
        AllureAssert.assertTrue(Page.getPage(HeaderPage.class).exit.isDisplayed(), "В заголовке отображается элемент \"Выйти\"");
    }
    
    @То("В заголовке отображается элемент Поиск")
    public void searchLinkIsDisplayed() {
        AllureAssert.assertTrue(Page.getPage(HeaderPage.class).searchLink.isDisplayed(), "В заголовке отображается элемент \"Поиск\"");
    }
    
    @То("В заголовке отображается поле для поиска")
    public void searchFieldIsDisplayed() {
        AllureAssert.assertTrue(Page.getPage(HeaderPage.class).searchField.isDisplayed(), "В заголовке отображается поле для поиска");
    }

    @То("В заголовке не отображается элемент Войти")
    public void loginNotDisplayed() {
        AllureAssert.assertFalse(BrowserUtils.isElementPresent(Page.getPage(HeaderPage.class).login), "Не отображается элемент \"Войти\"");
    }

    @То("В заголовке не отображается элемент Регистрация")
    public void accountRegisterNotDisplayed() {
        AllureAssert.assertFalse(BrowserUtils.isElementPresent(Page.getPage(HeaderPage.class).accountRegister), "Не отображается элемент \"Регистрация\"");
    }

    @Но("В заголовке не отображается элемент Администрирование")
    public void administrationNotDisplayed() {
        AllureAssert.assertFalse(BrowserUtils.isElementPresent(Page.getPage(HeaderPage.class).administration), "Не отображается элемент \"Администрирование\"");

    }
}
