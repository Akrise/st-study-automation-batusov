package steps;

import at.study.redmine.allure.asserts.AllureAssert;
import at.study.redmine.cucumber.PageObjectHelper;
import at.study.redmine.ui.BrowserUtils;
import at.study.redmine.ui.pages.HeaderPage;
import at.study.redmine.ui.pages.Page;
import cucumber.api.java.ru.И;
import cucumber.api.java.ru.То;

public class UISteps {

    @И("Нажать на кнопку Войти")
    public void pressLoginButton() {
        Page.getPage(HeaderPage.class).login.click();
    }

    @И("На странице (.*) присутствует элемент (.*)")
    public void elementIsDisplayedOnThePage(String pageName, String elementName) {
        AllureAssert.assertTrue(PageObjectHelper.findElement(pageName, elementName).isDisplayed(), "На странице " + pageName + " отображается элемент " + elementName);
    }

    @И("На странице (.*) НЕ отображается элемент (.*)")
    public void elementIsNotDisplayedOnThePage(String pageName, String elementName) {
        AllureAssert.assertFalse(BrowserUtils.isElementPresent(PageObjectHelper.findElement(pageName, elementName)), "На странице " + pageName + " НЕ отображается элемент " + elementName);
    }

    @И("На странице (.*) нажать на кнопку (.*)")
    public void clickButtonOnThePage(String pageName, String elementName) {
        PageObjectHelper.findElement(pageName, elementName).click();
    }

    @И("На странице (.*) ввести в поле (.*) текст (.*)")
    public void inputTextInField(String pageName, String fieldName, String text) {
        PageObjectHelper.findElement(pageName, fieldName).sendKeys(text);
    }

}
