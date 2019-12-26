import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class LocationPageGridElement {
    private WebDriver driver;
    private String elementName;
    private String rootElement;
    private String operatorID;

    public WebElement elementFounder (WebDriver driver, String elementName, String rootElement, String operatorID) {
        this.driver = driver;
        this.elementName = elementName;
        this.rootElement = rootElement;
        this.operatorID = operatorID;

        List<WebElement> tableElements = driver.findElements(By.className(rootElement));
        String webElementText;
        WebElement foundElement = null;

        for (WebElement webElement : tableElements) {
            webElementText = webElement.getText();
            if (webElementText.contains(elementName)) {
                foundElement = webElement.findElement(By.className(operatorID));
                break;
            }
        }

        return foundElement;
    }

}

