package com.framework.base;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class DriverContext {
  public static void GoToUrl(String url) {
    try{
      Thread.sleep(5000);
    }
    catch (Exception e){
      System.out.println("Error sleep: " + e);
    }
    System.out.println("Go to url " + url);
    LocalDriverContext.getRemoteWebDriver().get(url);
  }

  public static void QuitDriver(){
    LocalDriverContext.getRemoteWebDriver().quit();
  }

  public static void WaitForPageToLoad(){
    WebDriverWait wait= new WebDriverWait(LocalDriverContext.getRemoteWebDriver(), Duration.ofSeconds(60));
    RemoteWebDriver jsExecutor = LocalDriverContext.getRemoteWebDriver();

    ExpectedCondition<Boolean> jsLoad = webDriver ->  (LocalDriverContext.getRemoteWebDriver())
        .executeScript("return document.readyState").toString().equals("complete");

    //Get JS Ready
    boolean jsReady = jsExecutor.executeScript("return document.readyState").toString().equals("complete");

    if(!jsReady)
      wait.until(jsLoad);
    else
      //Settings.Logs.Write("Page is ready !");
      System.out.println("Page is ready !");

  }

  public  static  void WaitForElementVisible(final WebElement elementFindBy){
    System.out.println("Wait for element visible");
    WebDriverWait wait= new WebDriverWait(LocalDriverContext.getRemoteWebDriver(), Duration.ofSeconds(30));
    wait.until(ExpectedConditions.visibilityOf(elementFindBy));
  }

  public  static  boolean WaitForTextVisibleOnElement(final WebElement elementFindBy, String TextValidate){
    WebDriverWait wait= new WebDriverWait(LocalDriverContext.getRemoteWebDriver(), Duration.ofSeconds(10));
    return wait.until(ExpectedConditions.textToBePresentInElement(elementFindBy, TextValidate));
  }

  public  static  void WaitForElementNotVisible(final WebElement elementFindBy){
    System.out.println("Wait for element not visible");
    WebDriverWait wait= new WebDriverWait(LocalDriverContext.getRemoteWebDriver(), Duration.ofSeconds(60));
    wait.until(ExpectedConditions.invisibilityOf(elementFindBy));
  }
  public  static  WebElement LookingForElementVisible(final WebElement elementFindBy){
    WebDriverWait wait= new WebDriverWait(LocalDriverContext.getRemoteWebDriver(), Duration.ofSeconds(10));
    return wait.until(ExpectedConditions.visibilityOf(elementFindBy));
  }

  public  static  void WaitForElementBeClickable(final WebElement elementFindBy){
    WebDriverWait wait= new WebDriverWait(LocalDriverContext.getRemoteWebDriver(), Duration.ofSeconds(10));
    wait.until(ExpectedConditions.elementToBeClickable(elementFindBy));
  }

  public static void WaitForElementTextVisible(final WebElement elementFindBy, String text){
    WebDriverWait wait= new WebDriverWait(LocalDriverContext.getRemoteWebDriver(), Duration.ofSeconds(10));
    wait.until(ExpectedConditions.textToBePresentInElement(elementFindBy, text));
  }

  public static void WaitForElementValueTextVisible(final WebElement elementFindBy, String text){
    WebDriverWait wait= new WebDriverWait(LocalDriverContext.getRemoteWebDriver(), Duration.ofSeconds(10));
    wait.until(ExpectedConditions.textToBePresentInElementValue(elementFindBy, text));
  }

  public static void WaitUntilTextDisplayed(final By element, String text){
    WebDriverWait wait = new WebDriverWait(LocalDriverContext.getRemoteWebDriver(),Duration.ofSeconds(10));
    wait.until(textDisplayed(element, text));
  }

  private static ExpectedCondition<Boolean> textDisplayed (final By elementFindBy, final String text){
    return webDriver -> webDriver.findElement(elementFindBy).getText().contains(text);
  }

  public static WebElement FindElementByID(final String elementID) {
    return LocalDriverContext.getRemoteWebDriver().findElement(By.id(elementID));
  }

    public static WebElement FindElementByXPath(final String xpath) {
    return LocalDriverContext.getRemoteWebDriver().findElement(By.xpath(xpath));
  }

  public static void switchTo(){
    LocalDriverContext.getRemoteWebDriver().switchTo().activeElement();
  }
  public static void WaitElementEnabled(final By elementFindBy){
    WebDriverWait wait = new WebDriverWait(LocalDriverContext.getRemoteWebDriver(),Duration.ofSeconds(10));
    wait.until(webDriver -> webDriver.findElement(elementFindBy).isEnabled());
  }

  public  static WebElement WaitForElementVisibleReturn(final WebElement elementFindBy){

    WebDriverWait wait= new WebDriverWait(LocalDriverContext.getRemoteWebDriver(), Duration.ofSeconds(10));
    try {
      return wait.until(ExpectedConditions.visibilityOf(elementFindBy));
    }catch(Exception e){
      System.out.println("Don't found element "+e.getMessage());
      return null;
    }

  }

  public  static  void ScrollToElement(final WebElement elementFindBy) throws InterruptedException {
    JavascriptExecutor js = LocalDriverContext.getRemoteWebDriver();
    js.executeScript("arguments[0].scrollIntoView();", elementFindBy);
    Thread.sleep(500);
    new Actions(LocalDriverContext.getRemoteWebDriver())
        .moveToElement(elementFindBy)
        .perform();
    Thread.sleep(500);
  }

  public  static  void ScrollToEndDocument() throws InterruptedException {
    JavascriptExecutor js = LocalDriverContext.getRemoteWebDriver();
    js.executeScript("window.scrollBy(0,3000);");
    Thread.sleep(500);
  }

  public static void clicksOn(final WebElement elementFindBy) {
    ((JavascriptExecutor)LocalDriverContext.getRemoteWebDriver()).executeScript(
        "arguments[0].scrollIntoView();arguments[0].click()",
        elementFindBy);
  }

  public static String getValue(final WebElement elementFindBy) {
    try
    {
      JavascriptExecutor jse = ((JavascriptExecutor)LocalDriverContext.getRemoteWebDriver());
      String value =(String)jse.executeScript("return arguments[0].value", elementFindBy);
      return value;
    }
    catch(Exception ex)
    {
      return "";
    }
  }
  
  public static void fillsField(final WebElement elementFindBy, String value) {
    ((JavascriptExecutor)LocalDriverContext.getRemoteWebDriver()).executeScript(
        "arguments[0].scrollIntoView();arguments[0].value= arguments[1];",
        elementFindBy, value);
  }

  public static boolean isAbleToSee(final WebElement elementFindBy)  {
    try{
      return elementFindBy.isDisplayed();
    } catch (StaleElementReferenceException | NoSuchElementException | TimeoutException e) {
      return false;
    }
  }

  public static void refreshPage() {
    LocalDriverContext.getRemoteWebDriver().navigate().refresh();
  }

  public static void ClearInputElement(final WebElement elementFindBy){
    elementFindBy.clear();
    elementFindBy.sendKeys(Keys.CONTROL, "A", Keys.DELETE);
  }
}
