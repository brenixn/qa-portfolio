package web;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://the-internet.herokuapp.com/login");
    }

    @Test
    public void deveLogarComSucesso() {
        WebElement campoUsuario = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("username")));
        WebElement campoSenha = driver.findElement(By.id("password"));
        WebElement botaoLogin = driver.findElement(By.cssSelector("button[type='submit']"));

        campoUsuario.sendKeys("tomsmith");
        campoSenha.sendKeys("SuperSecretPassword!");
        botaoLogin.click();

        WebElement mensagem = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("flash")));
        assertTrue(mensagem.getText().contains("You logged into a secure area!"));
    }

    @Test
    public void deveExibirErroComSenhaInvalida() {
        WebElement campoUsuario = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("username")));
        WebElement campoSenha = driver.findElement(By.id("password"));
        WebElement botaoLogin = driver.findElement(By.cssSelector("button[type='submit']"));

        campoUsuario.sendKeys("tomsmith");
        campoSenha.sendKeys("senhaerrada");
        botaoLogin.click();

        WebElement mensagem = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("flash")));
        assertTrue(mensagem.getText().contains("Your password is invalid!"));
    }

    @Test
    public void deveFalharComUsuarioInexistente() {
        WebElement campoUsuario = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("username")));
        WebElement campoSenha = driver.findElement(By.id("password"));
        WebElement botaoLogin = driver.findElement(By.cssSelector("button[type='submit']"));

        campoUsuario.sendKeys("usuarioinexistente");
        campoSenha.sendKeys("qualquersenha");
        botaoLogin.click();

        WebElement mensagem = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("flash")));
        assertTrue(mensagem.getText().contains("Your username is invalid!"));
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}