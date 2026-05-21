package Tema5;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.swing.text.html.parser.Parser;
import java.time.Duration;
import java.util.List;

import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;

public class Tema_5 {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeMethod
    public void setUP() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://practicesoftwaretesting.com/");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void ex1() {
        // a. Accesează o categorie de produse.
        WebElement categoriesButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("[data-test='nav-categories']")
        ));
        categoriesButton.click();

        // b. Folosește filtrele disponibile.
        WebElement handToolsLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("[data-test='nav-hand-tools']")
        ));
        handToolsLink.click();

        // c. Accesează o categorie de produse
        WebElement hammerCheckbox = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//label[contains(text(), 'Hammer')]/input")));
        hammerCheckbox.click();

        List<WebElement> hammerProducts = driver.findElements(
                By.xpath("//h5[@data-test='product-name' and contains(normalize-space(), 'Hammer')]"));

        int count = hammerProducts.size();
        System.out.println("Found " + count + " products containing the word 'Hammer'.");

        boolean isAvailableAtLeastTwice = count >= 2;
        Assert.assertTrue(isAvailableAtLeastTwice, "There are at least two hammers.");

        // d. Verifică faptul că cel puțin două produse conțin numele categoriei selectate.
        List<WebElement> products = driver.findElements(By.cssSelector(".col-md-9 .card"));

        assertTrue(products.size() >= 2, "At least two products should be seen for the selected filter. Found: "
                + products.size());
    }

    @Test
    public void ex2() {
        // a. Deschide un produs.
        WebElement openProduct = wait.until(ExpectedConditions.elementToBeClickable
                (By.xpath("//a[@data-test='product-01KS2TZYCC96KR9VCW4NGXPJG2']")));
        openProduct.click();
        // b. Apasă Add to cart.
        WebElement addToCartProduct = wait.until(ExpectedConditions.elementToBeClickable
                (By.xpath("//button[@data-test='add-to-cart']")));
        addToCartProduct.click();
        // c. Deschide coșul.
        WebElement openCart = wait.until(ExpectedConditions.elementToBeClickable
                (By.xpath("//a[@data-test='nav-cart']")));
        openCart.click();
        // d. Verificare:
        WebElement hammer = wait.until((ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//span[@data-test='product-title' and contains(text(), 'Thor Hammer')]"))));

        WebElement quantity = wait.until((ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//tr[descendant::span[contains(text(), 'Thor Hammer')]]//input[@data-test='product-quantity']"))));

        // i. produsul apare în cos ii. cantitatea este 1.
        assertTrue(Integer.parseInt(quantity.getAttribute("value")) > 0, "There is at least a product");
    }

    @Test
    public void ex3() {
       // b. Caută produsul Hammer.
        WebElement searchProductHammer = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//input[@id='search-query']")));
        searchProductHammer.sendKeys("Hammer");

        WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable
                (By.xpath("//button[@data-test='search-submit']")));
        searchButton.click();

        // c. Deschide produsul.
        WebElement openProduct = wait.until(ExpectedConditions.elementToBeClickable
                (By.xpath("//a[@data-test='product-01KS2TZYC7R2A9J0JHAC6BY1ZX']")));
        openProduct.click();

        // d. Adaugă produsul în coș.
        WebElement addToCart = wait.until(ExpectedConditions.elementToBeClickable
                (By.xpath("//button[@data-test='add-to-cart']")));
        addToCart.click();

        // e. Deschide coșul.
        WebElement openCart = wait.until(ExpectedConditions.elementToBeClickable
                (By.xpath("//a[@data-test='nav-cart']")));
        openCart.click();

        // f. Verifică: i. produsul este în coș ii. cantitatea este 1 iii. prețul este afișat.
        WebElement hammer = wait.until((ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//span[@data-test='product-title' and contains(text(), 'Hammer')]"))));

        WebElement quantity = wait.until((ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//tr[descendant::span[contains(text(), 'Hammer')]]//input[@data-test='product-quantity']"))));
        assertTrue(Integer.parseInt(quantity.getAttribute("value")) > 0, "There is at least a product");

        WebElement price = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//span[@data-test='product-price']")));

    }
}
