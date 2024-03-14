package servicos;

import entidades.Cadastro;
import entidades.Log;
import entidades.Formulario;

import java.util.Scanner;

public class Menu {

    // atributos estáticos que serão utilizados pelas classes do package servicos
    public static Formulario formulario;
    public static Cadastro cadastros;
    public static Log log;
    public static Scanner scan = new Scanner(System.in);


    public static void executar(){
        formulario = new Formulario();
        cadastros = new Cadastro();
        log = new Log();
    }
}
