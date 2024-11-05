package steps.Purchase;

import org.testng.Assert;

import com.framework.base.CurrentPageContext;

import io.cucumber.java.en.Then;
import pages.Purchase.Complete_Page;

public class Complete_Step {

    @Then("Se visualiza el texto de la forma correcta")
    public void seVisualizaElTextoDeLaFormaCorrecta() {
        Complete_Page completePage = CurrentPageContext.getCurrentPage().As(Complete_Page.class);
            boolean isMessageDisplayed = completePage.isCompleteMessageDisplayed("Thank you for your order!");
        Assert.assertTrue(isMessageDisplayed, "El mensaje de completado no se muestra correctamente.");
        System.out.println("Se visualiza el mensaje de completado correctamente.");
    }
    }

