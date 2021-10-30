package at.study.redmine.api.ui;

import at.study.redmine.property.Property;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
}
