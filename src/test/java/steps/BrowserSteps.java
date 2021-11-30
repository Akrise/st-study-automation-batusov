package steps;

import at.study.redmine.ui.BrowserManager;
import cucumber.api.java.ru.И;

public class BrowserSteps {

    @И("Браузер открыт на домашней странице")
    public void openBrowser(){
        BrowserManager.getBrowser();
    }


    @И("Браузер открыт на странице (.*)")
    public void openBrowser(String url){
        BrowserManager.getBrowser(url);
    }
}
