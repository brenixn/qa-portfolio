package web;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import web.pages.CheckboxPage;

import static org.junit.jupiter.api.Assertions.*;

public class CheckboxTest {

    private WebDriver driver;
    private CheckboxPage checkboxPage;

    @BeforeEach
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        checkboxPage = new CheckboxPage(driver);
        checkboxPage.abrirPagina();
    }

    @Test
    public void primeiroCheckboxDeveEstarDesmarcado(){
        assertFalse(checkboxPage.checkboxEstaMarcado(0));
    }

    @Test
    public void segundoCheckboxDeveEstarMarcado(){
        assertTrue(checkboxPage.checkboxEstaMarcado(1));
    }

    @Test
    public void deveMarcaPrimeiroCheckbox(){
        checkboxPage.clicarCheckbox(0);
        assertTrue(checkboxPage.checkboxEstaMarcado(0));
    }

    @Test
    public void deveDesmarcaSegundoCheckbox(){
        checkboxPage.clicarCheckbox(1);
        assertFalse(checkboxPage.checkboxEstaMarcado(1));
    }

    @AfterEach
    public void tearDown(){
        if (driver != null){
            driver.quit();
        }
    }
}
