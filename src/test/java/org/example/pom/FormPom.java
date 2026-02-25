package org.example.pom;

import org.example.utils.Utils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.Key;
import java.time.Duration;
import java.util.function.Function;

public class FormPom {
    static public WebDriver driver;
    static public JavascriptExecutor js;
    @FindBy(xpath = "//*[text()='Forms']")
    WebElement forms;

    @FindBy(xpath = "//*[text()='Practice Form']")
    WebElement practiceForm;

    @FindBy(xpath = "//*[@id='firstName']")
    WebElement firstName;

    @FindBy(xpath = "//*[@id='lastName']")
    WebElement lastName;

    @FindBy(xpath = "//*[@id='userEmail']")
    WebElement userEmail;
    @FindBy(xpath = "//*[@id='genterWrapper']")
    WebElement gender;
    @FindBy(xpath = "//*[@id='userNumber']")
    WebElement userNumber;

    @FindBy(xpath = "//*[@id='dateOfBirthInput']")
    WebElement dateOfBirthInput;

    @FindBy(xpath = "//*[@id='subjectsInput']")
    WebElement subjectsInput;

    @FindBy(xpath = "//*[@id='state']")
    WebElement state;

    @FindBy(xpath = "//*[@id='city']")
    WebElement city;

    @FindBy(xpath = "//*[@id='submit']")
    WebElement submit;

    public FormPom(WebDriver driverParam) {
        driver = driverParam;
        js = (JavascriptExecutor) driverParam;
        PageFactory.initElements(driver, this);
    }

    public void clickSubmit() {
        scrollToElement(submit);
        submit.click();
    }

    public String getTableData(String labelParam) {
        String value = driver.findElement(By.xpath("//label//*[text()='" + labelParam + "']/../*[2]")).getText();
        return value;
    }

    public void setGender(String genderParam) {
        WebElement gender = driver.findElement(By.xpath("//*[@id='genterWrapper']//label[text()='" + genderParam + "']"));
        gender.click();
    }
    public void setState(String stateParam) {
        scrollToElement(state);
        state.click();
        WebElement stateOption = driver.findElement(By.xpath("//*[text()='" + stateParam + "']"));
        stateOption.click();
    }

    public void setCity(String cityParam) {
        city.click();
        WebElement cityOption = driver.findElement(By.xpath("//*[text()='" + cityParam + "']"));
        cityOption.click();
    }

    public void setHobby(String hobbyParam) {
        WebElement ddState = driver.findElement(By.xpath("//*[@id='hobbiesWrapper']//label[text()='" + hobbyParam + "']/../input"));
        ddState.sendKeys(" ");
    }

    public void setNumber(String numberParam) {
        userNumber.clear();
        userNumber.sendKeys(numberParam);
    }

    public void setDate(String dateParam) {
        dateOfBirthInput.sendKeys(Keys.CONTROL, "a");
        dateOfBirthInput.sendKeys(dateParam);
        dateOfBirthInput.sendKeys(Keys.ENTER);
    }

    public void setSubject(String subjectParam) {
        subjectsInput.clear();
        subjectsInput.sendKeys(subjectParam);
        subjectsInput.sendKeys(Keys.ENTER);
    }


    public void setFirstName(String firstNameParam) {
        firstName.clear();
        firstName.sendKeys(firstNameParam);
    }

    public void setLastName(String lastNameParam) {
        lastName.clear();
        lastName.sendKeys(lastNameParam);
    }

    public void setUserEmail(String userEmailParam) {
        userEmail.clear();
        userEmail.sendKeys(userEmailParam);
    }
    public void clickPracticeForm() {
        practiceForm.click();
    }

    public void clickForms() {
        forms.click();
    }

    private static final Logger logger = LoggerFactory.getLogger(Utils.class);
    private static final long DEFAULT_TIMEOUT = 10;

    public static WebElement fluentWait(WebDriver driver, By by) {
        return fluentWait(driver, by, DEFAULT_TIMEOUT);
    }

    public static WebElement fluentWait(WebDriver driver, By by, long seconds) {
        logger.info("Waiting for element: {} with timeout: {} seconds", by, seconds);
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(seconds))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class);
        return wait.until((Function<WebDriver, WebElement>) dr -> {
            assert dr != null;
            return dr.findElement(by);
        });
    }

    public static <T> T explicitWait(WebDriver driver, ExpectedCondition<T> condition, long seconds) {
        logger.info("Waiting with explicit wait: {} seconds", seconds);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        return wait.until(condition);
    }




    public void scrollToElement(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }


    public void closeAdvert() {
        try {
            js.executeScript("var elem = document.evaluate(\"//*[@id='adplus-anchor']\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;" +
                    "elem.parentNode.removeChild(elem);");
        } catch (Exception ignored) {}
        try {
            js.executeScript("var elem = document.evaluate(\"//footer\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;" +
                    "elem.parentNode.removeChild(elem);");
        } catch (Exception ignored) {}
    }

}
