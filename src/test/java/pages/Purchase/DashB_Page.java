
package pages.Purchase;

import com.framework.base.BasePage;
import com.framework.base.DriverContext;
import com.framework.base.LocalDriverContext;

import com.google.common.base.Strings;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.framework.base.DriverContext.*;

import java.util.List;

public class DashB_Page extends BasePage {

  @FindBy(how = How.ID, using = "inventory_container")
  private WebElement titleProducts;
  @FindBy(css = "button[data-test='checkout']")
  private WebElement botonCheckout;
  @FindBy(css = "input[name='identification']")
  private WebElement inputCedula;
  @FindBy(css = "button[type='submit']")
  private WebElement botonIngresar;
  @FindBy(xpath = "//button[text()='Consultar']")
  private WebElement btnConsultar;

  public boolean isDashboardLoaded() {
    System.out.println("Validando si se cargó el dashboard..");
    WaitForElementVisible(titleProducts);
    return isAbleToSee(titleProducts);
  }

  public List<WebElement> obtenerElementosDeProductos() {
    return LocalDriverContext.getRemoteWebDriver().findElements(By.cssSelector("[data-test='inventory-item']"));
  }

  public void agregarProductoAlCarrito(WebElement producto) {
    WebElement addToCartButton = producto.findElement(By.cssSelector("[data-test^='add-to-cart-']"));
    addToCartButton.click();
  }

  public void irAlCarrito() {
    WebElement carritoButton = LocalDriverContext.getRemoteWebDriver().findElement(By.id("shopping_cart_container"));
    carritoButton.click();
  }

  public List<WebElement> obtenerElementosDelCarrito() {
    return LocalDriverContext.getRemoteWebDriver().findElements(By.cssSelector(".cart_item"));
  }

  public void observarProductosEnElCarrito() {
    List<WebElement> productos = obtenerElementosDelCarrito();
    System.out.println("Productos en el carrito:");
    for (WebElement producto : productos) {
      System.out.println("- " + producto.getText());
    }

  }

  public void hacerClickEnCheckout() {
    DriverContext.WaitForElementBeClickable(botonCheckout);
    DriverContext.clicksOn(botonCheckout);
  }

  public void presionarBotonIngresar() {
    botonIngresar.click();
  }

  public boolean returnButtonStatus() {
    return isAbleToSee(btnConsultar);
  }

  public void presionarBotonConsultar() {
    System.out.println("Se presionó el botón consultar");
    try {
      if (returnButtonStatus()) {
        clicksOn(btnConsultar);
        Thread.sleep(8000);

      }
    } catch (Exception ex) {
      System.out.println("Error en el botón consultar " + ex);
    }
  }

  public boolean ValidateText(String s) {
    try {
      if (!Strings.isNullOrEmpty(s)) {
        WebElement errorMessage = FindElementByXPath("//*[normalize-space()='" + s + "']");
        WaitForElementVisible(errorMessage);
        return true;
      }
    } catch (Exception ex) {
      System.out.println(ex);
      return false;
    }
    return false;
  }

  public void fillCedulaForm(String cedula) {
    System.out.println("Wait For InputCedula Visible");
    WaitForElementVisible(inputCedula); // Asegúrate de que 'inputCedula' sea el locator correcto para el campo de la
                                        // cédula.

    System.out.println("Wait For DivLoading Not Visible");
    // Agregar lógica si hay algún div de carga que deba desaparecer, si es
    // necesario

    System.out.println("Send Keys to Input Cedula");
    for (char c : cedula.toCharArray()) {
      inputCedula.sendKeys(Character.toString(c)); // Ingresa la cédula caracter por caracter
    }

    System.out.println("Send Tab key to move to next field");
    inputCedula.sendKeys(Keys.TAB); // Envía la tecla TAB para moverse al siguiente campo o desencadenar validación
  }

}