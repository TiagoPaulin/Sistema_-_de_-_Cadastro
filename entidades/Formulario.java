package entidades;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class Formulario implements Arquivo {

    // definindo atributos da classe
    private File formulario;
    private List<String> perguntasIniciais =
            List.of("1 - Qual seu nome completo?\n", "2 - Qual seu e-mail?\n",
                    "3 - Qual sua idade?\n", "4 - Qual sua altura?");

    // metodo construtor
    public Formulario () {

        this.formulario = new File("formulario.txt");

        if (formulario.length() == 0){

            this.perguntasIniciais.stream()
                    .forEach(pergunta -> escrita(pergunta, formulario, true));

        }
    }

    // métodos get
    public File getFormulario() {
        return formulario;
    }
    public List<String> getPerguntasIniciais() {
        return perguntasIniciais;
    }

    // métodos set
    public void setFormulario(File formulario) {
        this.formulario = formulario;
    }
    public void setPerguntasIniciais(List<String> perguntasIniciais) {
        this.perguntasIniciais = perguntasIniciais;
    }
}
