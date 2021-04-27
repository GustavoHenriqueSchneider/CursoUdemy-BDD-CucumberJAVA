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
    private int i=1;

    @Dado("que estou acessando a aplicação")
    public void queEstouAcessandoAAplicação() {
        navegador = new ChromeDriver();
        navegador.get("https://seubarriga.wcaquino.me");
    }
    @Quando("informo o usuário {string}")
    public void informoOUsuário(String email) {
        navegador.findElement(By.id("email")).sendKeys(email);
    }
    @Quando("a senha {string}")
    public void aSenha(String senha) {
        navegador.findElement(By.id("senha")).sendKeys(senha);
    }
    @Quando("seleciono entrar")
    public void selecionoEntrar() {
        navegador.findElement(By.tagName("button")).click();
    }
    @Então("visualizo a página inicial")
    public void visualizoAPáginaInicial() {
        String saudacao = navegador.findElement(By.xpath("//div [@class=\"alert alert-success\"]")).getText();
        Assert.assertEquals("Bem vindo, Gustavo!",saudacao);
    }
    @Quando("seleciono Contas")
    public void selecionoContas() {
        navegador.findElement(By.linkText("Contas")).click();
    }
    @Quando("seleciono Adicionar")
    public void selecionoAdicionar() {
        navegador.findElement(By.linkText("Adicionar")).click();
    }
    @Quando("^informo a conta \"(.*\\s?)\"$")
    public void informoAConta(String conta) {
        navegador.findElement(By.id("nome")).sendKeys(conta);
    }
    @Quando("seleciono Salvar")
    public void selecionoSalvar() {
        navegador.findElement(By.tagName("button")).click();
    }
    @Então("a conta é inserida com sucesso")
    public void aContaÉInseridaComSucesso() {
        String texto = navegador.findElement(By.xpath("//div [@class=\"alert alert-success\"]")).getText();
        Assert.assertEquals("Conta adicionada com sucesso!",texto);
    }
    @Então("sou notificado que o nome da conta é obrigatório")
    public void souNotificadoQueONomeDaContaÉObrigatório() {
        String texto = navegador.findElement(By.xpath("//div [@class=\"alert alert-danger\"]")).getText();
        Assert.assertEquals("Informe o nome da conta",texto);
    }
    @Então("sou notificado que já existe uma conta com esse nome")
    public void souNotificadoQueJáExisteUmaContaComEsseNome() {
        String texto = navegador.findElement(By.xpath("//div [@class=\"alert alert-danger\"]")).getText();
        Assert.assertEquals("Já existe uma conta com esse nome!",texto);
    }
    @Então("^recebo a mensagem \"(.*\\s?)\"$")
    public void receboAMensagem(String string) {
        String texto = navegador.findElement(By.xpath("//div[starts-with(@class, 'alert alert-')]")).getText();
        Assert.assertEquals(string,texto);
    }

    @After (order = 1, value = "@funcionais")
    public void screenshot(){
        File file = ((TakesScreenshot)navegador).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file, new File("target/screenshots/"+Scenario.class.getName()+"."+i+".jpg"));
            i++;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @After (order = 0, value = "@funcionais")
    public void tearDown(){
        navegador.quit();
    }
}
