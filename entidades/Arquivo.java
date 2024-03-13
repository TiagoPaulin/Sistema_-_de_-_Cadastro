package entidades;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public interface Arquivo {

    // metodo de escrita no arquivo, recebe uma linha e o arquivo na qual a linha sera escrita bem como um booleano indicando se os dados do txt serão sobrescritos ou não
    default void escrita (String linha, File arquivo, boolean sobrescrita) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo, sobrescrita))){

            bw.write(linha);
            bw.flush();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    // metodo de leitura do arquivo, recebe o arquivo a ser lido e retorna uma lista  com as linhas do arquivo
    default List<String> leitura (File arquivo) {

        List<String> linhas;

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {

            linhas = br.lines()
                    .collect(Collectors.toList());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return linhas;
    }
}
