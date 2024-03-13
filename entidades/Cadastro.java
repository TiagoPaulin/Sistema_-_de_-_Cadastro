package entidades;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Cadastro {
    // definindo atributos da classe
    private File cadastros;

    // metodo construtor
    public Cadastro () {

        this.cadastros = new File("CADASTROS");

        if (cadastros.mkdir()) {
            System.out.println("Pasta de cadastros criada com sucesso!");
        }

    }


    // metodo que adiciona o arquivo do usuário na pasta de "CADASTROS"
    public void adiciorArquivo (File arquivo) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(new File(cadastros, arquivo.getName()), true));
    }


    public File getCadastros() {
        return cadastros;
    }

    public void setCadastros(File cadastros) {
        this.cadastros = cadastros;
    }
}
