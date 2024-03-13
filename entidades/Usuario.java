package entidades;

import excecoes.*;

public class Usuario {

    // definindo atributos da classe
    private String nome;
    private String email;
    private int idade;
    private double altura;

    // definindo constantes
    private final int MINIMO_CARACTERES = 10;
    private final String REGEX_EMAIL = "@";
    private final int IDADE_MINIMA = 18;
    private final String REGEX_ALTURA = ",";


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

            System.out.println(e.getMessage());
            e.printStackTrace();
            System.exit(1);

        }
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


    // metodos set
    public void setNome(String nome) {
        try {
            if(nome.length() > MINIMO_CARACTERES){
                throw new NumCaracteresException(MINIMO_CARACTERES);
            }
            this.nome = nome;
        } catch (NumCaracteresException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    public void setIdade(String idade) {
        try {
            if (Integer.parseInt(idade) < IDADE_MINIMA){
                throw new MinIdadeException(IDADE_MINIMA);
            }

            this.idade = Integer.parseInt(idade);

        } catch (MinIdadeException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    public void setEmail(String email) {
        try {
            if (!email.contains(REGEX_EMAIL)){
                throw new ValidaEmailException();
            }

            this.email = email;

        } catch (ValidaEmailException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    public void setAltura(String altura) {
        try {
            if (!altura.contains(REGEX_ALTURA)){
                throw new ValidaAlturaException();
            }
            this.altura = Double.parseDouble(altura.replace(",", "."));
        } catch (ValidaAlturaException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
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
