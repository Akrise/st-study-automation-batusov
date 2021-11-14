package at.study.redmine.api.ui;

import at.study.redmine.api.ui.Browser;

public class BrowserManager {

    private static Browser browser;

    public static Browser getBrowser() {
        if (browser == null) {
            browser = new Browser();
        }
        return browser;
    }

    public static Browser getBrowser(String url) {
        if (browser == null) {
            browser = new Browser(url);
        }
        return browser;
    }

    public static void removeBrowser() {
        //TODO поменять на quit
        browser.getDriver().close();
        browser = null;
    }
}
