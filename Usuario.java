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
    public Usuario(String nome, String email, String idade, String altura){
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
        } catch (NumCaracteresException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.exit(1);
        } catch (ValidaEmailException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.exit(1);
        } catch (MinIdadeException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.exit(1);
        } catch (ValidaAlturaException e){
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
            e.getMessage();
            e.printStackTrace();
        }
    }
    public void setIdade(int idade) {
        try {
            if (idade < IDADE_MINIMA){
                throw new MinIdadeException(IDADE_MINIMA);
            }
            this.idade = idade;
        } catch (MinIdadeException e){
            e.getMessage();
            e.printStackTrace();
        }
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setAltura(double altura) {
        this.altura = altura;
    }
    // toString
    @Override
    public String toString() {
        return "\n Nome: "
               + nome + "\n Email: "
               + email + "\n Idade: "
               + idade + "\n Altura: "
               + altura + "m";
    }
}
