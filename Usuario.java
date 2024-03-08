public class Usuario {
    // definindo atributos
    private String nome;
    private String email;
    private int idade;
    private double altura;
    // metodo construtor
    public Usuario(String nome, String email, int idade, double altura){
        this.nome = nome;
        this.email = email;
        this.idade = idade;
        this.altura = altura;
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
        this.nome = nome;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setIdade(int idade) {
        this.idade = idade;
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
