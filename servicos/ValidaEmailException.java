package servicos;

public class ValidaEmailException extends Exception {
    // metodo construtor
    public ValidaEmailException(){}
    // mensagem de erro
    @Override
    public String toString() {
        return "O seu email é inválido!";
    }
}
