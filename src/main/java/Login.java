import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Login {
    /*public static void main(String[] args) {
        String webDriverPath = "/home/pun/Selenium/driver/geckodriver";
        System.setProperty("webdriver.gecko.driver", webDriverPath);
    }*/

    //Global variable

    public WebDriver driver;
    public String testURL = "https://d2ad6e0d0eehtx.cloudfront.net";

    ReadInputData inputData = new ReadInputData();


    @BeforeMethod
    public void setupTest() {
        driver = new ChromeDriver();
       // driver.manage().window().maximize();
        driver.navigate().to(testURL);
    }

    /*@Test
    public void launchPage () {
        String title = driver.getTitle();

        System.out.println("Page Title is " + title);

        Assert.assertEquals(title, "ContinUse", "Title assertion is failed!");
    }*/
    @Test
    public void setUsername_and_password() throws IOException {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        String uname = inputData.readExcel("/home/pun/Selenium", "Input.xlsx", 0, 0, 1);
        WebElement usernameElement = driver.findElement(By.name("email"));
        usernameElement.sendKeys(uname);

        System.out.println("Set username");

        String psswd = inputData.readExcel("/home/pun/Selenium", "Input.xlsx", 0, 1, 1);
        WebElement passwordElement = driver.findElement(By.name("password"));
        passwordElement.sendKeys(psswd);

        System.out.println("Set password");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement elementButton = driver.findElement(By.cssSelector("#app > div > div > div.LoginFormPage-RightContainer > div.LoginFormPage-LoginForm > div > div > form > div.LoginFormComponent-LoginButtonContainer > button"));
        elementButton.click();
                //"/html/body/div/div/div/div[2]/div[1]/div/div/form/div[3]/button");
        //buttonElement.click();

        System.out.println("Click button Log In");

        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /*@Test
    public void clickButton() {
        WebElement buttonElement = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div[2]/div[1]/div/div/form/div[3]/button"));
        buttonElement.click();
    }
*/
    @AfterMethod
    public void teardownTest() {
        driver.close();
    }
}
