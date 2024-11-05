package steps.Purchase;

import com.framework.base.Base;
import com.framework.base.CurrentPageContext;
import com.framework.base.DriverContext;
import com.framework.config.Settings;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.Purchase.DashB_Page;
import pages.Purchase.FormLogin_Page;

import org.testng.Assert;

import java.util.List;
import java.util.Map;

public class Login_FormSteps extends Base {

  @When("Se llena los campos del formulario login SauceDemo")
  public void seLlenaLosCamposDelFormularioLogin(List<Map<String, String>> dataTable) {
    Map<String, String> row = dataTable.get(0);
    String tipoPersona = row.get("TipoPersona");
    System.out.println("Tipo de Persona: " + tipoPersona);
    CurrentPageContext.setCurrentPage(GetInstance(FormLogin_Page.class));
    String email = row.get("Email");
    String password = row.get("Password");
    System.out.println("Fill form with email: " + email + " and password: " + password);

    CurrentPageContext.getCurrentPage().As(FormLogin_Page.class).fillForm1(email, password);
  }

  @When("Se presiona el boton login")
  public void sePresionaElBotonIngresar() {
    System.out.println("Se presiona el boton login");
    CurrentPageContext.getCurrentPage().As(FormLogin_Page.class).pressButtonSignIn();
  }

  @Given("Se carga la pantalla Login de SauceDemo")
  public void seCargaLaPantallaLogindeSauceDemo() {
    DriverContext.GoToUrl(Settings.UrlDomainSauceDemo + Settings.UrlLoginPath);
  }

  @Then("Se visualiza el mensaje {string} en la pantalla de login")
  public void Se_visualiza_el_mensaje_en_la_pantalla_de_login(String s) {
    System.out.println("Se visualiza el mensaje " + s);
    boolean isValid = CurrentPageContext.getCurrentPage().As(DashB_Page.class).ValidateText(s);
    Assert.assertTrue(isValid, "No se muestra mensaje");
  }

}
