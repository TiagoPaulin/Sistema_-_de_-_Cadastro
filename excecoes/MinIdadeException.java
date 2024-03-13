package excecoes;

public class MinIdadeException extends Exception {
    // atributo da classe
    private int minimo;
    // metodo construtor
    public MinIdadeException(int minimo){
        this.minimo = minimo;
    }
    // mensagem de erro
    @Override
    public String toString() {
        return "Não é possível cadastrar menores de " + minimo + " anos";
    }
}
