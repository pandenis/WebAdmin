import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class LoginPage implements TS {
    /*public static void main(String[] args) {
        String webDriverPath = "/home/pun/Selenium/driver/geckodriver";
        System.setProperty("webdriver.gecko.driver", webDriverPath);
    }*/

    //Global variable

    public WebDriver driver;
    private static String testURL = "https://stage.cu-bx.com/LoginPage/login";
    private static String filePath = "C:\\Temp\\Sel\\InputData";
    private static String fileName = "Input.xlsx";

    int stepNumber = 0;

    ReadInputData inputData = new ReadInputData();


    @BeforeMethod
    public void setupTest() {

        driver = new ChromeDriver();
        // driver.manage().window().maximize();
        driver.navigate().to(testURL);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }


    @Test
    public void LoginToWebAdminSite() throws IOException {

        stepNumber++;
        String title = driver.getTitle();
        System.out.println(stepNumber + ". Page Title is " + title);
        Assert.assertEquals(title, "ContinUse", "Title assertion is failed!");

        stepNumber++;
        String uname = inputData.readExcel(filePath, fileName, 0, 0, 1);
        WebElement usernameElement = driver.findElement(By.name("email"));
        usernameElement.sendKeys(uname);
        System.out.println(stepNumber + ". Set username");

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

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        stepNumber++;
        String actualString = driver.findElement(By.xpath("/html/body/div/div/div/div[2]/div[2]/div/div[1]/div[1]")).getText();
        String header = "Locations ";
        System.out.println(stepNumber + ". Page header is: " + actualString);
        Assert.assertTrue(actualString.contains(header), "Page Header assertion is failed!");
        Set<Cookie> allCookie = driver.manage().getCookies();

        //  return allCookie;

    }


    @AfterMethod
    public void teardownTest() {
        if (driver != null) {
            driver.quit();
        }
    }


}