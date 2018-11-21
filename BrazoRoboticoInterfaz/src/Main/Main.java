package Main;

import Interfaz.*;
/*
    Fecha: 20 de noviembre 2018
    Integrantes:
    Isaac Perez Segura
    Cinthia Aidee Alvarez Castro
    Isaac Ulises Hernandez Marquez
*/
public class Main {

    public static void main(String[] args) {
        Escritorio escritorio = new Escritorio();
        escritorio.setSize(1200,700);
        escritorio.setVisible(true);
        escritorio.setDefaultCloseOperation(escritorio.EXIT_ON_CLOSE);
        escritorio.setLocationRelativeTo(null);
        escritorio.setTitle("Brazo robotico");
    }
    
}
