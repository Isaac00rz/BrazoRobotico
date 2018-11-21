package Recursos;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
// Escritura en archivo de texto, para salvar los movimientos del brazo robotico.
public class Write {
    private String text="";
    private String route;

    public void write(String content, String path){
        FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter(path);
            pw = new PrintWriter(fichero);
            pw.print(content);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
           try {
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
    }
}
