import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // criando o txt do formulario com as perguntas
        File formulario = new File("formulario.txt");
        if (formulario.createNewFile()){
            System.out.println("Formulário de perguntas criado com sucesso!");
        }
        // inserindo as perguntas padrão
        BufferedWriter bw = new BufferedWriter(new FileWriter(formulario, true));
        bw.write("1 - Qual seu nome completo?\n");
        bw.write("2 - Qual seu email de contato?\n");
        bw.write("3 - Qual sua idade?\n");
        bw.write("4 - Qual sua altura?\n");
        bw.flush();
        bw.close();
        // criando a pasta que ira armazenar o txt de cada usuário
        File cadastros = new File("CADASTROS");
        if (cadastros.mkdir()){
            System.out.println("Pasta de cadastros criada com sucesso!");
        }
        // contabiliza o numero de cadastros no sistema
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
                case 6:
                    System.out.println("Encerrando programa");
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
        String linha;           // receberá a linha que estará sendo linda do formulario (pergunta)
        // responder formulario
        Scanner scanQuestionario = new Scanner(System.in);
        ArrayList<String> dados = new ArrayList<>(); // armazenará os dados com as respostas dos usuários
        // coletando os dados
        while((linha = br.readLine()) != null){ // le todas as perguntas do formulario e armazena os dados
            System.out.println(linha);
            System.out.print("R: ");
            dados.add(scanQuestionario.nextLine());
        }
        scanQuestionario.close();
        br.close();
        // instanciando o novo usuario e printando as informações principais
        System.out.println("Informações principais: ");
        Usuario usuario = new Usuario(dados.get(0), dados.get(1), Integer.parseInt(dados.get(2)), Double.parseDouble(dados.get(3)));
        System.out.println(usuario);
        // Utilizando stream para criar o nome de arquivo padrao
        String s = (nCadastros + 1) + "-" + dados.get(0).toUpperCase() + ".txt"; // a string "crua"
        String[] split = s.split(" ");                                     // separo ela em partes
        Optional<String> nomeArquivo = Arrays.stream(split)                      // inicio o fluxo de dados que vai retornar o nome do arquivo formatado dentro de um Optional
                .reduce((palavra1, palavra2) -> palavra1.concat(palavra2).toUpperCase());
        // Criando o txt do usuário e inserindo os dados de cadastro
        BufferedWriter bw = new BufferedWriter(new FileWriter(new File(cadastros, nomeArquivo.get()), true)); // true para apend para que não sobrescreva as informações anteriores
        // cadastrando as informações principais
        bw.write(usuario.toString());
        bw.flush();
        // stream para adicionar as informações restantes caso o usuario tenha adicionado mais perguntas
        if (dados.size() > 4){ // caso tenha mais que 4 perguntas
            dados.stream()                  // inicia i fluxo de dados
                    .skip(4)             // a partir do quarto dados
                    .forEach(dado -> {
                        try {
                            bw.write(dado); // faço a escrita do dado no txt
                            bw.newLine();
                            bw.flush();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
        }
        bw.close();
    }
    // metodo para listar usuarios cadastrados no sistema
    public static void listarCadastros(File cadastros) throws FileNotFoundException {
        BufferedReader br = new BufferedReader(new FileReader(cadastros));
        br.lines()
                .map(String::toLowerCase)
                .map(usuario -> {
                    int indice = usuario.indexOf(".txt");
                    return usuario.substring(0, indice);
                })
                .forEach(System.out::println);
    }
}