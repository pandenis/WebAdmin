import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class LocationPageGridElement {

    //Create Interface
    String rootXpath = "/html/body/div[1]/div/div/div[2]/div[2]/div/div[2]/div/div/div[1]/div[2]";
    WebElement row = null;

    //find the locations grid

//    WebElement table = driver.findElement(By.xpath(rootXpath));

    //find the row
    public void elementSearcher (String locationName, WebDriver driver) {
        //locationName = "Location Name1574581701454";
        List<WebElement> tableElements = driver.findElements(By.className("rt-tr-group"));
        int elementsNumber = tableElements.size();
        System.out.println("The number of elements is: " + elementsNumber + "\n");

        for (WebElement webElement : tableElements) {
            String webElementText = webElement.getText();
            System.out.println(webElementText + "\n");
            if (webElementText.contains(locationName)) {
                webElement.findElement(By.className("Table-optionDotsButton")).click();
                String xpath = "//div[text()=' Edit ']";
                WebElement optionMenuButtonsEdit = null;
                optionMenuButtonsEdit.findElement(By.xpath(xpath)).click();
            }


        }





    }
}
