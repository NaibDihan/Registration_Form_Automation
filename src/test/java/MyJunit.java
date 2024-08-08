import com.github.javafaker.Faker;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MyJunit {
    WebDriver driver;
    @BeforeAll
    public void setup(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

    }

    @Test
    public void formFillup() throws InterruptedException {
        driver.get("https://demo.wpeverest.com/user-registration/guest-registration-form/");

        Faker faker = new Faker();
        String name = faker.name().name();
        String firstname = faker.name().firstName();
        System.out.println(name);

        List<WebElement> formControls = driver.findElements(By.className("input-text"));
        List<WebElement> formControlsRadio = driver.findElements(By.className("input-radio"));
        List<WebElement> formControlsDate = driver.findElements(By.className("ur-flatpickr-field"));
        List<WebElement> formControlsDateSpan = driver.findElements(By.cssSelector(".flatpickr-day.today"));

        formControls.get(0).sendKeys(firstname);
        formControls.get(3).sendKeys("Uddin");
        formControls.get(1).sendKeys(firstname+"12@gmail.com");
        formControlsRadio.get(0).click();
        formControls.get(2).sendKeys(name+"$$__22");

        //Date of Birth
        formControlsDate.get(0).click();

        WebElement monthSelector = driver.findElement(By.className("flatpickr-monthDropdown-months"));
        monthSelector.click();
        monthSelector.sendKeys("N");
        monthSelector.sendKeys(Keys.ENTER);

        List<WebElement> yearSelector = driver.findElements(By.className("cur-year"));
        yearSelector.get(0).click();
        yearSelector.get(0).sendKeys("1999");
        yearSelector.get(0).sendKeys(Keys.ENTER);

        List<WebElement> formControlDateofBirth =driver.findElements(By.className("flatpickr-day"));
        formControlDateofBirth.get(19).click();




        //driver.findElement(By.cssSelector(".flatpickr-day.today")).click();
        formControls.get(5).sendKeys("Bangladeshi");

        driver.findElement(By.cssSelector("[name=phone_1665627880]")).sendKeys("9652873514");

        WebElement countrySelector = driver.findElement(By.id("country_1665629257"));
        countrySelector.click();
        countrySelector.sendKeys("Bangladesh");
        countrySelector.sendKeys(Keys.ENTER);

        Utils.scroll(driver,1000);

        driver.findElement(By.cssSelector("[name=phone_1665627865]")).sendKeys("9652873524");

        //Date of Arrival
        formControlsDate.get(1).click();
        formControlsDateSpan.get(1).click();




        formControls.get(7).sendKeys("6 months");
        formControls.get(8).sendKeys("201, 2");
        formControls.get(9).sendKeys("SQA, Dhanmondi");

        formControlsRadio.get(3).click();
        formControlsRadio.get(5).click();
        formControlsRadio.get(8).click();

        WebElement activitySelector = driver.findElement(By.id("select_1665628361"));
        activitySelector.click();
        activitySelector.sendKeys("A");
        activitySelector.sendKeys(Keys.ENTER);

        driver.findElement(By.cssSelector("[name=privacy_policy_1665633140]")).click();


        Thread.sleep(4000);

        driver.findElement(By.className("ur-submit-button")).click();


        String successMsgExpected = "User successfully registered.";
        String successMsg = driver.findElement(By.className("user-registration-message")).getText();
        Assertions.assertEquals(successMsgExpected,successMsg);

    }
    @AfterAll
    public void closeBrowser(){
        //driver.close();
        driver.quit();
    }


}
