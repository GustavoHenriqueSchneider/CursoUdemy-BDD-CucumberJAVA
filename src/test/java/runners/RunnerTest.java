package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "html:target/report-html/relatorio.html", "json:target/report-json/relatorio.json"},
        glue = {"steps","config"},
        features = "src/test/resources/features/alugar_filme.feature",
        snippets = CucumberOptions.SnippetType.CAMELCASE
)

public class RunnerTest {
}
