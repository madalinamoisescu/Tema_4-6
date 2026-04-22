import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Tema_6 {
    WebDriver driver;
    WebDriverWait wait;

    // 1. Creează un test care:

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    //■ deschide un site
    public void openWebsite() {
        driver.get("https://www.google.com/");
        String firstTitle = driver.getTitle();

    //■ deschide un tab nou (WindowType.TAB)
        driver.switchTo().newWindow(WindowType.TAB);

    //■ accesează alt site în noul tab
        driver.get("https://www.wikipedia.org");
        String secondTitle = driver.getTitle();

    //■ Afișează titlul ambelor taburi
        System.out.println("Tab 1 Title: " + firstTitle);
        System.out.println("Tab 2 Title: " + secondTitle);

    }


    //2. Creează un test care:

    @Test
    public void secondTest() {
        //● deschide 2 taburi
        driver.get("https://www.emag.ro");
        String firstTitleTab = driver.getWindowHandle();

        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://www.wikipedia.org");
        String secondTitleTab = driver.getWindowHandle();

        //● salvează windowHandles
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());

        //● navighează între ele
        String idTab1 = tabs.get(0);
        String idTab2 = tabs.get(1);

        //● Verifică titlul fiecărui tab folosind Assert
        driver.switchTo().window(idTab1);
        System.out.println("Suntem pe primul tab: " + driver.getTitle());
        Assert.assertTrue(driver.getTitle().contains(firstTitleTab), "Titlul primului tab este incorect");

        driver.switchTo().window(idTab2);
        System.out.println("Suntem pe al doilea tab: " + driver.getTitle());
        Assert.assertTrue(driver.getTitle().contains(secondTitleTab), "Titlul celui de-al doilea tab este incorect");

    }

    //Creează un test care:

    //● Ruleaza testul folosing fisier .xml
    @Test
    public void thirdTest() {
        //● deschide 2 taburi
        driver.get("https://www.google.com");
        String handlePrincipal = driver.getWindowHandle();
        String titluAsteptat = "Google";

        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://www.wikipedia.org");
        String handleSecundar = driver.getWindowHandle();


        //● revine pe tabul principal
        driver.switchTo().window(handlePrincipal);

        //● închide unul cu driver.close()
        driver.get(handleSecundar);
        driver.close();

        //● Verifică dacă încă ești pe pagina corectă
        String titlulCurent = driver.getTitle();
        System.out.println("Titlul dupa revenire: " + titlulCurent);
        Assert.assertTrue(titlulCurent.contains(titluAsteptat), "Nu am revenit pe pagina corecta");

        //● Ruleaza testul folosing fisier .xml

    }

    //4. Creează un test care:
    //1. Deschide https://practicesoftwaretesting.com/
    //2. Navighează pe o categorie
    //3. Deschide produsul într-un tab nou
    //4. Revine în tabul principal
    //5. Face refresh
    //6. Adauga:
    //a. @BeforeMethod
    //b. @AfterMethod
    //c. Assert
    //7. Ruleaza testul folosind un fisier .xml
    @Test
    public void fourthTest() {
        driver.get("https://practicesoftwaretesting.com/");
        String handlePrincipal = driver.getWindowHandle();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-test='nav-hand-tools']")));
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://practicesoftwaretesting.com/product/01KMZ9V9QYQY4BY8505AVNJP1S");




    }


}
