import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class LocationPageGridElement {

    //Create Interface
    String rootXpath = "/html/body/div[1]/div/div/div[2]/div[2]/div/div[2]/div/div/div[1]/div[2]";

    //find the locations grid

//    WebElement table = driver.findElement(By.xpath(rootXpath));

    //find the row
    public WebElement elementSearcher (String locationName, WebDriver driver) {
        locationName = "Location Name1574581701454";
        List<WebElement> tableElements = driver.findElements(By.className("rt-tr"));
        int elementsNumber = tableElements.size();
        System.out.println("The number of elements is: " + elementsNumber);
        for (WebElement webElement : tableElements) {
            System.out.println(webElement.getText() + "\n");
        }



        return null;
    }
}
