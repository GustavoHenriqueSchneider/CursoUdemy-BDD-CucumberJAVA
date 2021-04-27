package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "html:target/report-html/relatorio.html", "json:target/report-json/relatorio.json"},
        glue = {"steps","config"},
        features = "src/test/resources/features/",
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        dryRun = false,
        tags = "@funcionais"
)

public class RunnerFuncionalTest {
    @BeforeClass
    public static void reset(){
        WebDriver navegador = new ChromeDriver();
        navegador.get("https://seubarriga.wcaquino.me");
        navegador.findElement(By.id("email")).sendKeys("seila@gmail.com");
        navegador.findElement(By.id("senha")).sendKeys("g123456");
        navegador.findElement(By.tagName("button")).click();
        navegador.findElement(By.linkText("reset")).click();
        navegador.quit();
    }
}
