package Interfaz;

import com.panamahitek.ArduinoException;
import com.panamahitek.PanamaHitek_Arduino;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Escritorio extends JFrame implements ActionListener{
    private JDesktopPane escritorio;
    private PanamaHitek_Arduino ino;
    private JInternalFrame frame;
    private JMenuBar menu;
    private JMenu archivo;
    private JMenuItem nuevo;
    private int count = 1;
    
    public Escritorio (){
        escritorio = new JDesktopPane();
        
        menu = new JMenuBar();
        
        archivo = new JMenu("Archivo");
        
        nuevo = new JMenuItem("Nuevo");
        nuevo.addActionListener(this);
        
        archivo.add(nuevo);
        menu.add(archivo);
        
        this.setJMenuBar(menu);
        this.add(escritorio); 
        
        //connect();
    }
    private void connect() {
        // Construyendo la comunicacion al puerto serial
        ino = new PanamaHitek_Arduino();
        try {
            // Conexion con puerto serial ubuntu
            //ino.arduinoRXTX("/dev/ttyUSB0", 9600, manejadorArduino); 
            // Conexion con puerto serial windows
            ino.arduinoTX("COM4", 9600);
        } catch (ArduinoException ex) {
            Logger.getLogger(Escritorio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public int convertValue(int value){
        if(value>0 && value<=10){
            return 10;
        }else if(value>10 && value<=20){
            return 20;
        }else if(value>20 && value<=30){
            return 30;
        }else if(value>30 && value<=40){
            return 40;
        }else if(value>40 && value<=50){
            return 50;
        }else if(value>50 && value<=60){
            return 60;
        }else if(value>60 && value<=70){
            return 70;
        }else if(value>70 && value<=80){
            return 80;
        }else if(value>80 && value<=90){
            return 90;
        }else if(value>90 && value<=100){
            return 100;
        }else if(value>100 && value<=110){
            return 110;
        }else if(value>110 && value<=120){
            return 120;
        }else if(value>120 && value<=130){
            return 130;
        }else if(value>130 && value<=140){
            return 140;
        }else if(value>140 && value<=150){
            return 150;
        }else if(value>150 && value<=160){
            return 160;
        }else if(value>160 && value<=170){
            return 170;
        }else if(value>170 && value<=180){
            return 180;
        }else if(value>180 && value<=190){
            return 190;
        }else if(value>190 && value<=200){
            return 200;
        }else if(value>200 && value<=210){
            return 210;
        }else if(value>210 && value<=220){
            return 220;
        }else if(value>220 && value<=230){
            return 230;
        }else if(value>230 && value<=240){
            return 240;
        }else if(value>240 && value<=250){
            return 250;
        }else if(value>250 && value<=260){
            return 260;
        }else if(value>260 && value<=270){
            return 270;
        }else if(value>270 && value<=280){
            return 280;
        }else if(value>280 && value<=290){
            return 290;
        }else if(value>290 && value<=300){
            return 300;
        }else if(value>300 && value<=310){
            return 310;
        }else if(value>310 && value<=320){
            return 320;
        }else if(value>320 && value<=330){
            return 330;
        }else if(value>330 && value<=340){
            return 340;
        }else if(value>340 && value<=350){
            return 350;
        }else if(value>350 && value<=360){
            return 360;
        }else{return 0;}
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == nuevo){
            System.out.println("entro");
            Contenido contenido = new Contenido();
            frame = new JInternalFrame("Ventana "+count,true,true,true,true);
            frame.setSize(600,500);
            frame.setVisible(true);
            frame.add(contenido,BorderLayout.CENTER);
            escritorio.add(frame);
            count+= 1;
        }
    }
}