package Interfaz;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Interfaz extends JFrame{
    JLabel servoUnoL;
 public Interfaz (){
     ImageIcon pinza = new ImageIcon("src\\Imagenes\\pinza.png");
        servoUnoL = new JLabel("Ejemplo");
        servoUnoL.setIcon(pinza);
        add(servoUnoL);
 }
}
