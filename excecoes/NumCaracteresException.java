package excecoes;

public class NumCaracteresException extends Exception{
    // atributo da classe
    private int minimo;
    // metodo construtor
    public NumCaracteresException(int minimo){
        this.minimo = minimo;
    }
    // mensagem de erro
    @Override
    public String toString() {
        return "O nome do cadastro deve ter no minimo " + minimo + " caracteres!";
    }
}
