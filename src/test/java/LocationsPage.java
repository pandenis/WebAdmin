import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class LocationsPage {

    public WebDriver driver;

    public static String filePath = "C:\\Temp\\Sel\\InputData";
    public static String fileName = "Input.xlsx";

    int stepNumber = 0;
    String xpath = null;
    String locationName = "A_Location Name" + System.currentTimeMillis();

    ReadInputData readInputData = new ReadInputData();
    WebElement elementButton;

    @BeforeClass
    public void setupTest() throws IOException {

        InputData inputData = new InputData();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to(inputData.getTestURL());
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        ReadInputData readInputData = new ReadInputData();

        String title = driver.getTitle();
        System.out.println("Page Title is " + title);
        Assert.assertEquals(title, "ContinUse", "Title assertion is failed!");

        String uname = readInputData.readExcel(filePath, fileName, 0, 0, 1);
        WebElement usernameElement = driver.findElement(By.name("email"));
        usernameElement.sendKeys(uname);
        System.out.println(". Set username");

        String psswd = readInputData.readExcel(filePath, fileName, 0, 1, 1);
        WebElement passwordElement = driver.findElement(By.name("password"));
        passwordElement.sendKeys(psswd);
        System.out.println( ". Set password");

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        String xpath = "//button[text()='LOG IN']";
        WebElement elementButton = driver.findElement(By.xpath(xpath));
        elementButton.click();
        System.out.println(". Click button 'LOG IN'");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        xpath = "//div[@class='breadCrumbs-Wrapper']//a[1]";
        String actualString = driver.findElement(By.xpath(xpath)).getText();
        String header = "Locations";
        System.out.println(". Page header is: " + actualString);
        Assert.assertTrue(actualString.contains(header), "Page Header assertion is failed!");
    }

    @Test(priority = 1)
    public void Create_NewLocation() {
        WebElement elementButton;

        System.out.println("---Create Location---");

        stepNumber++;
        xpath = "//button[text()='CREATE NEW +']";
        driver.findElement(By.xpath(xpath)).click();
        System.out.println(stepNumber + ". Click the \"CREATE NEW\" button");

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
        xpath = "(//div[text()='SAVE'])";
        elementButton = driver.findElement(By.xpath(xpath));
        elementButton.click();
        System.out.println(stepNumber + ". Click the \"SAVE\" button");

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

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        stepNumber++;
        xpath = "(.//*[normalize-space(text()) and normalize-space(.)='*Required'])[6]/following::div[2]";
        String className = "genericFormFooter-SaveButton-enabled";
        elementButton = driver.findElement(By.xpath(xpath));
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        elementButton.click();
        System.out.println(stepNumber + ". Click the \"SAVE\" button");


        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        stepNumber++;

        xpath = "//button[text()='CREATE']";
        elementButton = driver.findElement(By.xpath(xpath));
        elementButton.click();
        System.out.println(stepNumber + ". Click the \"CREATE\" button");
        System.out.println("Location Name is: " + locationName + ". Created!");



        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
     @Test(priority = 2)
    public void Read_NewLocation() {
            System.out.println("\n---Read Created Location---");
            stepNumber++;
            xpath = "//div[contains(@class,'rt-td') and contains(text(),'" + locationName + "')]";
            String actualString = driver.findElement(By.xpath(xpath)).getText();
            Assert.assertTrue(actualString.contains(locationName), "Location read assertion is failed!");
            System.out.println(stepNumber + ". Created location is: " + locationName);

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    @Test(priority = 3, enabled = false)
    public void Update_NewLocation() {

            System.out.println("\n---Update Location---");



            List<WebElement> tableElements = driver.findElements(By.className("rt-tr-group"));

            for (WebElement webElement : tableElements) {
                String webElementText = webElement.getText();
                if (webElementText.contains(locationName)) {
                    webElement.findElement(By.className("Table-optionDotsButton")).click();
                    String xpath = "//div[text()=' Edit ']";
                    webElement.findElement(By.xpath(xpath)).click();
                    break;
                }

            }

            stepNumber++;
            locationName = locationName + " An Updated Location";
            String name = "locationName";
            WebElement locationNameElement = driver.findElement(By.name(name));
            driver.findElement(By.name(name)).clear();
            locationNameElement.sendKeys(locationName);
            System.out.println(stepNumber + ". Set Location Name");

            xpath = "(//div[text()='SAVE'])";
            elementButton = driver.findElement(By.xpath(xpath));
            elementButton.click();
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            System.out.println(stepNumber + ". Click the \"SAVE\" button");

            stepNumber++;
            xpath = "(.//*[normalize-space(text()) and normalize-space(.)='*Required'])[6]/following::div[2]";
            elementButton = driver.findElement(By.xpath(xpath));
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            elementButton.click();
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            System.out.println(stepNumber + ". Click the \"SAVE\" button");

            stepNumber++;
            xpath = "//button[text()='UPDATE']";
            elementButton = driver.findElement(By.xpath(xpath));
            elementButton.click();
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            System.out.println(stepNumber + ". Click the \"UPDATE\" button");

            List<WebElement> updatedTableElements = driver.findElements(By.className("rt-tr-group"));
            for (WebElement webElement : updatedTableElements) {
                String webElementText = webElement.getText();
                if (webElementText.equals(locationName)) {
                    System.out.println(stepNumber + ". Updated location is: " + locationName );
                    break;
                } else {
                    System.out.println(stepNumber + ". Updated location not found");
                }
            }
    }

    @Test(priority = 4)
    public void Delete_NewLocation() {
        System.out.println("\n---Delete Location---");
        //Open Table-EntityOptionMenuButtonText "3 dots"
        stepNumber++;
        driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);

        LocationPageGridElement gridElement = new LocationPageGridElement();
        String rootElement = "rt-tr-group";
        String operatorID = "Table-optionDotsButton";
        gridElement.elementFounder(driver, locationName, rootElement, operatorID).click();


        driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
        xpath = "(//div[@class='Table-EntityOptionMenuButtonText'])[1]";
        driver.findElement(By.xpath(xpath)).click();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

/*
        String warningButtonText = "Are you sure you want to delete the location \"" + locationName + "\"?";
        elementButton = driver.findElement(By.id("alert-dialog-slide-description"));
        String actual = elementButton.getText();
        Assert.assertTrue(warningButtonText.equals(actual), "Text Not Found!");
*/
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        String xpath = "//span[text()='DELETE']";
        driver.findElement(By.xpath(xpath)).click();
        //Click delete button

        System.out.println(stepNumber + ". Click \"Delete\" in  the menu pop-up window");

       //Verify that confirmation pop-up window appears
/*        stepNumber++;
        xpath = "//p[text()='Are you sure you want to delete the location \"" + locationName +"\"?']";
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String text = "Are you sure you want to delete the location \"" + locationName + "\"?" ;
        String  buttonText = driver.findElement(By.xpath(xpath)).getText();
        Assert.assertTrue(buttonText.equals(text), "Button text not found!");
        System.out.println(stepNumber + ". Deletion validation message are displaying!");*/


        //Validate that entity deleted
/*        stepNumber++;
        List<WebElement> notDeletedTableElements = driver.findElements(By.className("rt-tr-group"));
        for (WebElement webElement : notDeletedTableElements) {
            webElementText = webElement.getText();
            if (!webElementText.equals(locationName)) {
                System.out.println(stepNumber + ". Deleted location is: " + locationName );
                break;
            } else {
                System.out.println(stepNumber + ". Deleted location found");
            }
        }*/

        WebElement deletedGridElement = gridElement.elementFounder(driver, locationName, rootElement, operatorID);
        Assert.assertEquals(!deletedGridElement.getText().equals(locationName), "Location" + locationName + " was deleted");
        
    }

    @AfterClass
    public void teardownTest() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.close();
    }
}
