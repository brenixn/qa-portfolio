package bdd.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import web.pages.LoginPage;

import static org.junit.jupiter.api.Assertions.*;

public class LoginSteps {

    private WebDriver driver;
    private LoginPage loginPage;

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
    }

    @Given("que estou na página de login")
    public void queEstouNaPaginaDeLogin() {
        loginPage.abrirPagina();
    }

    @When("faço login com usuário {string} e senha {string}")
    public void facoLoginComUsuarioESenha(String usuario, String senha) {
        loginPage.fazerLogin(usuario, senha);
    }

    @Then("devo ver a mensagem {string}")
    public void devoVerAMensagem(String mensagemEsperada) {
        assertTrue(loginPage.obterMensagem().contains(mensagemEsperada));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}