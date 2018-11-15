package Main;
import Interfaz.Interfaz;

public class Main {

    public static void main(String[] args) {
        Interfaz frame = new Interfaz();
        frame.setSize(700,600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Brazo robotico");
    }
    
}
