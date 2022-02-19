package at.study.redmine.allure.asserts;

import io.qameta.allure.Step;
import org.testng.Assert;

public class AllureAssert {

    @Step("Проверка равенства: {2}")
    public static void assertEquals(Object actual, Object expected, String message) {
        Assert.assertEquals(actual, expected, message);
    }

    @Step("Проверка равенства:")
    public static void assertEquals(Object actual, Object expected) {
        Assert.assertEquals(actual, expected);
    }

    @Step("Проверка выполнения условия: {1}")
    public static void assertTrue(Boolean actual, String message) {
        Assert.assertTrue(actual);
    }

    @Step("Проверка невыполнения условия: {1}")
    public static void assertFalse(Boolean actual, String message) {
        Assert.assertFalse(actual);
    }

    @Step("Проверка условия NotNull")
    public static void assertNotNull(Object actual) {
        Assert.assertNotNull(actual);
    }
}
