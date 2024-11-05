package com.framework.config;

import com.google.common.base.Strings;
import io.cucumber.java.Scenario;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class ConfigReader {
  public static void PopulateSettings(final Scenario scenario) throws IOException {
    ConfigReader reader = new ConfigReader();
    reader.ReadProperty(scenario);
  }

  private void ReadProperty(final Scenario scenario) throws IOException {

    String paramEnvironment = System.getProperty("Param_environment");
    String propEnv = "";
    Properties p = new Properties();
    if (Strings.isNullOrEmpty(paramEnvironment)) {paramEnvironment="";}
    switch (paramEnvironment){

      case "dev":{
        propEnv = "ConfigIntegracion.properties";
        break;
      }
      case "qa":{
        propEnv = "ConfigCertificacion.properties";
        break;
      }
      case "stg":{
        propEnv = "ConfigStagingProd.properties";
        break;
      }
      case "prod":{
        propEnv = "ConfigProduccion.properties";
        break;
      }
      default: {
        propEnv = "ConfigCertificacion.properties";
        System.out.println("Opcion por defecto");
      }
    }

    String localPath  = System.getProperty("user.dir")+"/src/main/resources/";
    InputStream inputS = new FileInputStream(localPath + propEnv);
    p.load(inputS);

    Settings.PathLogs = p.getProperty("PathLogs");
    Settings.PathScreenshots = p.getProperty("PathScreenshots");
    Settings.PathDrivers = p.getProperty("PathDrivers");
    Settings.PathConfig = p.getProperty("PathConfig");
    Settings.BrowserType = BrowserType.valueOf(p.getProperty("BrowserType"));

    Settings.UrlDomainSauceDemo = p.getProperty("UrlDomainSauceDemo");
    Settings.UrlLoginPath = p.getProperty("UrlLoginPath");

    Settings.Environment = p.getProperty("Environment");
    Settings.Current_Scenario = scenario;
    Settings.User_Email = System.getProperty("Param_Email");
    Settings.User_Password = System.getProperty("Param_Password");
  }

}
