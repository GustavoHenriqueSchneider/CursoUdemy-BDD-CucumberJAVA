package servicos;

import entidades.NotaAluguel;
import entidades.Filme;

import java.util.Calendar;

public class AluguelService {
    public NotaAluguel alugar (Filme filme, String tipoAluguel){
        if(filme.getEstoque()==0)
            throw new RuntimeException("Filme sem estoque");
        NotaAluguel nota = new NotaAluguel();
        Calendar cal = Calendar.getInstance();
            if("extendido".equals(tipoAluguel)){
                nota.setPreco(filme.getAluguel()*2);
                cal.add(Calendar.DAY_OF_MONTH,3);
                nota.setPontuacao(2);
            }
            else if("semanal".equals(tipoAluguel)){
                nota.setPreco(filme.getAluguel()*3);
                cal.add(Calendar.DAY_OF_MONTH,7);
                nota.setPontuacao(3);
            }
            else
            {
                nota.setPreco(filme.getAluguel());
                cal.add(Calendar.DAY_OF_MONTH,1);
                nota.setPontuacao(1);
            }
        nota.setDataEntrega(cal.getTime());
        filme.setEstoque(filme.getEstoque()-1);
        return nota;
    }
}
