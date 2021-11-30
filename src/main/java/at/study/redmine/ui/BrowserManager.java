package at.study.redmine.ui;

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
        browser.getDriver().quit();
        browser = null;
    }
}
