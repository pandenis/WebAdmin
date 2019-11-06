import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class LocationsPage {
    public static WebDriver driver;
    public static String testURL = "https://stage.cu-bx.com/LoginPage/login";
    public static String filePath = "C:\\Temp\\Sel\\InputData";
    public static String fileName = "Input.xlsx";

    int stepNumber = 0;
    String xpath = null;
    String locationName = "Location Name" + System.currentTimeMillis();

    ReadInputData inputData = new ReadInputData();

    @BeforeMethod
    public void setupTest() {

        driver = new ChromeDriver();
        //driver = new FirefoxDriver();

        // driver.manage().window().maximize();
        driver.navigate().to(testURL);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    public void LoginToWebAdminSite() throws IOException {

        String title = driver.getTitle();
        System.out.println(stepNumber + ". Page Title is " + title);
        Assert.assertEquals(title, "ContinUse", "Title assertion is failed!");

        String uname = inputData.readExcel(filePath, fileName, 0, 0, 1);
        WebElement usernameElement = driver.findElement(By.name("email"));
        usernameElement.sendKeys(uname);
        System.out.println(stepNumber + ". Set username");

        String psswd = inputData.readExcel(filePath, fileName, 0, 1, 1);
        WebElement passwordElement = driver.findElement(By.name("password"));
        passwordElement.sendKeys(psswd);
        System.out.println(stepNumber + ". Set password");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String xpath = "//*[@id=\"app\"]/div/div/div[2]/div[2]/div[1]/div/div/form/div[3]/button";
        WebElement elementButton = driver.findElement(By.xpath(xpath));
        elementButton.click();
        System.out.println(stepNumber + ". Click button Log In");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        xpath = "/html/body/div/div/div/div[2]/div[2]/div/div[1]/div[1]";
        String actualString = driver.findElement(By.xpath(xpath)).getText();
        String header = "Locations ";
        System.out.println(stepNumber + ". Page header is: " + actualString);
        Assert.assertTrue(actualString.contains(header), "Page Header assertion is failed!");
    }

    @Test

    public void Create_NewLocation() throws IOException {

        String title = driver.getTitle();
//        System.out.println(stepNumber + ". Page Title is " + title);
        Assert.assertEquals(title, "ContinUse", "Title assertion is failed!");

        String uname = inputData.readExcel(filePath, fileName, 0, 0, 1);
        WebElement usernameElement = driver.findElement(By.name("email"));
        usernameElement.sendKeys(uname);
//        System.out.println(stepNumber + ". Set username");

        String psswd = inputData.readExcel(filePath, fileName, 0, 1, 1);
        WebElement passwordElement = driver.findElement(By.name("password"));
        passwordElement.sendKeys(psswd);
//        System.out.println(stepNumber + ". Set password");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        xpath = "//*[@id=\"app\"]/div/div/div[2]/div[2]/div[1]/div/div/form/div[3]/button";
        WebElement elementButton = driver.findElement(By.xpath(xpath));
        elementButton.click();
//        System.out.println(stepNumber + ". Click button Log In");

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        xpath = "/html/body/div/div/div/div[2]/div[2]/div/div[1]/div[1]";
        String actualString = driver.findElement(By.xpath(xpath)).getText();
        String header = "Locations ";
        Assert.assertTrue(actualString.contains(header), "Page Header assertion is failed!");
//        System.out.println(stepNumber + ". Page header is: " + actualString);


        xpath = "/html/body/div[1]/div/div/div[2]/div[2]/div/div[1]/div[2]/button";
        WebElement elementButtonCreate = driver.findElement(By.xpath(xpath));
        elementButtonCreate.click();
//        System.out.println(stepNumber + ". Click button Log In");


        System.out.println("Create Location");
        stepNumber++;

        WebElement locationNameElement = driver.findElement(By.name("locationName"));
        locationNameElement.sendKeys(locationName);
        System.out.println(stepNumber + ". Set Location Name");

        stepNumber++;
        String id = "react-select-2-input";
        driver.findElement(By.id(id)).clear();
        driver.findElement(By.id(id)).sendKeys("amiType");
        driver.findElement(By.id(id)).sendKeys(Keys.ENTER);
        System.out.println(stepNumber + ". Set Location Type");

        stepNumber++;
        String name = "locationDescription";
        driver.findElement(By.name(name)).click();
        driver.findElement(By.name(name)).clear();
        driver.findElement(By.name(name)).sendKeys("test");
        System.out.println(stepNumber + ". Set Location Description");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        stepNumber++;
        xpath = "(.//*[normalize-space(text()) and normalize-space(.)='e.g: Purpose of institute, How to locate it, Additional details about this location and etc.'])[1]/following::div[2]";
        elementButton = driver.findElement(By.xpath(xpath));
        elementButton.click();
        System.out.println(stepNumber + ". Click the SAVE button");

        stepNumber++;
        driver.findElement(By.id("react-select-3-input")).clear();
        driver.findElement(By.id("react-select-3-input")).sendKeys("Israel");
        driver.findElement(By.id("react-select-3-input")).sendKeys(Keys.ENTER);
        System.out.println(stepNumber + ". Set Address - Country");

        stepNumber++;
        id = "react-select-4-input";
        driver.findElement(By.id(id)).sendKeys("-");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.id(id)).sendKeys(Keys.ENTER);
        System.out.println(stepNumber + ". Set Address - State");

        stepNumber++;
        id = "react-select-5-input";
        driver.findElement(By.id(id)).clear();
        driver.findElement(By.id(id)).sendKeys("Afula");
        driver.findElement(By.id(id)).sendKeys(Keys.ENTER);
        System.out.println(stepNumber + ". Set Address - City");

        stepNumber++;
        name = "locationAddress";
        driver.findElement(By.name(name)).click();
        driver.findElement(By.name(name)).clear();
        driver.findElement(By.name(name)).sendKeys("address");
        System.out.println(stepNumber + ". Set Address - Address");

        stepNumber++;
        xpath = "(.//*[normalize-space(text()) and normalize-space(.)='*Required'])[6]/following::div[2]";
        elementButton = driver.findElement(By.xpath(xpath));
        elementButton.click();
        System.out.println(stepNumber + ". Click the SAVE button");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        stepNumber++;
        xpath = "//*[@id=\"app\"]/div/div/div[2]/div[2]/div/div/div[1]/button";
        elementButton = driver.findElement(By.xpath(xpath));
        elementButton.click();
        System.out.println(stepNumber + ". Click the CREATE button");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("---Read Created Location--");
    }
        @Test

        public void Read_NewLocation() {
            stepNumber++;
            xpath = "//*[text()='" + locationName + "']";
            //"//*div[text()='" + locationName + "')]";
            // "/html/body/div[1]/div/div/div[2]/div[2]/div/div[2]/div/div/div[1]/div[2]/div[5]/div/div[2]";
            String actualString = driver.findElement(By.xpath(xpath)).getText();
            Assert.assertTrue(actualString.contains(locationName), "Location read assertion is failed!");
            System.out.println(stepNumber + ". Created location is: " + locationName);

            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Test
        public void Update_NewLocation() {

        System.out.println("Update Location");

        driver.findElement(By.xpath(xpath));
    }



    @AfterMethod
    public void teardownTest() {
        driver.close();
    }
}
