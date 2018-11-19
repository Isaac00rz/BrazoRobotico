#include<EEPROM.h>
#include <Servo.h>

int pinBoton = 2;
Servo servo1;
Servo servo2;
Servo servo3;
Servo servo4;
Servo servo5; 
char instruccion[61];

void setup() {
  Serial.begin(9600);
  pinMode(pinBoton,INPUT);
  servo1.attach(4);
  servo2.attach(5);
  servo3.attach(6);
  servo4.attach(7);
  servo5.attach(8);
  attachInterrupt(digitalPinToInterrupt(pinBoton), parar,RISING);
}

void loop() {
  int conLeer=0;
  int i = 0;
  int valAnterior = 1;
  char modo[1];
  EEPROM.get(1000,modo);
  if(modo[0]=='1'){
    EEPROM.get(0,instruccion);
    EEPROM.get(1001,valAnterior);
    for(i=valAnterior;i<61;i=i+2){
      if(instruccion[i] == '@'){
        EEPROM.put(1001,1);
        break;
      }else{
        switch(instruccion[i]){
        case '1':
        moverServo1(i);
        break;
        case '2':
        moverServo2(i);
        break;
        case '3':
        moverServo3(i);
        break;
        case '4':
        moverServo4(i);
        break;
        case '5':
        moverServo5(i);
        break;
        }
      }
    }
  }else if (modo[0]=='0'){
    if(Serial.available()>0){
    delay(100);
    while(Serial.available()>0){
      instruccion[conLeer]=Serial.read();
      conLeer++;
    }
    if(instruccion[0] == '0'){
      EEPROM.put(1000,'0');
      switch(instruccion[1]){
        case '1':
        moverServo1(1);
        break;
        case '2':
        moverServo2(1);
        break;
        case '3':
        moverServo3(1);
        break;
        case '4':
        moverServo4(1);
        break;
        case '5':
        moverServo5(1);
        break;
      }
      }else if(instruccion[0] == '1'){
        EEPROM.put(1000,'1');
        EEPROM.put(1001,1);
        EEPROM.put(0,instruccion);
      }
  }
 }
}

void parar(){
  int sig;
  delay(1000);
  EEPROM.put(1000,'0');
  EEPROM.get(1001,sig);
  instruccion[sig] = '@';
  
}

void moverServo1(int ultimo){
  int valor = calValor(instruccion[ultimo+1]);
  servo1.write(valor);
  EEPROM.put(1001,(ultimo+2));              
  delay(1000);
}
void moverServo2(int ultimo){
  int valor = calValor(instruccion[ultimo+1]);
  servo2.write(valor); 
  EEPROM.put(1001,(ultimo+2));   
  delay(1000);                     
}


void moverServo3(int ultimo){
  int valor = calValor(instruccion[ultimo+1]);
  servo3.write(valor);  
  EEPROM.put(1001,(ultimo+2));  
  delay(1000);
}

void moverServo4(int ultimo){
  int valor = calValor(instruccion[ultimo+1]);
  servo4.write(valor);  
  EEPROM.put(1001,(ultimo+2));  
  delay(100);
}
void moverServo5(int ultimo){
  int valor = calValor(instruccion[ultimo+1]);
  if(valor==60){
    valor=64;
  }else if(valor==110){
    valor = 105;
  }
  servo5.write(valor);  
  EEPROM.put(1001,(ultimo+2));  
  delay(1000);
}

int calValor(char letra){
  int va = 0;
  switch(letra){
    case 'a':
    va = 0;
    break;
    case 'b':
    va = 10;
    break;
    case 'c':
    va = 20;
    break;
    case 'd':
    va = 30;
    break;
    case 'e':
    va = 40;
    break;
    case 'f':
    va = 50;
    break;
    case 'g':
    va = 60;
    break;
    case 'h':
    va = 70;
    break;
    case 'i':
    va = 80;
    break;
    case 'j':
    va = 90;
    break;
    case 'k':
    va = 100;
    break;
    case 'l':
    va = 110;
    break;
    case 'm':
    va = 120;
    break;
    case 'n':
    va = 130;
    break;
    case 'o':
    va = 140;
    break;
    case 'p':
    va = 150;
    break;
    case 'q':
    va = 160;
    break;
    case 'r':
    va = 170;
    break;
    case 's':
    va = 180;
    break;
    case 't':
    va = 190;
    break;
    case 'u':
    va = 200;
    break;
    case 'v':
    va = 210;
    break;
    case 'x':
    va = 220;
    break;
    case 'y':
    va = 230;
    break;
    case 'z':
    va = 240;
    break;
    case 'A':
    va = 250;
    break;
    case 'B':
    va = 260;
    break;
    case 'C':
    va = 270;
    break;
    case 'D':
    va = 280;
    break;
    case 'E':
    va = 290;
    break;
    case 'F':
    va = 300;
    break;
    case 'G':
    va = 310;
    break;
    case 'H':
    va = 320;
    break;
    case 'I':
    va = 330;
    break;
    case 'J':
    va = 340;
    break;
    case 'K':
    va = 350;
    break;
    case 'L':
    va = 360;
    break;
  }
  return va;
}
