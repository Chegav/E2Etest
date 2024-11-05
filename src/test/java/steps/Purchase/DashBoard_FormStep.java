package steps.Purchase;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.Purchase.DashB_Page;
import pages.Purchase.FormLogin_Page;

import java.util.Random;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.framework.base.Base;
import com.framework.base.CurrentPageContext;

public class DashBoard_FormStep extends Base {
    @When("Se agregan dos productos al carrito al azar")
    public void se_agregan_dos_productos_al_carrito_al_azar() {
        
        DashB_Page dashPage = CurrentPageContext.getCurrentPage().As(DashB_Page.class);
      
        List<WebElement> productos = dashPage.obtenerElementosDeProductos();
        if (productos.size() < 2) {
            throw new IllegalStateException("No hay suficientes productos para seleccionar.");
        }
        Random random = new Random();
        int firstIndex = random.nextInt(productos.size());
        int secondIndex;
        do {
            secondIndex = random.nextInt(productos.size());
        } while (secondIndex == firstIndex);
        dashPage.agregarProductoAlCarrito(productos.get(firstIndex));
        dashPage.agregarProductoAlCarrito(productos.get(secondIndex));
    }

    @When("El usuario va al carrito")
    public void el_usuario_va_al_carrito() {
        DashB_Page cartPage = CurrentPageContext.getCurrentPage().As(DashB_Page.class);
        cartPage.irAlCarrito();
    }
 @Then("Se visualiza el dashboard de forma correcta")
  public void seVisualizaElDashboardDeFormaCorrecta() {
   CurrentPageContext.setCurrentPage(CurrentPageContext.getCurrentPage().As(FormLogin_Page.class).changePage());
    Assert.assertTrue(CurrentPageContext.getCurrentPage().As(DashB_Page.class).isDashboardLoaded(),
        "No cargo la pantalla de dashboard");
        System.out.println("Se visualiza el dashboard de forma correcta");

  }
    @Then("El usuario observa los productos en el carrito")
    public void el_usuario_observa_los_productos_en_el_carrito() {
        DashB_Page cartPage = CurrentPageContext.getCurrentPage().As(DashB_Page.class);
        cartPage.observarProductosEnElCarrito();
    }

    @When("se completa el formulario de compra")
    public void seCompletaElFormularioDeCompra() {
        DashB_Page cartPage = CurrentPageContext.getCurrentPage().As(DashB_Page.class);
        cartPage.hacerClickEnCheckout();
    }

}
