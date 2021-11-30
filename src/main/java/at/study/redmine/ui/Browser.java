package at.study.redmine.ui;

import at.study.redmine.property.Property;
import io.qameta.allure.Attachment;
import lombok.Getter;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

@Getter
public class Browser {

    private WebDriver driver;
    private WebDriverWait wait;

    Browser(){
        this("");
    }

    Browser(String url){
        driver = DriverFactory.getDriver();
        driver.manage().window().maximize();
        int timeout = Property.getIntegerProperty("element.timeout.seconds");
        driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, timeout);
        get(url);
    }

    /**
     * Открыть стартовую страницу
     */
    public void get(){
        get("");
    }

    /**
     * Открыть страницу по относительному пути
     * @param url относительный путь, формата /wiki
     */
    public void get(String url){
        getDriver().get(Property.getStringProperty("ui.host") + url);
    }

    /**
     * Сделать снимок экрана
     * @return снимок экрана в виде массива байт
     */
    @Attachment("Скриншот браузера")
    public byte[] takeScreenshot() {
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }

    public Actions actions(){
        return new Actions(driver);
    }

    /**
     * Выполнить javascript код
     * @param js javascript код
     * @param args параметры
     */
    public void executeJavaScript(String js, Object... args){
        ((JavascriptExecutor)driver).executeScript(js, args);
    }
}
