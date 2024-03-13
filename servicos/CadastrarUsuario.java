package servicos;

import java.io.*;
import java.util.*;
import excecoes.*;

import static servicos.Menu.formulario;
import static servicos.Menu.cadastros;
import static servicos.Menu.log;
import static servicos.Menu.scan;
import static entidades.Usuario.MINIMO_CARACTERES;
import static entidades.Usuario.IDADE_MINIMA;
import static entidades.Usuario.REGEX_EMAIL;
import static entidades.Usuario.REGEX_ALTURA;

public class CadastrarUsuario {

    // metodo que realiza o cadastro do usuário no sistema
    public static void cadastrarUsuario() throws IOException {

        // recebendo as perguntas do formulário em uma lista
        List<String> perguntas = formulario.leitura(formulario.getFormulario());

        // armazenando as respostas do usuário
        ArrayList<String> dados = new ArrayList<>();

        perguntas.stream()
            .forEach(pergunta -> {
                String resposta;
                do {

                    System.out.println(pergunta);
                    System.out.print("R: ");
                    resposta = scan.nextLine();

                    } while (!validaResposta(pergunta, resposta));

                dados.add(resposta);
            });


    }

    // método que valida as respostas dadas pelo usuário no cadastro, caso sejam elas são adicionas no array e o array é retornado
    public static boolean validaResposta(String pergunta, String resposta){

        try {

            if (pergunta.contains("nome") && resposta.length() < MINIMO_CARACTERES){
                throw new NumCaracteresException(MINIMO_CARACTERES);
            }
            if (pergunta.contains("e-mail") && !resposta.contains(REGEX_EMAIL)){
                throw new ValidaEmailException();
            }
            if (pergunta.contains("idade") && Integer.parseInt(resposta) < IDADE_MINIMA){
                throw new MinIdadeException(IDADE_MINIMA);
            }
            if (pergunta.contains("altura") && !resposta.contains(REGEX_ALTURA)){
                throw new ValidaAlturaException();
            }

            return true;

        } catch (NumCaracteresException | ValidaEmailException | MinIdadeException | ValidaAlturaException e){

            System.out.println(e);
            return false;

        }
    }
}
