package entidades;

public class Filme {
    private int estoque,aluguel;
    public void setEstoque(int int1) {
        estoque = int1;
    }

    public void setAluguel(int int1){
        aluguel = int1;
    }

    public int getAluguel() {
        return aluguel;
    }

    public int getEstoque() {
        return estoque;
    }
}
