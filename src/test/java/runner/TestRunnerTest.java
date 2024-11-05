package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

@CucumberOptions(features = { "src/test/java/features/" }, glue = { "steps" },
    // dryRun = true,
    plugin = { "json:target/cucumber-reports/report.json",
        "html:target/cucumber-reports/report.html" }, tags = "@SauceDemo")

public class TestRunnerTest extends AbstractTestNGCucumberTests {

  @Override
  @DataProvider(parallel = true)
  public Object[][] scenarios() {
    return super.scenarios();
  }

  @BeforeClass(alwaysRun = true)
  public void setUpClass() {
    System.out.println("Before class ====> init at "
        + new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()));

    System.out.println("Before class ====> finish at "
        + new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()));
  }
}