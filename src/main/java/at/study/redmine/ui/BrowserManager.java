package at.study.redmine.ui;

public class BrowserManager {

    private static ThreadLocal<Browser> browser = new ThreadLocal<>();

    public static Browser getBrowser() {
        if (browser.get() == null) {
            browser.set(new Browser());
        }
        return browser.get();
    }

    public static Browser getBrowser(String url) {
        if (browser.get() == null) {
            browser.set(new Browser(url));
        }
        return browser.get();
    }

    public static void removeBrowser() {
        browser.get().getDriver().quit();
        browser.set(null);
    }
}
