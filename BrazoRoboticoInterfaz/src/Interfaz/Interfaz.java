package Interfaz;

import com.panamahitek.ArduinoException;
import com.panamahitek.PanamaHitek_Arduino;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Interfaz extends JFrame implements MouseListener{
    private PanamaHitek_Arduino ino;
    private JSlider servoUno;
    private JSlider servoDos;
    private JSlider servoTres;
    private JSlider servoCuatro;
    private JLabel servoUnoL,servoDosL,servoTresL,servoCuatroL;
    private JButton guardar;
    private boolean clickExited = true;
    public Interfaz (){
        addSliders();
        addButtons();
    }
    public void addButtons(){
        guardar = new JButton("Guardar");
        JPanel buttons = new JPanel();
        buttons.add(guardar);
        add(buttons,BorderLayout.SOUTH);
    }
    public void addSliders(){
        JPanel panelSliders = new JPanel(new GridLayout(4,1));
        servoUnoL = new JLabel("Base");
        servoDosL= new JLabel("Cadera");
        servoTresL= new JLabel("Muñeca");
        servoCuatroL = new JLabel("Pinza");
        
        servoUno = new JSlider();
        servoUno.setMajorTickSpacing(10);
        servoUno.setMaximum(180);
        servoUno.setValue(0);
        servoUno.setPaintTicks(true);
        servoUno.setPaintLabels(true);
        servoUno.setSnapToTicks(true);
        servoUno.addMouseListener(this);
        
        servoDos = new JSlider();
        servoDos.setMajorTickSpacing(10);
        servoDos.setMaximum(180);
        servoDos.setValue(0);
        servoDos.setPaintTicks(true);
        servoDos.setPaintLabels(true);
        servoDos.setSnapToTicks(true);
        servoDos.addMouseListener(this);
        
        servoTres = new JSlider();
        servoTres.setMajorTickSpacing(10);
        servoTres.setMaximum(180);
        servoTres.setValue(0);
        servoTres.setPaintTicks(true);
        servoTres.setPaintLabels(true);
        servoTres.setSnapToTicks(true);
        servoTres.addMouseListener(this);
        
        
        servoCuatro = new JSlider();
        servoCuatro.setMajorTickSpacing(10);
        servoCuatro.setMinorTickSpacing(10);
        servoCuatro.setMaximum(180);
        servoCuatro.setValue(0);
        servoCuatro.setExtent(10);
        servoCuatro.setPaintTicks(true);
        servoCuatro.setPaintLabels(true);
        servoCuatro.addMouseListener(this);
        
        JPanel servoUnoP = new JPanel(new GridLayout(2,1));
        servoUnoP.add(servoUnoL);
        servoUnoP.add(servoUno);
        panelSliders.add(servoUnoP);
        
        JPanel servoDosP = new JPanel(new GridLayout(2,1));
        servoDosP.add(servoDosL);
        servoDosP.add(servoDos);
        panelSliders.add(servoDosP);
        
        JPanel servoTresP = new JPanel(new GridLayout(2,1));
        servoTresP.add(servoTresL);
        servoTresP.add(servoTres);
        panelSliders.add(servoTresP);
        
        JPanel servoCuatroP = new JPanel(new GridLayout(2,1));
        servoCuatroP.add(servoCuatroL);
        servoCuatroP.add(servoCuatro);
        panelSliders.add(servoCuatroP);
        
        add(panelSliders,BorderLayout.CENTER);
    }
    
    private void comArduino() {
        // Construyendo la comunicacion al puerto serial
        ino = new PanamaHitek_Arduino();
        try {
            // Conexion con puerto serial ubuntu
            //ino.arduinoRXTX("/dev/ttyUSB0", 9600, manejadorArduino); 
            // Conexion con puerto serial windows
            ino.arduinoTX("COM4", 9600);
        } catch (ArduinoException ex) {
            Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
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
        }else if(value>170 && value<=180){
            return 180;
        }else if(value>170 && value<=190){
            return 180;
        }else if(value>170 && value<=200){
            return 180;
        }else if(value>170 && value<=180){
            return 180;
        }else if(value>170 && value<=180){
            return 180;
        }else if(value>170 && value<=180){
            return 180;
        }else if(value>170 && value<=180){
            return 180;
        }else if(value>170 && value<=180){
            return 180;
        }else if(value>170 && value<=180){
            return 180;
        }else if(value>170 && value<=180){
            return 180;
        }else if(value>170 && value<=180){
            return 180;
        }else if(value>170 && value<=180){
            return 180;
        }else if(value>170 && value<=180){
            return 180;
        }else if(value>170 && value<=180){
            return 180;
        }else if(value>170 && value<=180){
            return 180;
        }else if(value>170 && value<=180){
            return 180;
        }else if(value>170 && value<=180){
            return 180;
        }else if(value>170 && value<=180){
            return 180;
        }else{return 0;}
    }
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getSource() == servoUno){
            
        }else if(e.getSource() == servoDos){
            
        }else if(e.getSource() == servoTres){
            
        }else if(e.getSource() == servoCuatro){
            
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
