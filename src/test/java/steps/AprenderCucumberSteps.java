package steps;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.junit.Assert;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AprenderCucumberSteps {
    @Dado("que criei o arquivo corretamente")
    public void queCrieiOArquivoCorretamente() {

    }
    @Quando("executá-lo")
    public void executáLo() {

    }
    @Então("a especificação deve finalizar com sucesso")
    public void aEspecificaçãoDeveFinalizarComSucesso() {

    }
    private int contador = 0;
    @Dado("que o valor do contador é {int}")
    public void queOValorDoContadorÉ(Integer int1) {
        contador = int1;
    }
    @Quando("eu incrementar em {int}")
    public void euIncrementarEm(Integer int1) {
        contador+=int1;
    }
    @Então("o valor do contador será {int}")
    public void oValorDoContadorSerá(Integer int1) {
        Assert.assertEquals((long) int1, (long) contador);
    }
    Date entrega = new Date();

    @Dado("que a entrega é dia {data}")
    public void queAEntregaÉDia(Date data) {
        entrega = data;
    }
    @Quando("^a entrega atrasar em (\\d+) (.+)$")
    public void aEntregaAtrasarEmDias(int tempoAtraso, String tempo) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(entrega);
            if(tempo.equals("dias") || tempo.equals("dia"))
                cal.add(Calendar.DAY_OF_MONTH,tempoAtraso);
            else if(tempo.equals("meses") || tempo.equals("mes"))
                cal.add(Calendar.MONTH,tempoAtraso);
            else if(tempo.equals("anos") || tempo.equals("ano"))
                cal.add(Calendar.YEAR,tempoAtraso);
        entrega = cal.getTime();
    }
    @Então("^a entrega será efetuada em (\\d{2}\\/\\d{2}\\/\\d{4})$")
    public void aEntregaSeráEfetuadaEm(String data) {
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String dataFormatada = format.format(entrega);
        Assert.assertEquals(data,dataFormatada);
    }
    @Dado("^que o ticket( especial)? é (A.\\d{3})$")
    public void queOTicketÉA(String tipo, String ticket) {

    }
    @Dado("^que o valor da passagem é R\\$ (\\d+),(\\d+)$")
    public void queOValorDaPassagemÉR$(int arg1, int arg2) {

    }
    @Dado("^que o nome do passageiro é \"(.*){20}\"$")
    public void queONomeDoPassageiroÉ(String string) {

    }
    @Dado("^que o telefone do passageiro é (9\\d{3}-\\d{4})$")
    public void queOTelefoneDoPassageiroÉ(String telefone) {

    }
    @Quando("^criar os steps$")
    public void criarOsSteps() {

    }
    @Então("^o teste vai funcionar$")
    public void oTesteVaiFuncionar() {

    }
}
