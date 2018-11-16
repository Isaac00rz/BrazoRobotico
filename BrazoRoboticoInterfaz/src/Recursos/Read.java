package Recursos;

import java.io.BufferedReader;
import java.io.FileReader;

public class Read {
    private String text="";
    private String route;

    public String read(String route){
      int caracter;
      this.route=route;
      text="";
      try{
        FileReader file = new FileReader(route);
        BufferedReader buffer = new BufferedReader(file);
        while((caracter=buffer.read())!=-1){
            text+=((char)caracter);
        }
        buffer.close();
      }catch(Exception e){System.out.println(e);}
      return text;
    }
}
