package at.study.redmine.ui;

import at.study.redmine.property.Property;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class BrowserUtils {

    /**
     * Получить лист текстовых значений из листа веб-элементов.
     *
     * @param elements Лист веб-элементов
     * @return
     */
    public static List<String> getElementsText(List<WebElement> elements) {
        return elements.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    /**
     * Проверить наличие веб-элемента на странице(не видимость)
     *
     * @param element веб-элемент для проверки
     * @return
     */
    public static boolean isElementPresent(WebElement element) {
        try {
            BrowserManager.getBrowser().getDriver().manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            element.isDisplayed();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        } finally {
            BrowserManager.getBrowser().getDriver().manage().timeouts().implicitlyWait(Property.getIntegerProperty("element.timeout.seconds"), TimeUnit.SECONDS);
        }
    }
}
