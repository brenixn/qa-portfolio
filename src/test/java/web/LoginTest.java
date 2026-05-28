package web;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import web.pages.LoginPage;

import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {

    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeEach
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        loginPage.abrirPagina();
    }

    @Test
    public void deveLogarComSucesso() {
        loginPage.fazerLogin("tomsmith", "SuperSecretPassword!");
        assertTrue(loginPage.obterMensagem()
                .contains("You logged into a secure area!"));
    }

    @Test
    public void deveExibirErroComSenhaInvalida() {
        loginPage.fazerLogin("tomsmith", "senhaerrada");
        assertTrue(loginPage.obterMensagem()
                .contains("Your password is invalid!"));
    }

    @Test
    public void deveFalharComUsuarioInexistente() {
        loginPage.fazerLogin("usuarioinexistente", "qualquersenha");
        assertTrue(loginPage.obterMensagem()
                .contains("Your username is invalid!"));
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}