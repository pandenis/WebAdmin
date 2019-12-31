
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.tests.ReadInputData;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class UpdatePage  {

    int stepNumber = 0;
    public WebDriver driver;
    public String testURL = "https://d2ad6e0d0eehtx.cloudfront.net/";
    public String filePath = "/home/pun/Selenium";
    public String fileName = "Input.xlsx";
    public String uploadFilePath = "/home/pun/Selenium/example.apk";
    public String versionNumber = "4.1.2.6";
    public String groupName = "test";

    ReadInputData readInputData = new ReadInputData();

    @BeforeMethod
    public void setupTest() throws IOException {

        driver = new ChromeDriver();
        // driver.manage().window().maximize();
        driver.navigate().to(testURL);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        stepNumber++;
        String title = driver.getTitle();
        System.out.println(stepNumber + ". Page Title is " + title);
        Assert.assertEquals(title, "ContinUse", "Title assertion is failed!");

        stepNumber++;
        String uname = readInputData.readExcel(filePath, fileName, 0, 0, 1);
        WebElement usernameElement = driver.findElement(By.name("email"));
        usernameElement.sendKeys(uname);
        System.out.println(stepNumber + ". Set username");

        String psswd = readInputData.readExcel(filePath, fileName, 0, 1, 1);
        WebElement passwordElement = driver.findElement(By.name("password"));
        passwordElement.sendKeys(psswd);
//        System.out.println(stepNumber + ". Set password");
        System.out.println(stepNumber + ". Set password");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        stepNumber++;
        WebElement elementButton = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div[2]/div[1]/div/div/form/div[3]/button"));
        elementButton.click();
        System.out.println(stepNumber + ". Click button Log In");
        String actualString = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div[1]/div[1]/div[2]/div[1]/div[3]")).getText();
        String header = "Locations";
        Assert.assertTrue(actualString.contains(header), "Page Header assertion is failed!");

    }

    @Test
    public void uploadFileToS3() {
        stepNumber = 0;

        stepNumber++;
        String testURL = "https://d2ad6e0d0eehtx.cloudfront.net/update";
        driver.navigate().to(testURL);
        String actualString = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div/div[2]/div[1]/div[1]/div")).getText();
        String header = "Update Management";
        Assert.assertTrue(actualString.contains(header), "Page Header assertion is failed!");
        System.out.println(stepNumber + ". Navigate to update URI");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        stepNumber++;
        WebElement elementButton = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div/div[2]/div[1]/div[2]/button"));
        elementButton.click();
        System.out.println(stepNumber + ". Click button Upload");

        stepNumber++;
        actualString = driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div[1]/div[2]/span")).getText();
        header = "Upload Firmware";
        Assert.assertTrue(actualString.contains(header), "Pop-Up dialog window " + header + " assertion is failed!");
        System.out.println(stepNumber + ". Pop-Up dialog window " + header + " available");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        stepNumber++;
        String str = "//*[@id=\"fileId\"]";
        WebElement uploadElement = driver.findElement(By.xpath(str));
        uploadElement.sendKeys(uploadFilePath);
        //@TODO add file uploaded assertion
        System.out.println(stepNumber + ". Upload *.apk file");

        stepNumber++;
        str = "/html/body/div[2]/div/div/div/div[2]/div/div/div[2]/input";
        WebElement enterVersionNumber = elementButton.findElement(By.xpath(str));
        enterVersionNumber.sendKeys(versionNumber);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        actualString = driver.findElement(By.cssSelector("div.UpdateManagerComponent-inputRow:nth-child(2) > input:nth-child(2)")).getText();
        System.out.println(actualString);
//        Assert.assertTrue(actualString.contains(versionNumber), "Entering version number assertion is failed!");
        System.out.println(stepNumber + ". Enter version number");

        stepNumber++;
        str = "/html/body/div[2]/div/div/div/div[2]/div/div/div[3]/div[2]/div/div/div[1]";
        WebElement groupsDDlist = driver.findElement(By.xpath(str));
        groupsDDlist.click();
        groupsDDlist.sendKeys(groupName);
        System.out.println(stepNumber + ". Select group name");





    }




    @AfterMethod
    public void teardownTest() {
        driver.close();

    }
}
