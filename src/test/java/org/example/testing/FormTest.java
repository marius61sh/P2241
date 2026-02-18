package org.example.testing;

import org.example.pom.FormPom;
import org.example.utils.Driver;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FormTest {
    static public WebDriver driver;

    static public String URL = "https://demoqa.com/";

    @BeforeMethod
    public void beforeMethod() {
        driver = Driver.getAutoLocalDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void  FormTest() {
        System.out.println("Start test");
        driver.get(URL);
        FormPom formPom = new FormPom(driver);
        formPom.clickForms();
        System.out.println("Finish test");
    }

    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }
}
