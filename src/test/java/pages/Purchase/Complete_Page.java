package pages.Purchase;

import com.framework.base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.framework.base.DriverContext.*;

public class Complete_Page extends BasePage {
    @FindBy(how = How.XPATH, using = "//h2[@data-test='complete-header']")
    private WebElement completeHeader;

    public boolean isCompleteMessageDisplayed(String expectedMessage) {
        WaitForElementVisible(completeHeader);
        String actualMessage = completeHeader.getText();
        System.out.println("Mensaje actual: " + actualMessage);
        return actualMessage.equals(expectedMessage);
    }

    public boolean isDashboardLoaded() {
        System.out.println("Validando si se cargó el dashboard..");
        WaitForElementVisible(completeHeader);
        return isAbleToSee(completeHeader);
    }
}    
