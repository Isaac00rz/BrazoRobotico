package Recursos;

import com.panamahitek.ArduinoException;
import com.panamahitek.PanamaHitek_Arduino;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

public class ConnectionPort {
    private PanamaHitek_Arduino ino;
    private boolean finishedConnection;
    private ManejadorArduino arduinoListener;
    
    public void connect() {
        ino = new PanamaHitek_Arduino();
        arduinoListener = new ManejadorArduino();
        try {
            // Conexion con puerto serial ubuntu
            //ino.arduinoRXTX("/dev/ttyUSB0", 9600, manejadorArduino); 
            // Conexion con puerto serial windows
            ino.arduinoRXTX("COM4", 9600,arduinoListener);
            finishedConnection = true;
        } catch (ArduinoException ex) {
            finishedConnection = false;
            JOptionPane.showMessageDialog(null, "No se ha podido conectar con el puerto COM");
        }
    }
    public boolean getConnectionStatus(){
        return finishedConnection;
    }
    public PanamaHitek_Arduino getConnection(){
        return ino;
    }
      private class ManejadorArduino implements SerialPortEventListener {

        @Override
        public synchronized void serialEvent(SerialPortEvent spe) {
            try {
                if (ino.isMessageAvailable()) {
                    System.out.println(ino.printMessage());
                }
            } catch (SerialPortException | ArduinoException ex) {
                Logger.getLogger(ConnectionPort.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}