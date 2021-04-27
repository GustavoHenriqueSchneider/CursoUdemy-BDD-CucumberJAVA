package steps;

import entidades.Filme;
import entidades.NotaAluguel;
import io.cucumber.java.bs.A;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.junit.Assert;
import servicos.AluguelService;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class AlugarFilmeSteps {
    private Filme filme;
    private AluguelService aluguel = new AluguelService();
    private NotaAluguel nota;
    private String erro;
    private String tipoAluguel;

    @Dado("^um filme com estoque de (\\d+) unidades$")
    public void umFilmeComEstoqueDeUnidades(int int1) {
        filme = new Filme();
        filme.setEstoque(int1);
    }
    @Dado("^que o preço de aluguel seja R\\$ (\\d+)$")
    public void queOPreçoDeAluguelSejaR$(int int1) {
        filme.setAluguel(int1);
    }
    @Quando("^alugar$")
    public void alugar() {
        try{
            nota = aluguel.alugar(filme,tipoAluguel);
        }
        catch (RuntimeException e){
            erro = e.getMessage();
        }
    }
    @Dado("um filme")
    public void umFilme(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> mapa = dataTable.asMap(String.class,String.class);
        filme = new Filme();
        filme.setEstoque(Integer.parseInt(mapa.get("estoque")));
        filme.setAluguel(Integer.parseInt(mapa.get("preco")));
        tipoAluguel = mapa.get("tipo");
    }

    @Então("^o preço do aluguel será R\\$ (\\d+)$")
    public void oPreçoDoAluguelSeráR$(int int1) {
        Assert.assertEquals(int1, nota.getPreco());
    }
    @Então("^o estoque do filme será (\\d+) unidade$")
    public void oEstoqueDoFilmeSeráUnidade(int int1) {
        Assert.assertEquals(int1,filme.getEstoque());
    }

    @Então("não será possivel por falta de estoque")
    public void data() {
        Assert.assertEquals("Filme sem estoque",erro);
    }

    @Dado("que o tipo do aluguel seja {word}")
    public void queOTipoDoAluguelSeja(String tipo) {
        tipoAluguel = tipo;
    }
    @Então("^a data de entrega será em (\\d+) dias?$")
    public void aDataDeEntregaSeráEmDias(Integer int1) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH,int1);
        Date dataRetorno = nota.getDataEntrega();

        Calendar calRetorno = Calendar.getInstance();
        calRetorno.setTime(dataRetorno);

        Assert.assertEquals(cal.get(Calendar.DAY_OF_MONTH),calRetorno.get(Calendar.DAY_OF_MONTH));
    }
    @Então("^a pontuação recebida será de (\\d+) pontos$")
    public void aPontuaçãoRecebidaSeráDePontos(int int1) {
        Assert.assertEquals(int1, nota.getPontuacao());
    }
}
