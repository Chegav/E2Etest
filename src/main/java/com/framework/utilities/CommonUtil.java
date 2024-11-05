package com.framework.utilities;

import com.framework.base.LocalDriverContext;
import com.framework.config.Settings;

import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class CommonUtil {

  public static void waitSleep(int i) {
    try {
      Thread.sleep(i);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public static void takeScreenshot(Scenario scenario) {

    File scrFile = ((TakesScreenshot) LocalDriverContext.getRemoteWebDriver()).getScreenshotAs(OutputType.FILE);
    //String NewFileNamePath = null;
    File directory = new File(".");

    ZoneId zid = ZoneId.of("America/Lima");
    ZonedDateTime date = ZonedDateTime.now(zid);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    String fileNameFormat = date.format(formatter);
    String classAndMethodName = fileNameFormat + "_" + scenario.getName().replace(" ","_");

    try {
      String NewFileNamePath = directory.getCanonicalPath() + File.separator + Settings.PathConfig + File.separator + Settings.PathScreenshots + classAndMethodName + ".png";
      FileUtils.copyFile(scrFile, new File(NewFileNamePath));
    } catch (IOException e1) {
      e1.printStackTrace();
    }

  }

  public static void embedScreenshot(Scenario scenario) {
    try {
      if (LocalDriverContext.getRemoteWebDriver() != null) {
        final byte[] byteFile = ((TakesScreenshot) LocalDriverContext.getRemoteWebDriver()).getScreenshotAs(OutputType.BYTES);
        scenario.attach(byteFile, "image/png", scenario.getName());
      }
      else {
        throw new NullPointerException("El elemento driver es nulo. Clase: embedScreenshot");
      }
    } catch (Exception e1) {
      e1.printStackTrace();
    }
  }

    public static SSLContext getSSLContext() {
    try {
    SSLContext sslContext = SSLContext.getInstance("SSL");

    // Configura un TrustManager que confía en todo
      sslContext.init(null, new TrustManager[] { new X509TrustManager() {
          public X509Certificate[] getAcceptedIssuers() { return null; }
          public void checkClientTrusted(X509Certificate[] certs, String authType) {}
          public void checkServerTrusted(X509Certificate[] certs, String authType) {}
      }}, new java.security.SecureRandom());

      return sslContext;
    } catch (KeyManagementException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    catch (NoSuchAlgorithmException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return null;

  }

}
