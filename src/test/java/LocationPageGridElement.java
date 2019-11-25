import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LocationPageGridElement {

    //Create Interface
    String rootXpath = "/html/body/div[1]/div/div/div[2]/div[2]/div/div[2]/div/div/div[1]/div[2]";

    //find the locations grid
    WebDriver driver;
    WebElement table = driver.findElement(By.xpath(rootXpath));

    //find the row
    public WebElement elementSearcher (String locationName) {
        String xpath = "/html/body/div[1]/div/div/div[2]/div[2]/div/div[2]/div/div/div[1]/div[2][text()='" + locationName + "']";
        WebElement location = table.findElement(By.xpath(xpath)).getAttribute("");
        return location;
    }
}
