package at.study.redmine.cucumber;

import at.study.redmine.ui.pages.Page;
import lombok.SneakyThrows;
import org.openqa.selenium.WebElement;
import org.reflections.Reflections;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class PageObjectHelper {

    /**
     * Функция для поиска ВебЕлемента по имени страницы и имени элемента на странице
     * @param pageName Имя страницы
     * @param elementName Имя элемента
     * @return Найденный ВебЕлемент
     */
    public static WebElement findElement(String pageName, String elementName) {
        return getElement(getPage(pageName), elementName);
    }

    //TODO проверить работу
    public static List<WebElement> findElementsList(String pageName, String elementsName){
        return getElementsList(getPage(pageName), elementsName);
    }


    private static <T extends Page> T getPage(String pageName) {
        Set<Class<? extends Page>> allPages = new Reflections("at.study.redmine.ui.pages").getSubTypesOf(Page.class);
        Class<? extends Page> pageObjectClass = allPages.stream()
                .filter((page) -> page.isAnnotationPresent(PageName.class))
                .filter((page) -> page.getAnnotation(PageName.class).value().equals(pageName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Не найдено старницы с аннотацией @PageName = " + pageName));
        return (T) Page.getPage(pageObjectClass);
    }

    @SneakyThrows
    private static WebElement getElement(Page page, String elementName) {
        Field[] allFields = page.getClass().getFields();
        Field elementField = Stream.of(allFields)
                .filter(field -> field.isAnnotationPresent(ElementName.class))
                .filter(field -> field.getAnnotation(ElementName.class).value().equals(elementName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Не найдено элемента с аннотацией @ElementName(\"" + elementName + "\")"));
        elementField.setAccessible(true);
        return (WebElement) elementField.get(page);
    }

    @SneakyThrows
    private static List<WebElement> getElementsList(Page page, String elementsName) {
        Field[] allFields = page.getClass().getDeclaredFields();
        Field elementField = Stream.of(allFields)
                .filter(field -> field.isAnnotationPresent(ElementName.class))
                .filter(field -> field.getAnnotation(ElementName.class).value().equals(elementsName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Не найдено элемента с аннотацией @ElementName(\"" + elementsName  + "\")"));
        elementField.setAccessible(true);
        return (List<WebElement>) elementField.get(page);
    }
}
