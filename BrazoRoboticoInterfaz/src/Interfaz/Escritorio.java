package Interfaz;

import com.panamahitek.ArduinoException;
import com.panamahitek.PanamaHitek_Arduino;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Escritorio extends JFrame implements ActionListener{
    private JDesktopPane escritorio;
    private PanamaHitek_Arduino ino;
    private JInternalFrame frame;
    private JMenuBar menu;
    private JMenu archivo;
    private JMenuItem nuevo;
    private JSlider servoUno;
    private JSlider servoDos;
    private JSlider servoTres;
    private JSlider servoCuatro;
    private JLabel servoUnoL,servoDosL,servoTresL,servoCuatroL;
    private JButton guardar,addMove,importar;
    private Mouse mouseListener;
    private int count = 1;
    private DefaultTableModel moves;
    private JTable movesTable;
    private JScrollPane tablePane;
    
    public Escritorio (){
        escritorio = new JDesktopPane();
        mouseListener = new Mouse();
        menu = new JMenuBar();
        archivo = new JMenu("Archivo");
        nuevo = new JMenuItem("Nuevo");
        nuevo.addActionListener(this);
        archivo.add(nuevo);
        menu.add(archivo);
        this.setJMenuBar(menu);
        add(escritorio); 
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
    public void addSliders(){
        JPanel panelSliders = new JPanel(new GridLayout(4,1));
        servoUnoL = new JLabel("Pinza",new ImageIcon("src\\Imagenes\\pinza.png"),JLabel.HORIZONTAL);
        servoUnoL.setHorizontalTextPosition(JLabel.LEFT);
        servoUnoL.setFont(new Font("Constantia", Font.BOLD, 15));
        
        servoDosL= new JLabel("Cadera");
        
        servoTresL= new JLabel("Muñeca");
        
        servoCuatroL = new JLabel("Pinza");
        
        servoUno = new JSlider();
        servoUno.setMajorTickSpacing(10);
        servoUno.setMaximum(360);
        servoUno.setValue(0);
        servoUno.setPaintTicks(true);
        servoUno.setSnapToTicks(true);
        servoUno.addMouseListener(mouseListener);
        
        servoDos = new JSlider();
        servoDos.setMajorTickSpacing(10);
        servoDos.setMaximum(180);
        servoDos.setValue(0);
        servoDos.setPaintTicks(true);
        servoDos.setSnapToTicks(true);
        servoDos.addMouseListener(mouseListener);
        
        servoTres = new JSlider();
        servoTres.setMajorTickSpacing(10);
        servoTres.setMaximum(180);
        servoTres.setValue(0);
        servoTres.setPaintTicks(true);
        servoTres.setSnapToTicks(true);
        servoTres.addMouseListener(mouseListener);
        
        
        servoCuatro = new JSlider();
        servoCuatro.setMajorTickSpacing(10);
        servoCuatro.setMinorTickSpacing(10);
        servoCuatro.setMaximum(100);
        servoCuatro.setMinimum(60);
        servoCuatro.setValue(60);
        servoCuatro.setExtent(10);
        servoCuatro.setPaintTicks(true);
        servoCuatro.addMouseListener(mouseListener);
        
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
        JScrollPane sliderScroll = new JScrollPane(panelSliders
                ,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        addMove = new JButton("Añadir");
        
        JPanel addMovePanel = new JPanel();
        addMovePanel.add(addMove);
        
        JPanel movesElements = new JPanel(new BorderLayout());
        
        movesElements.add(sliderScroll,BorderLayout.CENTER);
        movesElements.add(addMovePanel,BorderLayout.SOUTH);
        
        frame.add(movesElements);
    }
    public void addTable(){
        JPanel tableContent = new JPanel(new BorderLayout());
        moves = new DefaultTableModel();
        
        moves.addColumn("No. Servo");
        moves.addColumn("Grados");
        movesTable = new JTable(moves);
        movesTable.setFont(new Font("Verdana", Font.PLAIN, 15));
        tablePane = new JScrollPane(movesTable,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
                ,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        tableContent.add(tablePane,BorderLayout.CENTER);
        guardar = new JButton("Guardar");
        importar = new JButton("Importar");
        JPanel tableButtonsPanel = new JPanel();
        tableButtonsPanel.add(guardar);
        tableButtonsPanel.add(importar);
        tableContent.add(tableButtonsPanel,BorderLayout.SOUTH);
        
        frame.add(tableContent);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == nuevo){
            frame = new JInternalFrame("Ventana "+count,true,true,true,true);
            frame.setLayout(new GridLayout(1,2));
            frame.setSize(600,500);
            frame.setVisible(true);
            addSliders();
            addTable();
            escritorio.add(frame);
            count+= 1;
        }
    }
    private class Mouse implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
          
        }

        @Override
        public void mousePressed(MouseEvent e) {
           
        }

        @Override
        public void mouseReleased(MouseEvent e) {
           if(e.getSource() == servoUno){
               System.out.println(convertValue(servoUno.getValue()));
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
}
