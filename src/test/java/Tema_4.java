import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class Tema_4 {
    WebDriver driver = new ChromeDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

    @BeforeMethod
    public void setUp() {
        driver.manage().window().maximize();
        driver.get("https://practicesoftwaretesting.com/");
    }

    // 1. Creează un test care:
    // a. Deschide aplicația https://practicesoftwaretesting.com/ .
    // b. Verifică titlul paginii.
    // c. Verifică dacă logo-ul aplicației este vizibil.
    @Test
    public void openWebsite() {
        String pageTitle = driver.getTitle();
        Assert.assertEquals(pageTitle, "Practice Software Testing - Toolshop - v5.0");
        WebElement logo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='navbar-brand']")));
        Assert.assertTrue(logo.isDisplayed());
    }


    // 2. Creează un test pentru căutarea unui produs
    // a. Identifică search bar.
    // b. Introdu textu “hammer”
    // c. Apasă Enter sau butonul de căutare.
    // d. Așteaptă afișarea rezultatelor.
    // e. Verifică dacă apare cel puțin un produs în listă.
    @Test
    public void searchProduct() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Search']")));

        WebElement searchBar = driver.findElement(By.xpath("//input[@placeholder='Search']"));
        searchBar.sendKeys("hammer");

        WebElement searchButton = driver.findElement(By.xpath("//button[@type='submit']"));
        searchButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-test='search_completed']")));
        WebElement list = driver.findElement(By.xpath("//div[@data-test='search_completed']/a[1]"));
        Assert.assertTrue(list.isDisplayed());

    }

    //3. Creează un test pentru deschiderea unui produs
    //a. Deschide pagina principală.
    //b. Așteaptă încărcarea produselor.
    //c. Apasă pe primul produs din listă.
    //d. Verifică dacă:
    //i. imaginea produsului este vizibilă
    //ii. butonul Add to cart este vizibil.
    @Test
    public void checkProduct() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='container']/a[@class='card'][1]")));
        WebElement firstProduct = driver.findElement(By.xpath("//div[@class='container']/a[@class='card'][1]"));
        firstProduct.click();

        WebElement image = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@class=\"figure-img img-fluid\"]")));
        Assert.assertTrue(image.isDisplayed());

        WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id=\"btn-add-to-cart\"]")));
        Assert.assertTrue(button.isDisplayed());
    }

    // 4. Creează un test pentru filtrarea produselor
    // a. Deschide pagina principală.
    // b. Selectează un filtru (ex: brand sau categorie)
    // c. Așteaptă actualizarea listei.
    // d. Verifică dacă produsele afișate respectă filtrul.
    @Test
    public void productFilter() {
        WebElement powerToolsFilter = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                "//input[@data-test='category-01KKKYTNV87FY20DGDMS6RD2MX']")));
        powerToolsFilter.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@data-test='category-01KKKYTNV87FY20DGDMS6RD2MX']")));

    }


}
