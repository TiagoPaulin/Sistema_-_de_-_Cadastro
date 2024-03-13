package servicos;

import entidades.Cadastro;
import entidades.Log;
import entidades.Formulario;

import java.util.Scanner;

public class Menu {

    // atributos estáticos que serão utilizados pelas classes do package servicos
    public static Formulario formulario = new Formulario();
    public static Cadastro cadastros = new Cadastro();
    public static Log log = new Log();
    public static Scanner scan = new Scanner(System.in);
}
