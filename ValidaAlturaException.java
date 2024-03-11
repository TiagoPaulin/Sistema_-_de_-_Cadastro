public class ValidaAlturaException extends  Exception{
    // metodo construtor
    public ValidaAlturaException(){}
    @Override
    public String toString() {
        return "Voce deve digitar sua altura utilizando ',' para separa a casa de metros da casa de centímetros";
    }
}
