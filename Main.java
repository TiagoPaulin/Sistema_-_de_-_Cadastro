import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        // criando o txt do formulario com as perguntas
        File formulario = new File("formulario.txt");
        if (formulario.createNewFile()){
            System.out.println("Formulário de perguntas criado com sucesso!");
        }
        //perguntas default
        BufferedWriter bw = new BufferedWriter(new FileWriter(formulario));
        bw.write("1 - Qual seu nome completo?\n");
        bw.write("2 - Qual seu email de contato?\n");
        bw.write("3 - Qual sua idade?\n");
        bw.write("4 - Qual sua altura?\n");
        bw.flush();
        bw.close();
        // criando o txt dos cadastros realizados
        File cadastros = new File("cadastros.txt");
        if(cadastros.createNewFile()){
            System.out.println("Formulario de cadastros criado com sucesso!");
        }
        int nCadastros = 0;

        // menu de execucao
        int opcao = 0;
        Scanner scanMenu = new Scanner(System.in);
        while(opcao != 6){
            System.out.println("==== MENU ====");
            System.out.println("1. Cadastrar usuário");
            System.out.println("2. Listar todos os usuários cadastrados");
            System.out.println("3. Cadastrar nova pergunta no formulário");
            System.out.println("4. Deletar pergunta do formulário");
            System.out.println("5. Pesquisar usuário pro nome, idade ou email");
            System.out.println("6. Encerrar programa");
            System.out.print("Selecione uma das opções: ");
            opcao = scanMenu.nextInt();
            switch (opcao){
                case 1:
                    cadastrarUsuario(formulario, cadastros, nCadastros);
                    nCadastros ++;
                    break;
                case 2:
                    listarCadastros(cadastros);
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
        }
    }
    // metodo para cadastrar usuarios no sistema
    public static void cadastrarUsuario(File formulario, File cadastros, int nCadastros) throws IOException {
        // ler formulario
        BufferedReader br = new BufferedReader(new FileReader(formulario));
        String linha;
        // responder questionario
        Scanner scanQuestionario = new Scanner(System.in);
        ArrayList<String> dados = new ArrayList<>();
        // coletando os dados
        while((linha = br.readLine()) != null){
            System.out.println(linha);
            System.out.print("R: ");
            dados.add(scanQuestionario.nextLine());
        }
        scanQuestionario.close();
        br.close();
        // instanciando o novo usuario
        System.out.println("Informações principais: ");
        System.out.println(new Usuario(dados.get(0), dados.get(1), Integer.parseInt(dados.get(2)), Double.parseDouble(dados.get(3))).toString());
        // registrando o usuário no txt de cadastros
        BufferedWriter bw = new BufferedWriter(new FileWriter(cadastros, true));
        bw.write((nCadastros + 1) + "-" + dados.get(0).toUpperCase() + ".TXT");
        bw.newLine();
        bw.flush();
        bw.close();
    }
    // metodo para listar usuarios cadastrados no sistema
    public static void listarCadastros(File cadastros) throws FileNotFoundException {
        BufferedReader br = new BufferedReader(new FileReader(cadastros));
        br.lines()
                .map(String::toLowerCase)
                .map(usuario -> {
                    StringBuilder formatado = new StringBuilder();
                    String[] split = usuario.split(" ");
                    for (String nome : split){
                        if (nome.isEmpty()){
                            String inicial = nome.substring(0).toUpperCase();
                            String resto = nome.substring(1);
                            if (!nome.equals(".txt")){
                                formatado.append(inicial).append(resto).append(" ");
                            }
                        }
                    }
                    return formatado;
                })
                .forEach(System.out::println);
    }
}