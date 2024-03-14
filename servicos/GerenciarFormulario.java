package servicos;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static servicos.Menu.formulario;
import static servicos.Menu.log;
import static servicos.Menu.scan;

public class GerenciarFormulario {


//    metodo que adiona uma nova pergunta ao formulario
    public static void adicionarPergunta () {

        // coleta a nova pergunta
        Scanner scanPergunta = new Scanner(System.in);
        System.out.print("Digite a pergunta que deseja inserir no formulário: ");
        String pergunta = scanPergunta.nextLine();

        pergunta = ((log.getNumeroPerguntas() + 1) + " - " +  pergunta + "\n"); // formata a pergunta

        formulario.escrita(pergunta, formulario.getFormulario(), true); // escreve a nova pergunta no formulario

        // contabiliza uma pergunta
        log.setNumeroPerguntas(log.getNumeroPerguntas() + 1);

    }


    // metodo para deletar pergunta do formulario
    public static void deletarPergunta() throws IOException {

        List<String> perguntas = formulario.leitura(formulario.getFormulario()); // le as perguntas do formulario

        // printa todas as perguntas no terminal
        System.out.println("==== PERGUNTAS CADASTRADAS ====");
        perguntas.stream()
                .forEach(System.out::println);

        // solicita que o usuário escolha uma pelo indice
        System.out.print("Entre com o número da pergunta que deseja deletar do formulario: ");
        int num = scan.nextInt();

        // atualiza o array de perguntas retirando a perguntas escolhida e atualizando o índice das demais
        perguntas = perguntas.stream()
                .filter(pergunta -> !pergunta.startsWith(num + " - "))      // filtra a pergunta que o usuário deseja remover]
                .map(pergunta -> {                                          // rearranja os indices das perguntas
                    int n = Character.getNumericValue(pergunta.charAt(0));  // armazena o indice da pergunta em um atributo n
                    if(n > num){                                            // se essa pergunta for posterior a pergunta deletada
                        String s = pergunta.substring(1);         // armazeno a pergunta sem o indice
                        pergunta = (n - 1) + s;                            // concateno a pergunta com seu novo indice
                    }
                    return pergunta;
                })
                .collect(Collectors.toList());

        formulario.escrita("", formulario.getFormulario(), false); // limpa o formulario

        perguntas.stream()
                .forEach(pergunta -> formulario.escrita(pergunta, formulario.getFormulario(), true)); // reescreve o formulario

       log.setNumeroPerguntas(log.getNumeroPerguntas() - 1);
    }

    
}
