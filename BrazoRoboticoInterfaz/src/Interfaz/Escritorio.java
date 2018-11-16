package Interfaz;

import com.panamahitek.ArduinoException;
import com.panamahitek.PanamaHitek_Arduino;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Escritorio extends JFrame implements ActionListener{
    private JDesktopPane escritorio;
    private JInternalFrame frame;
    private JMenuBar menu;
    private JMenu archivo;
    private JMenuItem nuevo;
    private int count = 1;
    private BufferedImage img = null;
    
    public Escritorio (){
        try {
            img = ImageIO.read(new File("src\\Imagenes\\linuxIcon.png"));
        } catch (IOException ex) {
            Logger.getLogger(Escritorio.class.getName()).log(Level.SEVERE, null, ex);
        }
        escritorio = new JDesktopPane(){
            @Override
            protected void paintComponent(Graphics grphcs) {
                super.paintComponent(grphcs);
                grphcs.drawImage(img, 300, 100, null);
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(img.getWidth(), img.getHeight());
            }
        };
        escritorio.setBackground(new Color(56,56,56));

        menu = new JMenuBar();
        
        archivo = new JMenu("Archivo");
        archivo.setFont(new Font("Constantia", Font.BOLD, 15));
        
        nuevo = new JMenuItem("Nuevo");
        nuevo.setFont(new Font("Constantia", Font.BOLD, 15));
        nuevo.addActionListener(this);
        
        archivo.add(nuevo);
        menu.add(archivo);
        
        this.setJMenuBar(menu);
        this.add(escritorio); 
        
        //connect();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == nuevo){
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