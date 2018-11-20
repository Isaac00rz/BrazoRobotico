#include<EEPROM.h>
#include <Servo.h>

const int pinBoton = 2; //Botón de paro.
const int buzzer = 13;
// Leds que nos ayudarán a saber qué servo se está moviendo.
const int ledWh = 9;
const int ledGr = 10;
const int ledOr = 11;
const int ledYe = 12;
const int ledRed = 4;
// Declaramos los servos.
Servo servo1;
Servo servo2;
Servo servo3;
Servo servo4;
Servo servo5;
char instruccion[61];
const int timeThreshold = 150; // costante para el rebote
long timeCounter = 0;

void setup() {
  Serial.begin(9600);
  pinMode(pinBoton, INPUT);
  pinMode(buzzer, OUTPUT);
  noTone(buzzer);
  pinMode(ledWh, OUTPUT);
  pinMode(ledGr, OUTPUT);
  pinMode(ledOr, OUTPUT);
  pinMode(ledYe, OUTPUT);
  pinMode(ledRed, OUTPUT);
  servo1.attach(4); //Servo 360.
  servo2.attach(5); // Cadera.
  servo3.attach(6); // Codo.
  servo4.attach(7); //Muñeca.
  servo5.attach(8); //Pinza.
  attachInterrupt(digitalPinToInterrupt(pinBoton), parar, HIGH); //Si llega a suceder algo que no haga el brazo, cortamos el circuito por el pin 2 para detenerlo.
}

void loop() {
  noTone(buzzer);
  digitalWrite(ledWh, LOW);
  digitalWrite(ledGr, LOW);
  digitalWrite(ledOr, LOW);
  digitalWrite(ledYe, LOW);
  digitalWrite(ledRed, LOW);
  int conLeer = 0;
  int i = 0;
  int valAnterior = 1;
  char modo[1];
  EEPROM.get(1000, modo);// En la posicion 1000 se guardara la variable para ver si el modo esta en automatico o de escucha
  if (modo[0] == '1') { // Si el valor es 1 entonces esta en modo automatico
    EEPROM.get(0, instruccion); // Se obtiene la cadena de instruccion de la posicion 0 en adelante
    EEPROM.get(1001, valAnterior);// Se optiene el valor guardado en la posicion 1001, el cual tiene el movimiento en el que se quedo arduino, esto en caso de se le corte la energia a aurduino
    for (i = valAnterior; i < 61; i = i + 2) {
      if (instruccion[i] == '@') {// Si se encuentra un @ quiere decir que es el fin de la instruccion
        EEPROM.put(1001, 1);// ´por ende se pasa e valor anterior a 1
        break;
      } else {
        switch (instruccion[i]) {
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
  } else if (modo[0] == '0') {// Si se optiene un 0 entonces es un movimiento de escucha, solo lo realizara sin guardar nada
    if (Serial.available() > 0) {
      delay(100);
      while (Serial.available() > 0) {
        instruccion[conLeer] = Serial.read();
        conLeer++;
      }
      if (instruccion[0] == '0') {//Cuando lee la cadena, si encuentra un 0 solo hace el movimiento
        EEPROM.put(1000, '0');
        switch (instruccion[1]) {
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
      } else if (instruccion[0] == '1') {// Si encuentra un 1, lo guarda en la posicion 0, pone un 1 en la 1000 para que sepa que esta en modo automatico, y pone el movimiento anterior en 1
        EEPROM.put(1000, '1');
        EEPROM.put(1001, 1);
        EEPROM.put(0, instruccion);
      }
    }
  }
}

void parar() {
  int sig;
  if (millis() > timeCounter + timeThreshold) {//Rebote
    EEPROM.put(1000, '0');//Se pone un 0 para que entre en modo escucha
    EEPROM.get(1001, sig);// Se optiene la posicion del siguiente movimiento
    instruccion[sig] = '@';// Se pone un @ en la siguiente posicion para que pare de hacer movimientos
    digitalWrite(ledRed, HIGH);
  }
}

void moverServo1(int ultimo) {
  int valor = calValor(instruccion[ultimo + 1]);// se calcula el valor que representa el char
  servo1.write(valor);// Se le manda al servo
  EEPROM.put(1001, (ultimo + 2));// Se guarda el movimiento, para saber en el que se queda en caso de apagado
  delay(1000);
  tone(buzzer, 500);
  delay(500);
  noTone(buzzer);
}
void moverServo2(int ultimo) {
  int valor = calValor(instruccion[ultimo + 1]);
  servo2.write(valor);
  EEPROM.put(1001, (ultimo + 2));
  delay(1000);
  digitalWrite(ledWh, HIGH);
  delay(500);
  tone(buzzer, 600);
  delay(500);
  noTone(buzzer);
}

void moverServo3(int ultimo) {
  int valor = calValor(instruccion[ultimo + 1]);
  servo3.write(valor);
  EEPROM.put(1001, (ultimo + 2));
  delay(1000);
  digitalWrite(ledGr, HIGH);
  delay(500);
  tone(buzzer, 700);
  delay(500);
  noTone(buzzer);
}

void moverServo4(int ultimo) {
  int valor = calValor(instruccion[ultimo + 1]);
  servo4.write(valor);
  EEPROM.put(1001, (ultimo + 2));
  delay(1000);
  digitalWrite(ledOr, HIGH);
  delay(500);
  tone(buzzer, 800);
  delay(500);
  noTone(buzzer);
}
void moverServo5(int ultimo) {
  int valor = calValor(instruccion[ultimo + 1]);
  if (valor == 60) {
    valor = 64;
  } else if (valor == 110) {
    valor = 105;
  }
  servo5.write(valor);
  EEPROM.put(1001, (ultimo + 2));
  delay(1000);
  digitalWrite(ledYe, HIGH);
  delay(500);
  tone(buzzer, 900);
  delay(500);
  noTone(buzzer);
}

int calValor(char letra) {
  int va = 0;
  switch (letra) {
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
