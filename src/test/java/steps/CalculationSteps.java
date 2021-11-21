package steps;

import cucumber.api.java.ru.Если;
import cucumber.api.java.ru.И;
import org.testng.Assert;

public class CalculationSteps {

    private int result;

    @Если("Я суммирую (.*) и (.*)")
    public void sumNumbers(int first, int second) {
        result = first + second;
    }

    @И("Я получаю в результате (.*)")
    public void assertResultEquals(int expectedResult) {
        Assert.assertEquals(result, expectedResult);
    }

    @Если("Я вычитаю {int} из {int}")
    public void difference6and1(int subtrahend, int minuend) {
        result = minuend - subtrahend;
    }

}
