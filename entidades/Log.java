package entidades;

import java.io.File;
import java.util.List;

public class Log implements Arquivo{

    // definindo atributos
    private File log;
    private int numeroCadastros;
    private int numeroPerguntas;

    // metodo construtor
    public Log () {

        this.log = new File("log.txt");

        if (log.length() == 0){
            atualizarLog("0", "4");
        }

        recuperarLog();

    }


    // metodo para manter os dados do log.txt atualizados (chama o metodo escrita da interface)
    public void atualizarLog (String nCadastros, String nPerguntas) {
        escrita("N° Cadastros: " + nCadastros + "\nN° Perguntas: " + nPerguntas, log, false);
    }


    // metodo para recuperar os doas registrados no log e armazenar nos respectivos atributos (chama o metodo leitura da interface)
    public void recuperarLog () {

        List<String> dados = leitura(log);

        setNumeroCadastros(Integer.parseInt(dados.get(0).substring(14)));
        setNumeroPerguntas(Integer.parseInt(dados.get(1).substring(14)));

    }


    // metodos get
    public File getLog() {
        return log;
    }
    public int getNumeroCadastros() {
        return numeroCadastros;
    }
    public int getNumeroPerguntas() {
        return numeroPerguntas;
    }

    // metodos set
    public void setLog(File log) {
        this.log = log;
    }
    public void setNumeroCadastros(int numeroCadastros) {
        this.numeroCadastros = numeroCadastros;
    }
    public void setNumeroPerguntas(int numeroPerguntas) {
        this.numeroPerguntas = numeroPerguntas;
    }
}
