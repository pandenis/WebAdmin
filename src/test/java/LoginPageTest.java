import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.tests.ReadInputData;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import pages.tests.*;

public class LoginPageTest implements TS {
    /*public static void main(String[] args) {
        String webDriverPath = "/home/pun/Selenium/driver/geckodriver";
        System.setProperty("webdriver.gecko.driver", webDriverPath);
    }*/
    //TODO: Create method in pages.tests.ReadInputData launch the LoginPage

    //Global variable

    public WebDriver driver;

    public static String filePath = "C:\\Temp\\Sel\\InputData";
    public static String fileName = "Input.xlsx";

    int stepNumber = 0;

    ReadInputData inputData = new ReadInputData();
    LoginPage loginPage = new LoginPage();

    @BeforeMethod
    public void setupTest() {
       driver = loginPage.launch();
    }

    @Test
    public void AssertPageTitle() {

        stepNumber++;
        String actualTitle = driver.getTitle();
        String expectedTitle = "ContinUse1";

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualTitle, expectedTitle);
        softAssert.assertAll();
    }

    @Test()
     public void enterEmail()

    {
        driver = loginPage.setUsername();
        stepNumber++;

    }

    @Test(dependsOnMethods="enterEmail")
    public void test() throws IOException {

        stepNumber++;
        String psswd = inputData.readExcel(filePath, fileName, 0, 1, 1);
        WebElement passwordElement = driver.findElement(By.name("password"));
        passwordElement.sendKeys(psswd);
        System.out.println(stepNumber + ". Set password");

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        stepNumber++;
        WebElement elementButton = driver.findElement(By.xpath("//button[text()='LOG IN']"));
        elementButton.click();
        System.out.println(stepNumber + ". Click button Log In");

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);


        stepNumber++;

        String pt = driver.getTitle();

 //       System.out.println(pt);

        String actualString = driver.findElement(By.linkText("Locations")).getText();
        String expectedHeader = "Locations";
        if (actualString.equalsIgnoreCase(expectedHeader)) {
            System.out.println(stepNumber + ". Page header is: " + actualString);
        } else {
            Assert.assertTrue(actualString.contains(expectedHeader), "Page Header assertion is failed!");
        }

        //  return allCookie;

    }


    @AfterMethod
    public void teardownTest() {
        if (driver != null) {
            driver.quit();
        }
    }


}