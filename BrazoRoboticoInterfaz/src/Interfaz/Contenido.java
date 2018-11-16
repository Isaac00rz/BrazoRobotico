package Interfaz;

import Recursos.*;
import com.panamahitek.PanamaHitek_Arduino;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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

public class Contenido extends JPanel implements ActionListener{
    private JSlider servoUno;
    private JSlider servoDos;
    private JSlider servoTres;
    private JSlider servoCuatro;
    private JLabel servoUnoL,servoDosL,servoTresL,servoCuatroL;
    private JButton guardar,borrar,importar,servoUnoB,servoDosB,servoTresB,servoCuatroB;
    private DefaultTableModel moves;
    private JTable movesTable;
    private JScrollPane tablePane;
    private Mouse mouseListener;
    private ConnectionPort connection;
    private PanamaHitek_Arduino ino;
    private JFileChooser chooser;
    private Read reader;
    private MovesValues moveValues;
    public Contenido (){
        moveValues = new MovesValues();
        reader = new Read();
        chooser = new JFileChooser();
        connection = new ConnectionPort();
        //if(connection.connect()){
            this.setLayout(new GridLayout(1,2));
            addSliders();
            addTable();
        /*}else{
            JOptionPane.showMessageDialog(null, "No se ha podido comunicar con el puerto COM");
        }*/
    }
    
    public void addSliders(){
        JPanel panelSliders = new JPanel(new GridLayout(4,1));
        servoUnoL = new JLabel("Pinza",new ImageIcon("src\\Imagenes\\pinza.png"),JLabel.HORIZONTAL);
        servoUnoL.setHorizontalTextPosition(JLabel.LEFT);
        servoUnoL.setFont(new Font("Constantia", Font.BOLD, 15));
        
        servoDosL= new JLabel("Cadera");
        servoDosL.setHorizontalTextPosition(JLabel.LEFT);
        servoDosL.setFont(new Font("Constantia", Font.BOLD, 15));
        
        servoTresL= new JLabel("Muñeca");
        servoTresL.setHorizontalTextPosition(JLabel.LEFT);
        servoTresL.setFont(new Font("Constantia", Font.BOLD, 15));
        
        servoCuatroL = new JLabel("Pinza");
        servoCuatroL.setHorizontalTextPosition(JLabel.LEFT);
        servoCuatroL.setFont(new Font("Constantia", Font.BOLD, 15));
        
        servoUnoB = new JButton("Añadir");
        servoUnoB.addActionListener(this);
        servoDosB = new JButton("Añadir");
        servoDosB.addActionListener(this);
        servoTresB = new JButton("Añadir");
        servoTresB.addActionListener(this);
        servoCuatroB = new JButton("Añadir");
        servoCuatroB.addActionListener(this);
        
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
        servoCuatro.setSnapToTicks(true);
        servoCuatro.setMaximum(100);
        servoCuatro.setMinimum(60);
        servoCuatro.setValue(60);
        servoCuatro.setPaintTicks(true);
        servoCuatro.addMouseListener(mouseListener);
        
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
        
        guardar = new JButton("Guardar");
        
        borrar = new JButton("Borrar");
        borrar.addActionListener(this);
        
        importar = new JButton("Importar");
        importar.addActionListener(this);
        
        JPanel tableButtonsPanel = new JPanel();
        tableButtonsPanel.add(guardar);
        tableButtonsPanel.add(importar);
        tableButtonsPanel.add(borrar);
        tableContent.add(tableButtonsPanel,BorderLayout.SOUTH);
        
        add(tableContent);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
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
            }else if(e.getSource() == borrar){
                if(movesTable.getSelectedRow() != -1){
                    moves.removeRow(movesTable.getSelectedRow());
                }else{JOptionPane.showMessageDialog(null,"No se selecciono ninguna fila");}
            }else if(e.getSource() == importar){
                String text;
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Archivos de texto txt", "txt");
                chooser.setFileFilter(filter);
                int returnVal = chooser.showOpenDialog(null);
                if(returnVal == JFileChooser.APPROVE_OPTION) {
                   moves.setRowCount(0);
                   text = reader.read(chooser.getSelectedFile().getAbsolutePath());
                    Object [] data;
                    for (int j = 1,i=0; j < text.length(); j+=2) {
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
