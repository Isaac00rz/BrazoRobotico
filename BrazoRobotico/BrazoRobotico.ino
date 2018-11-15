#include<EEPROM.h>
#include <Servo.h>

Servo servo1;
Servo servo2;
Servo servo3;
Servo servo4; 
char instruccion[16];

void setup() {
  Serial.begin(9600);
  servo1.attach(3);
  servo2.attach(4);
  servo3.attach(5);
  servo4.attach(6);
}

void loop() {
  int i=0;
  if(Serial.available()>0){
    delay(100);
    while(Serial.available()>0){
      instruccion[i]=Serial.read();
      i++;
    }
    if(instruccion[0] == '0'){
      switch(instruccion[1]){
        case '1':
        moverServo1();
        break;
        case '2':
        moverServo2();
        break;
        case '3':
        moverServo3();
        break;
        case '4':
        moverServo4();
        break;
      }
      }else if(instruccion[0] == '1'){
        
        }
  }

}

void moverServo1(){
  int valor = calValor(instruccion[2]);
  int pos;
  for (pos = 0; pos <= valor; pos += 1) 
   {
      servo1.write(pos);              
      delay(15);                       
   }
}
void moverServo2(){
  int valor = calValor(instruccion[2]);
  int pos;
  for (pos = 0; pos <= valor; pos += 1) 
   {
      servo2.write(pos);              
      delay(15);                       
   }
}

void moverServo3(){
  int valor = calValor(instruccion[2]);
  int pos;
  for (pos = 0; pos <= valor; pos += 1) 
   {
      servo3.write(pos);              
      delay(15);                       
   }
}

void moverServo4(){
  int valor = calValor(instruccion[2]);
  int pos;
  for (pos = 0; pos <= valor; pos += 1) 
   {
      servo4.write(pos);              
      delay(15);                       
   }
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
