package com.framework.base;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Quotes;
import org.openqa.selenium.support.ui.UnexpectedTagNameException;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import org.openqa.selenium.WrapsElement;

public class NewSelect implements INewSelect, WrapsElement {
  private final WebElement element;
  private final boolean isMulti;

  public NewSelect(WebElement element) {
    String tagName = element.getTagName();
    if ("select".equalsIgnoreCase(tagName)) {
      this.element = element;
      String value = element.getAttribute("multiple");
      this.isMulti = value != null && !"false".equals(value);
    } else {
      throw new UnexpectedTagNameException("select", tagName);
    }
  }

  public WebElement getWrappedElement() {
    return this.element;
  }

  public boolean isMultiple() {
    return this.isMulti;
  }

  public List<WebElement> getOptions() {
    return this.element.findElements(By.tagName("option"));
  }

  public List<WebElement> getAllSelectedOptions() {
    return (List)this.getOptions().stream().filter(WebElement::isSelected).collect(Collectors.toList());
  }

  public WebElement getFirstSelectedOption() {
    return (WebElement)this.getOptions().stream().filter(WebElement::isSelected).findFirst().orElseThrow(() -> {
      return new NoSuchElementException("No options are selected");
    });
  }

  public void selectByVisibleText(String text) {
    this.assertSelectIsEnabled();
    List<WebElement> options = this.element.findElements(By.xpath(".//option[normalize-space(.) = " + Quotes.escape(text) + "]"));
    Iterator var3 = options.iterator();

    while(var3.hasNext()) {
      WebElement option = (WebElement)var3.next();
      this.setSelected(option, true);
      if (!this.isMultiple()) {
        return;
      }
    }

    boolean matched = !options.isEmpty();
    if (!matched && text.contains(" ")) {
      String subStringWithoutSpace = this.getLongestSubstringWithoutSpace(text);
      List candidates;
      if ("".equals(subStringWithoutSpace)) {
        candidates = this.element.findElements(By.tagName("option"));
      } else {
        candidates = this.element.findElements(By.xpath(".//option[contains(., " + Quotes.escape(subStringWithoutSpace) + ")]"));
      }

      String trimmed = text.trim();
      Iterator var7 = candidates.iterator();

      while(var7.hasNext()) {
        WebElement option = (WebElement)var7.next();
        if (trimmed.equals(option.getText().trim())) {
          this.setSelected(option, true);
          if (!this.isMultiple()) {
            return;
          }

          matched = true;
        }
      }
    }

    if (!matched) {
      throw new NoSuchElementException("Cannot locate option with text: " + text);
    }
  }

  private String getLongestSubstringWithoutSpace(String s) {
    String result = "";
    StringTokenizer st = new StringTokenizer(s, " ");

    while(st.hasMoreTokens()) {
      String t = st.nextToken();
      if (t.length() > result.length()) {
        result = t;
      }
    }

    return result;
  }

  public void selectByIndex(int index) {
    this.assertSelectIsEnabled();
    this.setSelectedByIndex(index, true);
  }

  public void selectByValue(String value) {
    this.assertSelectIsEnabled();
    Iterator var2 = this.findOptionsByValue(value).iterator();

    do {
      if (!var2.hasNext()) {
        return;
      }

      WebElement option = (WebElement)var2.next();
      this.setSelected(option, true);
    } while(this.isMultiple());

  }

  public void deselectAll() {
    if (!this.isMultiple()) {
      throw new UnsupportedOperationException("You may only deselect all options of a multi-select");
    } else {
      Iterator var1 = this.getOptions().iterator();

      while(var1.hasNext()) {
        WebElement option = (WebElement)var1.next();
        this.setSelected(option, false);
      }

    }
  }

  public void deselectByValue(String value) {
    if (!this.isMultiple()) {
      throw new UnsupportedOperationException("You may only deselect options of a multi-select");
    } else {
      Iterator var2 = this.findOptionsByValue(value).iterator();

      while(var2.hasNext()) {
        WebElement option = (WebElement)var2.next();
        this.setSelected(option, false);
      }

    }
  }

  public void deselectByIndex(int index) {
    if (!this.isMultiple()) {
      throw new UnsupportedOperationException("You may only deselect options of a multi-select");
    } else {
      this.setSelectedByIndex(index, false);
    }
  }

  public void deselectByVisibleText(String text) {
    if (!this.isMultiple()) {
      throw new UnsupportedOperationException("You may only deselect options of a multi-select");
    } else {
      List<WebElement> options = this.element.findElements(By.xpath(".//option[normalize-space(.) = " + Quotes.escape(text) + "]"));
      if (options.isEmpty()) {
        throw new NoSuchElementException("Cannot locate option with text: " + text);
      } else {
        Iterator var3 = options.iterator();

        while(var3.hasNext()) {
          WebElement option = (WebElement)var3.next();
          this.setSelected(option, false);
        }

      }
    }
  }

  private List<WebElement> findOptionsByValue(String value) {
    List<WebElement> options = this.element.findElements(By.xpath(".//option[@value = " + Quotes.escape(value) + "]"));
    if (options.isEmpty()) {
      throw new NoSuchElementException("Cannot locate option with value: " + value);
    } else {
      return options;
    }
  }

  private void setSelectedByIndex(int index, boolean select) {
    String match = String.valueOf(index);
    Iterator var4 = this.getOptions().iterator();

    WebElement option;
    do {
      if (!var4.hasNext()) {
        throw new NoSuchElementException("Cannot locate option with index: " + index);
      }

      option = (WebElement)var4.next();
    } while(!match.equals(option.getAttribute("index")));

    this.setSelected(option, select);
  }

  private void setSelected(WebElement option, boolean select) {
    this.assertOptionIsEnabled(option, select);
    if (option.isSelected() != select) {
      option.click();
    }

  }

  private void assertOptionIsEnabled(WebElement option, boolean select) {
    if (select && !option.isEnabled()) {
      throw new UnsupportedOperationException("You may not select a disabled option");
    }
  }

  private void assertSelectIsEnabled() {
    if (!this.element.isEnabled()) {
      throw new UnsupportedOperationException("You may not select an option in disabled select");
    }
  }

  public boolean equals(Object o) {
    if (!(o instanceof NewSelect)) {
      return false;
    } else {
      NewSelect select = (NewSelect)o;
      return Objects.equals(this.element, select.element);
    }
  }

  public int hashCode() {
    return Objects.hash(new Object[]{this.element});
  }
}
