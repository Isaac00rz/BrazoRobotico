package Main;
import Interfaz.*;

public class Main {

    public static void main(String[] args) {
        Escritorio escritorio = new Escritorio();
        escritorio.setSize(800,700);
        escritorio.setVisible(true);
        escritorio.setDefaultCloseOperation(escritorio.EXIT_ON_CLOSE);
        escritorio.setLocationRelativeTo(null);
        escritorio.setTitle("Brazo robotico");
    }
    
}
