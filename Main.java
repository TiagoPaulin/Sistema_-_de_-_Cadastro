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
        // cadastrando usuario
        cadastrarUsuario(formulario, cadastros,nCadastros);
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
        BufferedWriter bw = new BufferedWriter(new FileWriter(cadastros));
        bw.write(String.valueOf(nCadastros + 1) + "-" + dados.get(0).toUpperCase() + ".TXT");
        bw.flush();
        bw.close();
    }
}