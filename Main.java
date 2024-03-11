import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
public class Main {
    // main
    public static void main(String[] args) throws IOException {
        // criando o txt do formulario com as perguntas
        File formulario = new File("formulario.txt");
        if (formulario.createNewFile()){
            System.out.println("Formulário de perguntas criado com sucesso!");
        }
        // inserindo as perguntas padrão
        if (formulario.length() == 0){
            BufferedWriter bw = new BufferedWriter(new FileWriter(formulario, true));
            bw.write("1 - Qual seu nome completo?\n");
            bw.write("2 - Qual seu email de contato?\n");
            bw.write("3 - Qual sua idade?\n");
            bw.write("4 - Qual sua altura?\n");
            bw.flush();
            bw.close();
        }
        // criando log de cadastro
        File logCadastro = new File("log.txt");
        if (logCadastro.createNewFile()){
            System.out.println("Arquivo de log criado com sucesso!");
        }
        // log inicial
        if (logCadastro.length() == 0) {
            BufferedWriter bw = new BufferedWriter(new FileWriter(logCadastro));
            bw.write("0");
            bw.flush();
            bw.close();
        }
        // criando a pasta que ira armazenar o txt de cada usuário
        File cadastros = new File("CADASTROS");
        if (cadastros.mkdir()){
            System.out.println("Pasta de cadastros criada com sucesso!");
        }
        // contabiliza o numero de cadastros no sistema e o numero de perguntas do formulario
        BufferedReader br = new BufferedReader(new FileReader(logCadastro));
        int nCadastros = Integer.parseInt(br.readLine()); // pega o numero de usuarios cadastrados no sistema ao inicio da execucao
        int nPerguntas = 4;
        // menu de execucao
        int opcao = 0;
        Scanner scanMenu = new Scanner(System.in);
        while(opcao != 6){
            System.out.println("==== MENU ====");
            System.out.println("1. Cadastrar usuário");
            System.out.println("2. Listar todos os usuários cadastrados");
            System.out.println("3. Cadastrar nova pergunta no formulário");
            System.out.println("4. Deletar pergunta do formulário");
            System.out.println("5. Pesquisar usuário por nome, idade ou email");
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
                    adicionarPergunta(formulario, nPerguntas);
                    nPerguntas ++;
                    break;
                case 4:
                    deletarPergunta(formulario);
                    nPerguntas --;
                    break;
                case 5:
                    buscarCadastro(cadastros);
                    break;
                case 6:
                    System.out.println("Encerrando programa");
                    BufferedWriter bw = new BufferedWriter(new FileWriter(logCadastro));
                    bw.write(String.valueOf(nCadastros));
                    bw.flush();
                    bw.close();
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
        br.close();
        // verificando se o email ja esta cadastrado no sistema
        if (emailCadastrado(dados.get(1), cadastros)){
            System.out.println("O email inserido ja esta cadastrado no sistema");
            System.exit(1);
        }
        // instanciando o novo usuario e printando as informações principais
        Usuario usuario = new Usuario(dados.get(0), dados.get(1), dados.get(2), dados.get(3));
        System.out.println("Informações principais: ");
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
                            bw.write("\n" + dado); // faço a escrita do dado no txt
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
    public static void listarCadastros(File cadastros){
        File[] arquivos = cadastros.listFiles(); // lista todos os arquivos da pasta de cadastros
        List<String> nomes = Arrays.stream(arquivos)// inicia o fluxo de dados que retornara a lista com os nomes de cada usuario cadastrado
                .map(arquivo -> {
                    String nome;  // atributo que ira receber o nome do ususario
                    try (BufferedReader br = new BufferedReader(new FileReader(arquivo))){
                        br.readLine();    // pula a primeira linha
                        nome = br.readLine(); // le a linha com o nome
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    return nome;
                })
                .collect(Collectors.toList()); // retorna os dados obtidos em forma de lista
        nomes.stream() // inicia o fluxo de dados que ira printar a lista de nomes
                .forEach(System.out::println);

    }
    // metodo para adicionar pergunta no formulario
    public static void adicionarPergunta(File formulario, int nPerguntas) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(formulario, true));
        Scanner scanPergunta = new Scanner(System.in);
        System.out.print("Digite a pergunta que deseja inserir no formulário: ");
        String pergunta = scanPergunta.nextLine();
        bw.write((nPerguntas + 1) + " - " +  pergunta + "\n");
        bw.flush();
        bw.close();
    }
    // metodo para deletar pergunta do formulario
    public static void deletarPergunta(File formulario) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(formulario));
        List<String> perguntas = new ArrayList<>();  // array que vai armazenar as perguntas do formulario
        System.out.println("==== PERGUNTAS CADASTRADAS ====");
        br.lines() // inicia o fluxo de dados
                .forEach(linha -> {
                    perguntas.add(linha); // armazena a pergunta no array
                    System.out.println(linha); // printa a pergunta no terminal
                });
        br.close();
        Scanner scanNumPergunta = new Scanner(System.in);
        System.out.print("Entre com o número da pergunta que deseja deletar do formulario: ");
        int num = scanNumPergunta.nextInt();
        BufferedWriter bw = new BufferedWriter(new FileWriter(formulario));
        perguntas.stream() // fluxo de dados para reescrever as perguntas no formulário sem a pergunta removida
                .filter(pergunta -> !pergunta.startsWith(num + " - ")) // filtra a pergunta que o usuário deseja remover]
                .map(pergunta -> { // rearranja os indices das perguntas
                    int n = Character.getNumericValue(pergunta.charAt(0)); // armazena o indice da pergunta em um atributo num
                    if(n > num){ // se essa perguntas for posterior a pergunta deletada
                        String s = pergunta.substring(1); // armazeno a pergunta sem o indice
                        pergunta = (n - 1) + s; // concateno a pergunta com seu novo indice
                    }
                    return pergunta; // retorno a pergunta
                })
                .forEach(pergunta -> { // escreve pergunta por pergunta no formulario
                    try {
                        bw.write(pergunta + "\n");
                        bw.flush();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
        bw.close();
    }
    // metodo para buscar usuarios cadastrados
    public static void buscarCadastro(File cadastros){
        File[] arquivos = cadastros.listFiles();
        Scanner scanBusca = new Scanner(System.in);
        System.out.println("Como voce deseja realizar a busca?");
        System.out.println("1. Nome");
        System.out.println("2. Idade");
        System.out.println("3. Email");
        System.out.print("Selecione uma opção: ");
        int opcaoBusca = scanBusca.nextInt();
        switch (opcaoBusca){
            case 1:
                buscaNome(arquivos);
                break;
            case 2:
                buscaIdade(arquivos);
                break;
            case 3:
                buscaEmail(arquivos);
                break;
            default:
                System.out.println("Opção inválida");
                break;
        }
    }
    // busca por nome
    public static void buscaNome(File[] arquivos){
        Scanner scan = new Scanner(System.in);
        System.out.print("Digite o nome da pessoa: ");
        String nome = scan.nextLine();
        List<File> filtrados = Arrays.stream(arquivos) // retorna uma lista com os arquivos filtrados baseados na busca
                .filter(arquivo -> {
                    try (BufferedReader br = new BufferedReader(new FileReader(arquivo))){
                        br.readLine();
                        String nomeArquivo = br.readLine();
                        return (nomeArquivo.toLowerCase().startsWith((" Nome: " + nome).toLowerCase())) || nomeArquivo.equalsIgnoreCase(" Nome: " + nome) || nomeArquivo.contains(nome); // verifica se o arquivo esta com o nome inserido na busca
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList()); // retorna em forma de lista
        if (filtrados.isEmpty()){
            System.out.println(" ");
            System.out.println("========================================");
            System.out.println("NENHUM REULTADO ENCONTRADO PARA: " + nome);
            System.out.println("========================================");
        } else {
            System.out.println(" ");
            System.out.println("========================================");
            System.out.println("RESULTADOS PARA: " + nome);
            System.out.println("========================================");
            filtrados.stream() // printa as informações dos arquivos filtrados no terminal
                    .forEach(arquivo -> {
                        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))){
                            String linha;
                            while ((linha = br.readLine()) != null){
                                System.out.println(linha);
                            }
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
        }
    }
    // busca por idade
    public static void buscaIdade(File[] arquivos){
        Scanner scan = new Scanner(System.in);
        System.out.print("Digite a idade da pessoa: ");
        int idade = scan.nextInt();
        List<File> filtrados = Arrays.stream(arquivos) // retorna uma lista com os arquivos filtrados baseados na busca
                .filter(arquivo -> {
                    try (BufferedReader br = new BufferedReader(new FileReader(arquivo))){
                        br.readLine();
                        br.readLine();
                        br.readLine();
                        int idadeArquivo = Integer.parseInt(br.readLine().substring(8));
                        return idade == idadeArquivo; // verifica se o arquivo esta com o nome inserido na busca
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList()); // retorna em forma de lista
        if (filtrados.isEmpty()){
            System.out.println(" ");
            System.out.println("========================================");
            System.out.println("NENHUM REULTADO ENCONTRADO PARA: " + idade);
            System.out.println("========================================");
        } else {
            System.out.println(" ");
            System.out.println("========================================");
            System.out.println("RESULTADOS PARA: " + idade);
            System.out.println("========================================");
            filtrados.stream() // printa as informações dos arquivos filtrados no terminal
                    .forEach(arquivo -> {
                        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))){
                            String linha;
                            while ((linha = br.readLine()) != null){
                                System.out.println(linha);
                            }
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
        }
    }
    // busca por email
    public static void buscaEmail(File[] arquivos){
        Scanner scan = new Scanner(System.in);
        System.out.print("Digite o email da pessoa: ");
        String email = scan.nextLine();
        List<File> filtrados = Arrays.stream(arquivos) // retorna uma lista com os arquivos filtrados baseados na busca
                .filter(arquivo -> {
                    try (BufferedReader br = new BufferedReader(new FileReader(arquivo))){
                        br.readLine();
                        br.readLine();
                        String emailArquivo = br.readLine();
                        return (emailArquivo.toLowerCase().startsWith((" Email: " + email).toLowerCase())) || emailArquivo.equalsIgnoreCase(" Email: " + email); // verifica se o arquivo esta com o nome inserido na busca
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList()); // retorna em forma de lista
        if (filtrados.isEmpty()){
            System.out.println(" ");
            System.out.println("========================================");
            System.out.println("NENHUM REULTADO ENCONTRADO PARA: " + email);
            System.out.println("========================================");
        } else {
            System.out.println(" ");
            System.out.println("========================================");
            System.out.println("RESULTADOS PARA: " + email);
            System.out.println("========================================");
            filtrados.stream() // printa as informações dos arquivos filtrados no terminal
                    .forEach(arquivo -> {
                        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))){
                            String linha;
                            while ((linha = br.readLine()) != null){
                                System.out.println(linha);
                            }
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
        }
    }
    // metodo para verificar se o email ja esta cadastrado
    public static boolean emailCadastrado(String email, File cadastros){
        File[] arquivos = cadastros.listFiles();
        List<File> filtrados = Arrays.stream(arquivos) // retorna uma lista com os arquivos filtrados baseados no email
                .filter(arquivo -> {
                    try (BufferedReader br = new BufferedReader(new FileReader(arquivo))){
                        br.readLine();
                        br.readLine();
                        String emailArquivo = br.readLine();
                        return emailArquivo.equals(" Email: " + email); // verifica se o arquivo esta com o nome inserido na busca
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());
        if (!filtrados.isEmpty()){
            return true;
        }
        return false;
    }
}