package org.example.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FormPom {
    static public WebDriver driver;
    @FindBy(xpath = "//*[text()='Forms']")
    WebElement forms;

    public FormPom(WebDriver driverParam) {
        driver = driverParam;
        PageFactory.initElements(driver, this);
    }

    public void clickForms() {
        forms.click();
    }
}
