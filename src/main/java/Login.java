import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.FileInputStream;

public class Login {
    /*public static void main(String[] args) {
        String webDriverPath = "/home/pun/Selenium/driver/geckodriver";
        System.setProperty("webdriver.gecko.driver", webDriverPath);
    }*/

    //Global variable

    String webDriverPath = "/home/pun/Selenium/driver/geckodriver";
    public WebDriver driver;
    public String testURL = "https://d2ad6e0d0eehtx.cloudfront.net";


    @BeforeMethod
    public void setupTest() {
        driver = new ChromeDriver();
        driver.navigate().to(testURL);
    }

    @Test
    public void launchPage () {
        String title = driver.getTitle();

        System.out.println("Page Title is " + title);

        Assert.assertEquals(title, "ContinUse", "Title assertion is failed!");
    }
    @Test
    public void setUsername () {

    }

    @AfterMethod
    public void teardownTest() {
        driver.close();
    }
}
