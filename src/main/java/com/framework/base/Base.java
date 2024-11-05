package com.framework.base;

import com.framework.controls.api.ControlFactory;

public class Base {

  public <TPage extends BasePage> TPage GetInstance(Class<TPage> page) {
    //Custom control page factory initialization
    System.out.println("Get instance " + page.getName());
    Object obj = ControlFactory.initElements(LocalDriverContext.getRemoteWebDriver(), page);
    return page.cast(obj);
  }
}
