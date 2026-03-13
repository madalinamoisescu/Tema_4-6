import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class Waits {

    WebDriver driver = new ChromeDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    @Test
    public void testWait() {

        driver.get("https://demoqa.com/dynamic-properties");

        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.id("enableAfter")));

        button.click();
    }

    @Test
    public void testElementVisible(){
        //visibleAfter
        driver.get("https://demoqa.com/dynamic-properties");

        WebElement buttonVisible = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("visibleAfter")));

        boolean isDisplayed = buttonVisible.isDisplayed();

        Assert.assertTrue(isDisplayed, "Button is not visible after 5 seconds");

    }

    @Test
    public void testTextElementIsPresent() {
        driver.get("https://demoqa.com/dynamic-properties");

        boolean textOnButtonPresent = wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("visibleAfter"), "Visible After 5 Seconds"));

        Assert.assertTrue(textOnButtonPresent, "Text is not visible or correct");
    }
}
