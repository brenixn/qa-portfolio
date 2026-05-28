package web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    //Localizar elementos
    private By campoUsuario = By.id("username");
    private By campoSenha = By.id("password");
    private By botaoLogin = By.cssSelector("button[type='submit']");
    private By mensagemFlash = By.id("flash");

    //Construtor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    //Ações
    public void abrirPagina(){
        driver.get("https://the-internet.herokuapp.com/login");
    }

    public void preencherUsuario(String usuario){
        wait.until(ExpectedConditions.visibilityOfElementLocated(campoUsuario))
                .sendKeys(usuario);
    }

    public void preencherSenha(String senha){
        driver.findElement(campoSenha).sendKeys(senha);
    }

    public void clicarLogin(){
        driver.findElement(botaoLogin).click();
    }

    public String obterMensagem(){
        return wait.until(ExpectedConditions
                .visibilityOfElementLocated(mensagemFlash))
                .getText();
    }

    //Ação composta - faz login completo em uma linha
    public void fazerLogin(String usuario, String senha){
        preencherUsuario(usuario);
        preencherSenha(senha);
        clicarLogin();
    }
}
