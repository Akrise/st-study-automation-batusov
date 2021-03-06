package runner;

import at.study.redmine.ui.BrowserManager;
import at.study.redmine.context.Context;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.PickleEventWrapper;
import org.testng.ITest;
import org.testng.annotations.*;

import java.lang.reflect.Method;

@CucumberOptions(
        plugin = {
                "pretty",
                "io.qameta.allure.cucumber4jvm.AllureCucumber4Jvm",
                "json:target/cucumber.json"
        },
        glue = {"steps"},
        features = "src/test/java/resources/features",
        tags = {"@ui"}
)
public class TestRunner extends AbstractTestNGCucumberTests implements ITest {
    @Override
    public String getTestName() {
        return null;
    }

    @BeforeClass(alwaysRun = true)
    @Override
    public void setUpClass() throws Exception {
        super.setUpClass();
    }

    @Override
    public void runScenario(PickleEventWrapper pickleWrapper, CucumberFeatureWrapper featureWrapper) throws Throwable {
        super.runScenario(pickleWrapper, featureWrapper);
    }

    @DataProvider(parallel = true)
    @Override
    public Object[][] scenarios() {
        System.out.println("Total scenarious count: " + super.scenarios().length);
        return super.scenarios();
    }

    @AfterClass(alwaysRun = true)
    @Override
    public void tearDownClass() throws Exception {
        super.tearDownClass();
    }

    @BeforeMethod
    public void beforeMethod(Method method, Object[] testData) {

    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod(Method method, Object[] testData) {
        Context.clearStash();
        BrowserManager.removeBrowser();
    }

}
