#include<EEPROM.h>
#include <Servo.h>

int pinBoton = 3;
Servo servo1;
Servo servo2;
Servo servo3;
Servo servo4;
Servo servo5; 
char instruccion[61];

void setup() {
  Serial.begin(9600);
  attachInterrupt(digitalPinToInterrupt(pinBoton),parar,LOW);
  servo1.attach(4);
  servo2.attach(5);
  servo3.attach(6);
  servo4.attach(7);
  servo5.attach(8);
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
    for(i=valAnterior;i<61;i++){
      if(instruccion[i] == '@'){
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
  }else{
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
        moverServo1(0);
        break;
        case '2':
        moverServo2(0);
        break;
        case '3':
        moverServo3(0);
        break;
        case '4':
        moverServo4(0);
        break;
        case '5':
        moverServo5(0);
        break;
      }
      }else if(instruccion[0] == '1'){
        EEPROM.put(1000,'1');
        EEPROM.put(0,instruccion);
      }
  }
 }
}

void parar(void){
  EEPROM.put(1000,'0');
  loop();
}

void moverServo1(int ultimo){
  int valor = calValor(instruccion[2]);
  servo1.write(valor);
  EEPROM.put(1001,(ultimo+1));              
}
void moverServo2(int ultimo){
  int valor = calValor(instruccion[2]);
  servo2.write(valor); 
  EEPROM.put(1001,(ultimo+1));                        
}


void moverServo3(int ultimo){
  int valor = calValor(instruccion[2]);
  servo3.write(valor);  
  EEPROM.put(1001,(ultimo+1));  
}

void moverServo4(int ultimo){
  int valor = calValor(instruccion[2]);
  servo4.write(valor);  
  EEPROM.put(1001,(ultimo+1));  
}
void moverServo5(int ultimo){
  int valor = calValor(instruccion[2]);
  if(valor==60){
    valor=64;
  }else if(valor==110){
    valor = 105;
  }
  servo5.write(valor);  
  EEPROM.put(1001,(ultimo+1));  
}

int calValor(char letra){
  int va = 0;
  switch(letra){
    case 'A':
    va = 0;
    break;
    case 'B':
    va = 10;
    break;
    case 'C':
    va = 20;
    break;
    case 'D':
    va = 30;
    break;
    case 'E':
    va = 40;
    break;
    case 'F':
    va = 50;
    break;
    case 'G':
    va = 60;
    break;
    case 'H':
    va = 70;
    break;
    case 'I':
    va = 80;
    break;
    case 'J':
    va = 90;
    break;
    case 'K':
    va = 100;
    break;
    case 'L':
    va = 110;
    break;
    case 'M':
    va = 120;
    break;
    case 'N':
    va = 130;
    break;
    case 'O':
    va = 140;
    break;
    case 'P':
    va = 150;
    break;
    case 'Q':
    va = 160;
    break;
    case 'R':
    va = 170;
    break;
    case 'S':
    va = 180;
    break;
  }
  return va;
}
