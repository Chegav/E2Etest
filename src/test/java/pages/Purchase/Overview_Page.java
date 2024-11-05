package pages.Purchase;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import static com.framework.base.DriverContext.*;

import com.framework.base.BasePage;

public class Overview_Page extends BasePage {

    @FindBy(how = How.XPATH, using = "//button[@id='finish']")
    private WebElement btnFinish;
    public void clickFinishButton() {
        WaitForElementVisible(btnFinish); 
        btnFinish.click(); 
        System.out.println("Se hizo clic en el botón 'Finish'.");

        try {
            Thread.sleep(5000); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Complete_Page changePage() {
        return GetInstance(Complete_Page.class); 
    }
}
