package com.framework.controls.internals;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsElement;
import org.openqa.selenium.interactions.Locatable;

import com.framework.controls.api.ImplementedBy;

@ImplementedBy(ControlBase.class)
public interface Control extends WebElement, WrapsElement, Locatable {
    ControlBase Wait();

    ControlBase WaitForVisible();

    ControlBase WaitForBeClickable();

    ControlBase Click();

    ControlBase ScrollToElement() throws InterruptedException;
}
