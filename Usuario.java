public class Usuario {
    // definindo atributos da classe
    private String nome;
    private String email;
    private int idade;
    private double altura;
    // metodo construtor
    public Usuario(String nome, String email, int idade, double altura){
        setNome(nome);
        this.email = email;
        setIdade(idade);
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
        try {
            this.nome = nome;
            if(nome.length() > MINIMO_CARACTERES){
                throw new NumCaracteresException(MINIMO_CARACTERES);
            }
        } catch (NumCaracteresException e) {
            e.getMessage();
        }
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setIdade(int idade) {
        try {
            this.idade = idade;
            if (idade < IDADE_MINIMA){
                throw new MinIdadeException(IDADE_MINIMA);
            }
        } catch (MinIdadeException e){
            e.getMessage();
        }
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
