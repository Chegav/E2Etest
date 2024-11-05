package com.framework.config;

import com.framework.utilities.LogUtil;

import io.cucumber.java.Scenario;

public class Settings {

  public static LogUtil Logs;
  public static String PathLogs;
  public static String PathScreenshots;
  public static String PathDrivers;
  public static String PathConfig;
  public static BrowserType BrowserType;

  public static String UrlDomainSauceDemo;
  public static String UrlLoginPath;

  public static String Environment;
  public static Scenario Current_Scenario;
  public static String User_Email;

  public static String User_Password;

}
