package org.example.pom;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
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

import java.io.ByteArrayInputStream;
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
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By valueCell = By.xpath("//td[normalize-space(text())='" + labelParam + "']/following-sibling::td");
        WebElement cell = wait.until(d -> d.findElement(valueCell));
        return cell.getText();
    }
    @Step("Set Gender")
    public void setGender(String genderParam) {
        takeScreenshot("Before Set Gender");
        WebElement gender = driver.findElement(By.xpath("//*[@id='genterWrapper']//label[text()='" + genderParam + "']"));
        gender.click();
        takeScreenshot("After Set Gender");
    }
    @Step("Set State")
    public void setState(String stateParam) {
        takeScreenshot("Before Set State");
        closeAdvert();
        clickWithRetry(By.id("state"));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement input = wait.until(d -> d.findElement(By.id("react-select-3-input")));
        input.sendKeys(stateParam);
        input.sendKeys(Keys.ENTER);
        takeScreenshot("After Set State");
    }
    @Step("Set City")
    public void setCity(String cityParam) {
        takeScreenshot("Before Set City");
        closeAdvert();
        clickWithRetry(By.id("city"));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement input = wait.until(d -> d.findElement(By.id("react-select-4-input")));
        input.sendKeys(cityParam);
        input.sendKeys(Keys.ENTER);
        takeScreenshot("After Set City");
    }
    @Step("Set Hobby")
    public void setHobby(String hobbyParam) {
        takeScreenshot("Before Set Hobby");
        WebElement ddState = driver.findElement(By.xpath("//*[@id='hobbiesWrapper']//label[text()='" + hobbyParam + "']/../input"));
        ddState.sendKeys(" ");
        takeScreenshot("After Set Hobby");
    }
    @Step("Set Number")
    public void setNumber(String numberParam) {
        takeScreenshot("Before Set Number");
        userNumber.clear();
        userNumber.sendKeys(numberParam);
        takeScreenshot("After Set Number");
    }
    @Step("Set Date")
    public void setDate(String dateParam) {
        takeScreenshot("Before Set Date");
        dateOfBirthInput.sendKeys(Keys.CONTROL, "a");
        dateOfBirthInput.sendKeys(dateParam);
        dateOfBirthInput.sendKeys(Keys.ENTER);
        takeScreenshot("After Set Date");
    }
    @Step("Set Subject")
    public void setSubject(String subjectParam) {
        takeScreenshot("Before Set Subject");
        subjectsInput.clear();
        subjectsInput.sendKeys(subjectParam);
        subjectsInput.sendKeys(Keys.ENTER);
        takeScreenshot("After Set Subject");
    }

    @Step("Set First Name")
    public void setFirstName(String firstNameParam) {
        takeScreenshot("Before Set First Name");
        firstName.clear();
        firstName.sendKeys(firstNameParam);
        takeScreenshot("After Set First Name");
    }
    @Step("Set Last Name")
    public void setLastName(String lastNameParam) {
        takeScreenshot("Before Set Last Name");
        lastName.clear();
        lastName.sendKeys(lastNameParam);
        takeScreenshot("After Set Last Name");
    }
    @Step("Set User Email")
    public void setUserEmail(String userEmailParam) {
        takeScreenshot("Before Set User Email");
        userEmail.clear();
        userEmail.sendKeys(userEmailParam);
        takeScreenshot("After Set User Email");
    }
    public void clickPracticeForm() {
        By practiceFormBy = By.xpath("//*[normalize-space(text())='Practice Form']");
        clickWithRetry(practiceFormBy);
    }


    public void clickForms() {
        By formsBy = By.xpath("//*[normalize-space(text())='Forms']");
        clickWithRetry(formsBy);
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
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
    }

    private void clickWithRetry(By by) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement element = wait.until(d -> d.findElement(by));
        scrollToElement(element);

        try {
            wait.until(org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable(by)).click();
        } catch (Exception e) {
            closeAdvert();
            element = wait.until(d -> d.findElement(by));
            scrollToElement(element);
            js.executeScript("arguments[0].click();", element);
        }
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

    private void takeScreenshot(String stepName) {
        try {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment(stepName, "image/png", new ByteArrayInputStream(screenshot), ".png");
        } catch (Exception e) {
            Allure.addAttachment("Screenshot Error", e.toString());
        }
    }

}
