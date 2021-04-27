package steps;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;

public class InserirContasSteps {
    private WebDriver navegador;

    @Dado("que desejo adicionar uma conta")
    public void queDesejoAdicionarUmaConta() {
        navegador = new ChromeDriver();
        navegador.get("https://seubarriga.wcaquino.me/login");
        navegador.findElement(By.id("email")).sendKeys("seila@gmail.com");
        navegador.findElement(By.id("senha")).sendKeys("g123456");
        navegador.findElement(By.tagName("button")).click();
        navegador.findElement(By.linkText("Contas")).click();
        navegador.findElement(By.linkText("Adicionar")).click();
    }

    @Quando("adiciono a conta {string}>")
    public void adicionoAConta(String string) {
        navegador.findElement(By.id("nome")).sendKeys(string);
        navegador.findElement(By.tagName("button")).click();
    }

    @Então("^recebo a mensagem \"(.*\\s?)\"$")
    public void receboAMensagem(String string) {
        String texto = navegador.findElement(By.xpath("//div[starts-with(@class, 'alert alert-')]")).getText();
        Assert.assertEquals(string,texto);
    }

    @After (order = 1, value = "@funcionais")
    public void screenshot(Scenario cenario){
        File file = ((TakesScreenshot)navegador).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file, new File("target/screenshots/"+cenario.getId()+".jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @After (order = 0, value = "@funcionais")
    public void tearDown(){
        navegador.quit();
    }
}
