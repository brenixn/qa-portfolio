package web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CheckboxPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By checkboxes = By.cssSelector("input[type='checkbox']");

    public CheckboxPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void abrirPagina(){
        driver.get("https://the-internet.herokuapp.com/checkboxes");
    }

    public List<WebElement> obterCheckboxes(){
        return wait.until(ExpectedConditions
                .presenceOfAllElementsLocatedBy(checkboxes));
    }

    public boolean checkboxEstaMarcado(int index){
        return obterCheckboxes().get(index).isSelected();
    }

    public void clicarCheckbox(int index){
        obterCheckboxes().get(index).click();
    }
}
