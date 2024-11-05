package com.framework.controls.elements;


import com.framework.controls.api.ImplementedBy;
import com.framework.controls.internals.Control;
import com.framework.controls.internals.ControlBase;

@ImplementedBy(HyperLinkBase.class)
public interface HyperLink extends Control {

    void ClickLink();

    String GetUrlText();

    boolean CheckUrlTextContains(String containsText);

    ControlBase Wait();

    ControlBase WaitForVisible();

    ControlBase WaitForBeClickable();

    ControlBase Click();

    ControlBase ScrollToElement() throws InterruptedException;
}
