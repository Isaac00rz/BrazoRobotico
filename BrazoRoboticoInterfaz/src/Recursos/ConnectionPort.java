package Recursos;

import com.panamahitek.ArduinoException;
import com.panamahitek.PanamaHitek_Arduino;

public class ConnectionPort {
    private PanamaHitek_Arduino ino;
    
    public boolean connect() {
        // Construyendo la comunicacion al puerto serial
        ino = new PanamaHitek_Arduino();
        try {
            // Conexion con puerto serial ubuntu
            //ino.arduinoRXTX("/dev/ttyUSB0", 9600, manejadorArduino); 
            // Conexion con puerto serial windows
            ino.arduinoTX("COM4", 9600);
            return true;
        } catch (ArduinoException ex) {
            return false;
        }
    }
}
