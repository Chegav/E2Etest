package steps;

import com.framework.base.DriverContext;
import com.framework.base.FrameworkInitialize;
import com.framework.config.ConfigReader;
import com.framework.config.Settings;
import com.framework.utilities.CommonUtil;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TestInitialize extends FrameworkInitialize {

  @Before
  public void Initialize(Scenario scenario) throws IOException {
    ConfigReader.PopulateSettings(scenario);
    InitializeBrowser(Settings.BrowserType, scenario);
  }

  @After
  public void TearDown(Scenario scenario) {
    System.out.println("Finish at " + new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()));
    if (scenario.isFailed()) {
      CommonUtil.embedScreenshot(scenario);
      //CommonUtil.takeScreenshot(scenario);
    }
    DriverContext.QuitDriver();
  }
}
