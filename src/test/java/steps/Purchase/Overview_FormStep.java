package steps.Purchase;

import io.cucumber.java.en.And;
import pages.Purchase.Overview_Page;

import com.framework.base.Base;
import com.framework.base.CurrentPageContext;

public class Overview_FormStep extends Base {

    @And("el usuario da clic en boton finish")
    public void elUsuarioDaClicEnElBotonFinish() {
        CurrentPageContext.setCurrentPage(GetInstance(Overview_Page.class));
        CurrentPageContext.getCurrentPage().As(Overview_Page.class).clickFinishButton();
        CurrentPageContext.setCurrentPage(CurrentPageContext.getCurrentPage().As(Overview_Page.class).changePage());

    }

}
