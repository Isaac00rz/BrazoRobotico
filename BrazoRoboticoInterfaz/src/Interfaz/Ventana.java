package Interfaz;

import Recursos.*;
import com.panamahitek.ArduinoException;
import com.panamahitek.PanamaHitek_Arduino;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import jssc.SerialPortException;
/*
    Clase contenedora de JSlider's que son de utilidad para asignar
    un valor a cada servo ya sea en tiempo real ó cuando se desea
    guardar una serie de movimientos (ese es el proposito de JTable).
    Estas acciones son implementadas por medio de "ActionListener"
    y "MouseListener".
*/
public class Ventana extends JPanel implements ActionListener{
    private JSlider servoUno;
    private JSlider servoDos;
    private JSlider servoTres;
    private JSlider servoCuatro;
    private JSlider servoCinco;
    
    private JLabel servoUnoL,servoDosL,servoTresL,servoCuatroL,servoCincoL;
    private JButton enviarArduino,guardarTexto,borrar,importar
            ,servoUnoB,servoDosB,servoTresB,servoCuatroB,servoCincoB;
    private DefaultTableModel moves;
    private JTable movesTable;
    private JScrollPane tablePane;
    private Mouse mouseListener;
    private PanamaHitek_Arduino ino;
    private JFileChooser chooser;
    private Read reader;
    private Write writer;
    private MovesValues moveValues;
    
    /*
        Nuestro constructor requiere un objeto que contenga la conexion al puerto
        COM
    */
    public Ventana (PanamaHitek_Arduino com){
        this.ino=com;
        mouseListener = new Mouse();
        moveValues = new MovesValues();
        reader = new Read();
        writer = new Write();
        chooser = new JFileChooser();
        this.setLayout(new GridLayout(1,2));
        addSliders();
        addTable();
    }
    
