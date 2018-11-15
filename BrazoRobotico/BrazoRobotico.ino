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
  }
}
