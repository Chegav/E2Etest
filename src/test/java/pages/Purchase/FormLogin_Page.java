package pages.Purchase;

import com.framework.base.BasePage;

import static com.framework.base.DriverContext.*;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class FormLogin_Page extends BasePage {

  @FindBy(how = How.ID, using = "user-name")
  private WebElement inputUsername;
  @FindBy(how = How.ID, using = "password")
  private WebElement inputPassword;
  @FindBy(how = How.ID, using = "login-button")
  private WebElement btnLogin;

  public void fillForm1(String email_user, String password_user) {
    System.out.println("Wait For InputEmail Visible");
    System.out.println("Send Keys to Input Email");
    for (char c : email_user.toCharArray()) {
      inputUsername.sendKeys(Character.toString(c));
    }
    System.out.println("Send Tab key");
    inputUsername.sendKeys(Keys.TAB);
    System.out.println("Wait For InputPassword Visible");
    System.out.println("Send Keys to Input Password");
    for (char c : password_user.toCharArray()) {
      inputPassword.sendKeys(Character.toString(c));
    }
  }

  public void pressButtonSignIn() {
    try {
      if (isAbleToSee(btnLogin)) {
        ScrollToElement(btnLogin);
        btnLogin.sendKeys(Keys.ENTER);
      }
    } catch (Exception ex) {
      return;
    }
  }

  public DashB_Page changePage() {
    return GetInstance(DashB_Page.class);
  }

}
