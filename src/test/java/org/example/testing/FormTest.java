package org.example.testing;

import org.example.pom.FormPom;
import org.example.utils.Driver;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FormTest {
    static public WebDriver driver;

    static public String URL = "https://demoqa.com/";
    static public String FIRST_NAME = "Marius";
    static public String LAST_NAME = "Golimaz";
    static public String EMAIL = "marius.golimaz@gmail.com";
    static public String NUMBER = "0603363266";
    static public String DATE = "17 jul 2012";
    static public String SUBJECT = "Maths";
    static public String HOBBY = "Sports";
    static public String STATE = "NCR";
    static public String CITY = "Delhi";
    static public String GENDER = "Male";



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
        formPom.clickPracticeForm();
        formPom.setFirstName(FIRST_NAME);
        formPom.setLastName(LAST_NAME);
        formPom.setUserEmail(EMAIL);
        formPom.setGender(GENDER);
        formPom.setNumber(NUMBER);
        formPom.setDate(DATE);
        formPom.setSubject(SUBJECT);
        formPom.setHobby(HOBBY);
        formPom.setState(STATE);
        formPom.setCity(CITY);
        formPom.clickSubmit();

        String studentName = formPom.getTableData("Student Name");
        System.out.println("Finish test");
    }

    @AfterMethod
    public void afterMethod() {

    }
}
