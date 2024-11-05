package steps.Purchase;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import pages.Purchase.Checkout_Page;

import com.framework.base.Base;
import com.framework.base.CurrentPageContext;

public class Checkout_FormStep extends Base {

  @When("el usuario llena el formulario de checkout con nombre {string}, apellido {string}, y código postal {string}")
  public void seLlenaLosCamposDelFormularioLoginCreditoConsumo(String Nombre, String Apellido, String CodigoPostal) {
    CurrentPageContext.setCurrentPage(GetInstance(Checkout_Page.class));
    System.out.println("Fill form with Name: " + Nombre + ", LastName: " + Apellido + ", and Postal Code: " + CodigoPostal);
    CurrentPageContext.getCurrentPage().As(Checkout_Page.class).fillForm2(Nombre, Apellido, CodigoPostal);
  }

  @And("el usuario hace clic en el botón de continuar")
  public void elUsuarioHaceClicEnElBotonContinuar() {
    CurrentPageContext.getCurrentPage().As(Checkout_Page.class).hacerClickEnContinuar();
  }
}
