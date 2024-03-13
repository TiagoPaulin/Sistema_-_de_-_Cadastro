package entidades;

import excecoes.*;

import java.io.File;
import java.util.Arrays;
import java.util.Optional;

public class Usuario {

    // definindo atributos da classe
    private String nome;
    private String email;
    private int idade;
    private double altura;
    private File registro;

    // definindo constantes
    public static final int MINIMO_CARACTERES = 10;
    public static final String REGEX_EMAIL = "@";
    public static final int IDADE_MINIMA = 18;
    public static final String REGEX_ALTURA = ",";


    // metodo construtor
    public Usuario (String nome, String email, String idade, String altura) {

        try {

            if (nome.length() < MINIMO_CARACTERES){
                throw new NumCaracteresException(MINIMO_CARACTERES);
            }
            if (!email.contains(REGEX_EMAIL)){
                throw new ValidaEmailException();
            }
            if (Integer.parseInt(idade) < IDADE_MINIMA){
                throw new MinIdadeException(IDADE_MINIMA);
            }
            if (!altura.contains(REGEX_ALTURA)){
                throw new ValidaAlturaException();
            }

            this.nome = nome;
            this.email = email;
            this.idade = Integer.parseInt(idade);
            this.altura = Double.parseDouble(altura.replace(",", "."));

        } catch (NumCaracteresException | ValidaEmailException | MinIdadeException | ValidaAlturaException e){

            System.out.println(e);;

        }
    }


    // metodo para gerar o nome do arquivo do usuário
    public String gerarNome (String nomeUsuario, int nCadastros){

        String nome = (nCadastros + 1) + "-" + nomeUsuario + ".txt";  // a string "crua"

        String[] split = nome.split(" ");                       // separo ela em partes

        Optional<String> nomeFormatado = Arrays.stream(split)         // inicio o fluxo de dados que vai retornar o nome do arquivo formatado dentro de um Optional
            .reduce((palavra1, palavra2) -> palavra1.concat(palavra2).toUpperCase());

        return nomeFormatado.get();
    }


    // metodos get
    public String getNome(){
        return nome;
    }
    public String getEmail(){
        return email;
    }
    public int getIdade() {
        return idade;
    }
    public double getAltura() {
        return altura;
    }
    public File getRegistro(){
        return registro;
    }


    // metodos set
    public void setNome(String nome) {
        try {
            if(nome.length() > MINIMO_CARACTERES){
                throw new NumCaracteresException(MINIMO_CARACTERES);
            }
            this.nome = nome;
        } catch (NumCaracteresException e) {
            System.out.println(e);
        }
    }
    public void setIdade(String idade) {
        try {
            if (Integer.parseInt(idade) < IDADE_MINIMA){
                throw new MinIdadeException(IDADE_MINIMA);
            }

            this.idade = Integer.parseInt(idade);

        } catch (MinIdadeException e){
            System.out.println(e);
        }
    }
    public void setEmail(String email) {
        try {
            if (!email.contains(REGEX_EMAIL)){
                throw new ValidaEmailException();
            }

            this.email = email;

        } catch (ValidaEmailException e){
            System.out.println(e);
        }
    }
    public void setAltura(String altura) {
        try {
            if (!altura.contains(REGEX_ALTURA)){
                throw new ValidaAlturaException();
            }
            this.altura = Double.parseDouble(altura.replace(",", "."));
        } catch (ValidaAlturaException e) {
            System.out.println(e);
        }
    }
    public void setRegistro(String nomeArquivo){
        this.registro = new File(nomeArquivo);
    }

    // toString
    @Override
    public String toString() {
        return "Nome: "
                + nome + "\nEmail: "
                + email + "\nIdade: "
                + idade + "\nAltura: "
                + altura + "m";
    }
}
