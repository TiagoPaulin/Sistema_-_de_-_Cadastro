package servicos;

import java.io.*;
import java.util.*;

import entidades.Usuario;
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


    // metodo que realiza o cadastro do usuário nno sistema
    public static void cadastrarUsuario () throws IOException {

        ArrayList<String> dados = coletarDados();   // rrcebe as respostas do usuário que preencheu o formulario

        Usuario usuario = new Usuario(dados.get(0), dados.get(1), dados.get(2), dados.get(3)); // instancia usuario
        String  nomeArquivo = usuario.gerarNome(usuario.getNome(), log.getNumeroCadastros());  // gera o nome do seu registro
        usuario.setRegistro(nomeArquivo);   // cria o registro com onome gerado

        BufferedWriter bw = cadastros.cadastro(usuario.getRegistro()); // adiciona o registro do usuário na pasta de cadastros
        // escreve os dados do ususário no registro
        bw.write(usuario.toString());
        bw.flush();
        if (dados.size() > 4){ // caso o formulario tenha mais de 4 perguntas inicia uma stream para escrever o restante das respostas
            dados.stream()
                    .skip(4) // a partir da quarta
                    .forEach(dado -> {
                        try {
                            bw.write(dado);
                            bw.flush();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
        }
        bw.close();

        // printa as informações principais
        System.out.println("Informações principais: ");
        System.out.println(usuario);

        // contabiliza um cadastro
        log.setNumeroCadastros(log.getNumeroCadastros() + 1);
    }


    // metodo que exibe o formulário e retorna os dados do ususário
    public static ArrayList<String> coletarDados () {

        // recebendo as perguntas do formulário em uma lista
        List<String> perguntas = formulario.leitura(formulario.getFormulario());

        // armazenando as respostas do usuário
        ArrayList<String> dados = new ArrayList<>();

        perguntas.stream()              // fluxo de dados que realizará as perguntas e coletará as respostas de cada uma
            .forEach(pergunta -> {

                String resposta;

                do {                   // loop repetirá a pergunta até que a resposta passe na validação

                    System.out.println(pergunta);
                    System.out.print("R: ");
                    resposta = scan.nextLine();

                    } while (!validaResposta(pergunta, resposta));    // metodo que verifica a resposta do usuário

                dados.add(resposta);
            });

        return dados;   // retorna os dados coletados

    }


    // método que valida as respostas dadas pelo usuário no cadastro
    public static boolean validaResposta (String pergunta, String resposta) {

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

            return true;    // se passarem nas validações retorna true

        } catch (NumCaracteresException | ValidaEmailException | MinIdadeException | ValidaAlturaException e){

            System.out.println(e);
            return false;           // se a resposta não passar retorna false e a pergunta é feita novamente

        }
    }


}
