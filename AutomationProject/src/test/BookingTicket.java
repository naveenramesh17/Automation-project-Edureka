package test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class BookingTicket {

    private WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setCapability("marionette", true);
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\Naveen Ramesh\\Downloads\\geckodriver-v0.23.0-arm7hf\\geckodriver.exe");
        driver = new FirefoxDriver(capabilities);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    @Test
    public void bookFlightTicket() throws Exception {

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://newtours.demoaut.com.");

        WebElement user = driver.findElement(By.name("userName"));
        user.sendKeys("ajayk123");

        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("Work4efx");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement signin = driver.findElement(By.name("login"));
        signin.click();
        driver.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);

        WebElement continueButton = driver.findElement(By.name("findFlights"));
        continueButton.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement reserveFlights = driver.findElement(By.name("reserveFlights"));
        reserveFlights.click();

        WebElement firstName = driver.findElement(By.name("passFirst0"));
        firstName.sendKeys("Test");

        WebElement lastName = driver.findElement(By.name("passLast0"));
        lastName.sendKeys("Test123");

        WebElement number = driver.findElement(By.name("creditnumber"));
        number.sendKeys("123");

        WebElement purchase = driver.findElement(By.name("buyFlights"));
        purchase.click();

        System.out.println("Flight booking successfully completed");

        SendingEmail.sendMail();

    }
}