    public void addSliders(){
        JPanel panelSliders = new JPanel(new GridLayout(5,1));
        servoUnoL = new JLabel("Base");
        servoUnoL.setHorizontalTextPosition(JLabel.LEFT);
        servoUnoL.setFont(new Font("Constantia", Font.BOLD, 15));
        
        servoDosL= new JLabel("Cadera");
        servoDosL.setHorizontalTextPosition(JLabel.LEFT);
        servoDosL.setFont(new Font("Constantia", Font.BOLD, 15));
        
        servoTresL= new JLabel("Codo");
        servoTresL.setHorizontalTextPosition(JLabel.LEFT);
        servoTresL.setFont(new Font("Constantia", Font.BOLD, 15));
        
        servoCuatroL = new JLabel("Muñeca");
        servoCuatroL.setHorizontalTextPosition(JLabel.LEFT);
        servoCuatroL.setFont(new Font("Constantia", Font.BOLD, 15));
        
        servoCincoL = new JLabel("Pinza",new ImageIcon("src\\Imagenes\\pinza.png"),JLabel.HORIZONTAL);
        servoCincoL.setHorizontalTextPosition(JLabel.LEFT);
        servoCincoL.setFont(new Font("Constantia", Font.BOLD, 15));
        
        servoUnoB = new JButton("Añadir");
        servoUnoB.addActionListener(this);
        servoDosB = new JButton("Añadir");
        servoDosB.addActionListener(this);
        servoTresB = new JButton("Añadir");
        servoTresB.addActionListener(this);
        servoCuatroB = new JButton("Añadir");
        servoCuatroB.addActionListener(this);
        servoCincoB = new JButton("Añadir");
        servoCincoB.addActionListener(this);
        
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
        servoCuatro.setMaximum(180);
        servoCuatro.setValue(0);
        servoCuatro.setPaintTicks(true);
        servoCuatro.setSnapToTicks(true);
        servoCuatro.addMouseListener(mouseListener);
        
        servoCinco = new JSlider();
        servoCinco.setMajorTickSpacing(10);
        servoCinco.setSnapToTicks(true);
        servoCinco.setMaximum(100);
        servoCinco.setMinimum(60);
        servoCinco.setValue(60);
        servoCinco.setPaintTicks(true);
        servoCinco.addMouseListener(mouseListener);
        
        JPanel servoUnoP = new JPanel(new GridLayout(3,1));
        servoUnoP.add(servoUnoL);
        servoUnoP.add(servoUno);
        JPanel servoUnoBPanel = new JPanel();
        servoUnoBPanel.add(servoUnoB);
        servoUnoP.add(servoUnoBPanel);
        panelSliders.add(servoUnoP);
        
        JPanel servoDosP = new JPanel(new GridLayout(3,1));
        servoDosP.add(servoDosL);
        servoDosP.add(servoDos);
        JPanel servoDosBPanel = new JPanel();
        servoDosBPanel.add(servoDosB);
        servoDosP.add(servoDosBPanel);
        panelSliders.add(servoDosP);
        
        JPanel servoTresP = new JPanel(new GridLayout(3,1));
        servoTresP.add(servoTresL);
        servoTresP.add(servoTres);
        JPanel servoTresBPanel = new JPanel();
        servoTresBPanel.add(servoTresB);
        servoTresP.add(servoTresBPanel);
        panelSliders.add(servoTresP);
        
        JPanel servoCuatroP = new JPanel(new GridLayout(3,1));
        servoCuatroP.add(servoCuatroL);
        servoCuatroP.add(servoCuatro);
        JPanel servoCuatroBPanel = new JPanel();
        servoCuatroBPanel.add(servoCuatroB);
        servoCuatroP.add(servoCuatroBPanel);
        panelSliders.add(servoCuatroP);
        
        JPanel servoCincoP = new JPanel(new GridLayout(3,1));
        servoCincoP.add(servoCincoL);
        servoCincoP.add(servoCinco);
        JPanel servoCincoBPanel = new JPanel();
        servoCincoBPanel.add(servoCincoB);
        servoCincoP.add(servoCincoBPanel);
        panelSliders.add(servoCincoP);
        JScrollPane sliderScroll = new JScrollPane(panelSliders
                ,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        add(sliderScroll);
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
        
        enviarArduino = new JButton("Enviar a arduino");
        enviarArduino.addActionListener(this);
        
        guardarTexto = new JButton("Respaldar");
        guardarTexto.addActionListener(this);
        
        borrar = new JButton("Borrar");
        borrar.addActionListener(this);
        
        importar = new JButton("Importar");
        importar.addActionListener(this);
        
        JPanel tableButtonsPanel = new JPanel();
        tableButtonsPanel.add(enviarArduino);
        tableButtonsPanel.add(guardarTexto);
        tableButtonsPanel.add(importar);
        tableButtonsPanel.add(borrar);
        tableContent.add(tableButtonsPanel,BorderLayout.SOUTH);
        
        add(tableContent);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == borrar){
            // Si se desea borrar algun movimiento de la tabla
            if(movesTable.getSelectedRow() != -1){
                moves.removeRow(movesTable.getSelectedRow());
            }else{JOptionPane.showMessageDialog(null,"No se selecciono ninguna fila");}
        }else if(e.getSource() == importar){
            // Si se desea importar un archivo de texto que contiene movimientos
            String text;
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "Archivos de texto txt", "txt");
            chooser.setFileFilter(filter);
            int returnVal = chooser.showOpenDialog(null);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
               moves.setRowCount(0);
               text = reader.read(chooser.getSelectedFile().getAbsolutePath());
                Object [] data;
                for (int j = 0,i=0; j < text.length(); j+=2) {
                    data = new Object[2];
                    for (int k = 0; k < data.length; k++) {
                        if(k == 1){
                            data[k] = moveValues.convertToInt(text.charAt(j+k));
                        }else{
                            data[k] = text.charAt(j+k);
                        }
                    }
                    moves.addRow(data);
                }

            }
        }else if(e.getSource() == guardarTexto){
           // Si se desea salvar los movimientos en un archivo de texto
           FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "Archivos de texto .txt", "txt");
            chooser.setFileFilter(filter);
           int returnVal = chooser.showSaveDialog(null);
           if(returnVal == JFileChooser.APPROVE_OPTION){
               String [] extension = filter.getExtensions();
               String nameFile = chooser.getSelectedFile().getName()+"."+extension[0];
               String data = "";
               for (int i = 0; i < moves.getRowCount(); i++) {
                   System.out.println(Integer.parseInt(moves.getValueAt(i, 1).toString()));
                   data = data + moves.getValueAt(i, 0).toString() 
                           + moveValues.convertToChar(Integer.parseInt(moves.getValueAt(i, 1).toString()));
               }
               writer.write(data, chooser.getCurrentDirectory().getPath()+"\\"+nameFile);
           }
        }else if(e.getSource() == enviarArduino){
            /* 
                Si se desea guardar las instrucciones en arduino, para que este
                las ejecute aun despues de haber sido apagado.
            */
            try {
                if(moves.getRowCount() > 0){
                    String data = "1";
                    for (int i = 0; i < moves.getRowCount(); i++) {
                        data = data + moves.getValueAt(i, 0).toString() 
                               + moveValues.convertToChar(Integer.parseInt(moves.getValueAt(i, 1).toString()));
                    }
                    data = data + "@";
                    System.out.println(data);
                    ino.sendData(data);
                }else{JOptionPane.showMessageDialog(null, "No hay datos en la tabla");}
            } catch (ArduinoException | SerialPortException ex) {
                JOptionPane.showMessageDialog(null, "Verifique la conexion en el puerto COM");
            }
        }else if((e.getSource() == servoUnoB | e.getSource() == servoDosB 
                    | e.getSource() == servoTresB | e.getSource() == servoCuatroB
                    || e.getSource() == servoCincoB) && moves.getRowCount()<20){
            //Si se desea agregar algun valor a la tabla de movimientos.
            if(e.getSource() == servoUnoB){
                String [] data = {"1",Integer.toString(servoUno.getValue())};
                moves.addRow(data);
            }else if(e.getSource() == servoDosB){
                String [] data = {"2",Integer.toString(servoDos.getValue())};
                moves.addRow(data);
            }else if(e.getSource() == servoTresB){
                String [] data = {"3",Integer.toString(servoTres.getValue())};
                moves.addRow(data);
            }else if(e.getSource() == servoCuatroB){
                String [] data = {"4",Integer.toString(servoCuatro.getValue())};
                moves.addRow(data);
            }else if(e.getSource() == servoCincoB){
                String [] data = {"5",Integer.toString(servoCinco.getValue())};
                moves.addRow(data);
            }
        }else{
            JOptionPane.showMessageDialog(null, "No se pueden realizar mas de 20 movimientos");
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
            /*
                Si se realizo un cambiar al Slider este envia su valor
                al instante al arduino (sin ser guardado en la memoria
                EEPROM.
            */
           if(e.getSource() == servoUno){
               try {
                   //System.out.println("01"+moveValues.convertToChar(servoUno.getValue()));
                   ino.sendData("01"+moveValues.convertToChar(servoUno.getValue()));
               } catch (ArduinoException | SerialPortException ex) {
                   Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
               }
            }else if(e.getSource() == servoDos){
                try {
                   System.out.println("02"+moveValues.convertToChar(servoDos.getValue()));
                   ino.sendData("02"+moveValues.convertToChar(servoDos.getValue()));
               } catch (ArduinoException | SerialPortException ex) {
                   Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
               }
            }else if(e.getSource() == servoTres){
               try {
                   //System.out.println("03"+moveValues.convertToChar(servoTres.getValue()));
                   ino.sendData("03"+moveValues.convertToChar(servoTres.getValue()));
               } catch (ArduinoException | SerialPortException ex) {
                   Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
               }
            }else if(e.getSource() == servoCuatro){
              try {
                   //System.out.println("04"+moveValues.convertToChar(servoCuatro.getValue()));
                   ino.sendData("04"+moveValues.convertToChar(servoCuatro.getValue()));
               } catch (ArduinoException | SerialPortException ex) {
                   Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
               }
            }else if(e.getSource() == servoCinco){
              try {
                   //System.out.println("05"+moveValues.convertToChar(servoCinco.getValue()));
                   ino.sendData("05"+moveValues.convertToChar(servoCinco.getValue()));
               } catch (ArduinoException | SerialPortException ex) {
                   Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
               }
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
