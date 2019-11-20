import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

public interface TS {

    int stepNumber = 0;
    public WebDriver driver = null;
    public String testURL = "https://www.cu-bx.com/";

    @BeforeMethod
    public static void setupTest() {

    }


    @AfterClass
    public static void teardownTest() {
        if (driver != null) {
            driver.quit();
        }
    };
}
