package pages.Purchase;

import com.framework.base.BasePage;
import com.framework.base.DriverContext;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Checkout_Page extends BasePage {
    @FindBy(id = "first-name")
    private WebElement inputFirstName;
    @FindBy(id = "last-name")
    private WebElement inputLastName;
    @FindBy(id = "postal-code")
    private WebElement inputPostalCode;
    @FindBy(id = "continue")
    private WebElement continueButton;

   
    public void fillForm2(String Nombre, String Apellido, String CodigoPostal) {
      System.out.println("Send Keys to Input First Name");
      for (char c : Nombre.toCharArray()) {
          inputFirstName.sendKeys(Character.toString(c));
      }
      
      System.out.println("Send Keys to Input Last Name");
      for (char c : Apellido.toCharArray()) {
          inputLastName.sendKeys(Character.toString(c));
      }
      
      System.out.println("Send Keys to Input Postal Code");
      for (char c : CodigoPostal.toCharArray()) {
          inputPostalCode.sendKeys(Character.toString(c));
      }
  }
  
    public void hacerClickEnContinuar() {
        DriverContext.WaitForElementBeClickable(continueButton);
        continueButton.click();
    }
}

