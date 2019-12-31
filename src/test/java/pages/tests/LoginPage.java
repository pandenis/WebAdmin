package pages.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static pages.tests.ReadInputData.fileName;
import static pages.tests.ReadInputData.filePath;

public class LoginPage {

    public static ReadInputData inputData = new ReadInputData();
    WebDriver driver;

    public WebDriver launch() {

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to(inputData.testURL);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);


        return driver;
    }

    /*public static void assertPageTitle() {

    }*/

    public WebDriver setUsername() {
        String uname = null;

        try {
            uname = inputData.readExcel(filePath, fileName, 0, 0, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        WebElement usernameElement = driver.findElement(By.name("email"));
        usernameElement.sendKeys(uname);
//        System.out.println(stepNumber + ". Set username");

        return driver;
    }

    public static void setPassword() {

    }

    public static void  clickButton() {

    }
}
