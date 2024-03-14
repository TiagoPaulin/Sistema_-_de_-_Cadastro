package servicos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static servicos.Menu.cadastros;
import static servicos.Menu.scan;
public class BuscarUsuario {


    // metodo que lista o nome de todos os usuário cadastrados
    public static void listarCadastros () {

        File[] arquivos = cadastros.getCadastros().listFiles(); // lista todos os registros

        System.out.println("---------------------------------------------");
        Arrays.stream(arquivos)
            .map(arquivo -> {                                   // pega o arquivo e retorna a string do nome

                String nome;                                    // atributo que ira receber o nome do usuario

                try (BufferedReader br = new BufferedReader(new FileReader(arquivo))){

                    nome = br.readLine();                       // le a linha com o nome

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                return nome;

            })
            .forEach(System.out::println);                      // printa todos os nomes registrados
        System.out.println("---------------------------------------------");
    }


    // metodo para realizar buscas nos registros
    public static void buscarCadastro () {

        File[] arquivos = cadastros.getCadastros().listFiles();

        System.out.println("Como voce deseja realizar a busca?");
        System.out.println("1. Nome");
        System.out.println("2. Idade");
        System.out.println("3. Email");
        System.out.print("Selecione uma opção: ");
        int opcaoBusca = scan.nextInt();
        scan.nextLine();

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
    private static void buscaNome(File[] arquivos){

        System.out.print("Digite o nome da pessoa: ");
        String nome = scan.nextLine();

        List<File> filtrados = Arrays.stream(arquivos) // retorna uma lista com os arquivos filtrados baseados na busca
                .filter(arquivo -> {
                    try (BufferedReader br = new BufferedReader(new FileReader(arquivo))){

                        String nomeArquivo = br.readLine();
                        return (nomeArquivo.toLowerCase().startsWith(("Nome: " + nome).toLowerCase())) || nomeArquivo.equalsIgnoreCase("Nome: " + nome) || nomeArquivo.contains(nome); // verifica se o arquivo esta com o nome inserido na busca

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList()); // retorna em forma de lista

        exibirResultados(filtrados, nome);  // exibe os reusultados no terminal

    }


    // busca por idade
    private static void buscaIdade(File[] arquivos){

        System.out.print("Digite a idade da pessoa: ");
        int idade = scan.nextInt();

        List<File> filtrados = Arrays.stream(arquivos) // retorna uma lista com os arquivos filtrados baseados na busca
                .filter(arquivo -> {
                    try (BufferedReader br = new BufferedReader(new FileReader(arquivo))){

                        br.readLine();
                        br.readLine();
                        int idadeArquivo = Integer.parseInt(br.readLine().substring(7));
                        return idade == idadeArquivo; // verifica se o arquivo esta com o nome inserido na busca

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList()); // retorna em forma de lista

        exibirResultados(filtrados, String.valueOf(idade));  // exibe os reusultados no terminal
    }


    // busca por email
    private static void buscaEmail(File[] arquivos){

        System.out.print("Digite o email da pessoa: ");
        String email = scan.nextLine();

        List<File> filtrados = Arrays.stream(arquivos) // retorna uma lista com os arquivos filtrados baseados na busca
                .filter(arquivo -> {
                    try (BufferedReader br = new BufferedReader(new FileReader(arquivo))){

                        br.readLine();
                        String emailArquivo = br.readLine();
                        return (emailArquivo.toLowerCase().startsWith(("Email: " + email).toLowerCase())) || emailArquivo.equalsIgnoreCase("Email: " + email) || emailArquivo.contains(email); // verifica se o arquivo esta com o nome inserido na busca

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList()); // retorna em forma de lista

        exibirResultados(filtrados, email);  // exibe os reusultados no terminal
    }


    // metodo que exibe os resultados da busca no terminal
    private static void exibirResultados (List<File> resutados, String busca) {

        if (resutados.isEmpty()){       // caso nao tenha nenhum resultado

            System.out.println(" ");
            System.out.println("========================================");
            System.out.println("NENHUM REULTADO ENCONTRADO PARA: " + busca);
            System.out.println("========================================");

        } else {                        // caso tenha resultados

            System.out.println(" ");
            System.out.println("========================================");
            System.out.println("RESULTADOS PARA: " + busca);
            System.out.println("========================================");

            resutados.stream()          // printa as informações dos arquivos filtrados no terminal
                    .forEach(arquivo -> {

                        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))){

                            String linha;
                            while ((linha = br.readLine()) != null){
                                System.out.println(linha);
                            }
                            System.out.println("-----------------------------------");

                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
        }
    }

}
