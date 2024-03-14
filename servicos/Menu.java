package servicos;

import entidades.Cadastro;
import entidades.Log;
import entidades.Formulario;

import java.io.IOException;
import java.util.Scanner;

import static servicos.CadastrarUsuario.cadastrarUsuario;
import static servicos.GerenciarFormulario.deletarPergunta;
import static servicos.GerenciarFormulario.adicionarPergunta;
import static servicos.BuscarUsuario.listarCadastros;
import static servicos.BuscarUsuario.buscarCadastro;

public class Menu {

    // atributos estáticos que serão utilizados pelas classes do package servicos
    public static Formulario formulario;
    public static Cadastro cadastros;
    public static Log log;
    public static Scanner scan;


    public static void executar() throws IOException {

        formulario = new Formulario();
        cadastros = new Cadastro();
        log = new Log();
        scan = new Scanner(System.in);

        int opcao = 0;

        while(opcao != 6){

            System.out.println("========== MENU ==========");
            System.out.println("1. Cadastrar usuário");
            System.out.println("2. Listar todos os usuários cadastrados");
            System.out.println("3. Cadastrar nova pergunta no formulário");
            System.out.println("4. Deletar pergunta do formulário");
            System.out.println("5. Pesquisar usuário por nome, idade ou email");
            System.out.println("6. Encerrar programa");
            System.out.print("Selecione uma das opções: ");
            opcao = scan.nextInt();
            scan.nextLine();

            switch (opcao){
                case 1:
                    cadastrarUsuario();
                    log.setNumeroCadastros(log.getNumeroCadastros() + 1); // contabiliza um cadastro
                    break;
                case 2:
                    listarCadastros();
                    break;
                case 3:
                    adicionarPergunta();
                    log.setNumeroPerguntas(log.getNumeroPerguntas() + 1); // acrescenta uma pergunta
                    break;
                case 4:
                    deletarPergunta();
                    log.setNumeroPerguntas(log.getNumeroPerguntas() - 1); // decresce uma pergunta
                    break;
                case 5:
                    buscarCadastro();
                    break;
                case 6:
                    System.out.println("Encerrando programa");
                    log.atualizarLog(String.valueOf(log.getNumeroCadastros()), String.valueOf(log.getNumeroPerguntas())); // atualiza o log
                    scan.close();
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;

            }
        }
    }
}
