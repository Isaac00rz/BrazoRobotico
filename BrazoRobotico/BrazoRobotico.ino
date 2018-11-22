// Llamamos las librerías: EEPROM para modificar la memoria del Arduino.
//Servo para darle movimiento a cada uno de los que se utilizan.
#include<EEPROM.h>
#include <Servo.h>

const int pinBoton = 2; //Botón de paro.
const int buzzer = 13;
// Declaración de leds.
const int ledWh =  A0; //servo2.
const int ledGr =  A1; //servo3.
const int ledOr =  A2; //servo4.
const int ledYe =  A3; //servo5.
const int ledRed = A4; //motor pasos.

// Declaración de variables para el motor paso a paso.
const int motorN1 = 9;    // Amarillo In1
const int motorN2 = 10;    // Azul In2
const int motorN3 = 11;   // Naranja In3
const int motorN4 = 12;   // Verde In4

int valorActual= 0; //valor inicial del motor.
int conteoStep= 0; //contador para el motor.
int motorSpd = 1200; //Se fija una velocidad.
int stepsPerRev = 4096; //Pasos para una vuelta completa.
//estilo de secuencia: media fase.
const int numSteps = 8; //Número de pasos para el motor.
const int stepsLookup[8] = { B1000, B1100, B0100, B0110, B0010, B0011, B0001, B1001 };

// Declaración de micro servos a utilizar.
Servo servo2;
Servo servo3;
Servo servo4;
Servo servo5;
char instruccion[61];
const int timeThreshold = 150; // Costante para el rebote.
long timeCounter = 0;

void setup() {
  Serial.begin(9600);
  // El botón recibe una entrada; el buzzer recibe una salida.
  pinMode(pinBoton, INPUT);
  pinMode(buzzer, OUTPUT);
  //Cuando inicializa el programa, no debe escucharse el buzzer.
  noTone(buzzer);
  // Todos nuestros leds estarán en modo salida.
  pinMode(ledWh, OUTPUT);
  pinMode(ledGr, OUTPUT);
  pinMode(ledOr, OUTPUT);
  pinMode(ledYe, OUTPUT);
  pinMode(ledRed, OUTPUT);
  // Cuando inicializa, todos nuestros pines para controlar el
  // motor a pasos son de salida.
  pinMode(motorN1, OUTPUT);
  pinMode(motorN2, OUTPUT);
  pinMode(motorN3, OUTPUT);
  pinMode(motorN4, OUTPUT);
  //Indicamos a todos los servos el pin PWM que utilizan.
  servo2.attach(5); // Cadera.
  servo3.attach(6); // Codo.
  servo4.attach(7); //Muñeca.
  servo5.attach(8); //Pinza.
  //Si llega a suceder algo que no haga el brazo,
  //cortamos el circuito por el pin 2 para detenerlo.
  attachInterrupt(digitalPinToInterrupt(pinBoton), parar, HIGH);
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
  // En la posicion 1000 se guardara la variable para ver si
  //el modo esta en automatico o de escucha
  EEPROM.get(1000, modo);
  if (modo[0] == '1') { // Si el valor es 1 entonces esta en modo automatico
    // Se obtiene la cadena de instruccion de la posicion 0 en adelante
    EEPROM.get(0, instruccion);
    // Se optiene el valor guardado en la posicion 1001, el cual tiene el
    //movimiento en el que se quedo arduino, esto en caso de se le corte la energia a aurduino.
    EEPROM.get(1001, valAnterior);
    for (i = valAnterior; i < 61; i = i + 2) {
      // Si se encuentra un @ quiere decir que es el fin de la instruccion
      if (instruccion[i] == '@') {
        EEPROM.put(1001, 1);// ´por ende se pasa del valor anterior a 1
        break;
      } else {
        switch (instruccion[i]) {
          case '1':
            moverServo1(1);
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
  } else if (modo[0] == '0') {// Si se obtiene un 0 entonces es un movimiento de escucha, solo lo realizará sin guardar nada.
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
        // Si encuentra un 1, lo guarda en la posicion 0, pone un 1 en
        //la 1000 para que sepa que esta en modo automatico, y pone
        //el movimiento anterior en 1.
      } else if (instruccion[0] == '1') {
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
    //Se pone un 0 para que entre en modo escucha
    EEPROM.put(1000, '0');
    // Se optiene la posicion del siguiente movimiento
    EEPROM.get(1001, sig);
    // Se pone un @ en la siguiente posicion para que pare de hacer movimientos
    instruccion[sig] = '@';
    digitalWrite(ledRed, HIGH);
  }
}

void moverServo1(int ultimo) {
  // se calcula el valor que representa el char
  int valor = calValor(instruccion[ultimo + 1]);
  // calculamos el nuevo valor para el motor.
  /* Recordando que:
     4096, que es stepsPerRev, es un giro de 360 grados para un motor.
     Entonces, para darle un valor al motor no debe ser
     el valor que obtenemos del char, sino que debemos
     transformarlo a un valor que acepte el motor entre 0 y 4096.
     Esto se puede sacar con una regla de 3.
         360 grados - 4096 del motor.
         valor      - ?
     donde "?" es el valor que debe tomar el motor para que gire
     al grado ingresado en el programa.
  */
  
  int valorNuevo;
  valorNuevo= ((valor * stepsPerRev) / 360); //declaramos nuestra variable nueva.
  //setSalida contiene el write de los 4 pines utilizados en el arduino para modificar
  // el motor a pasos.
  if(valorNuevo >= valorActual)
  {
     for(int i = valorActual; i < valorNuevo; i++)
     {
      manecillasReloj();
      Serial.println(i);
      delayMicroseconds(motorSpd);
     }
  }else 
  {
    for (int i = valorActual; i > valorNuevo; i--)
    {
      manecillasRelojInversa();
      Serial.println(i);
      delayMicroseconds(motorSpd);
    }
  }
  valorActual = valorNuevo;
  // Se guarda el movimiento, para saber en el que se queda en caso de apagado.
  EEPROM.put(1001, (ultimo + 2));
  delay(1000);
  tone(buzzer, 500);
  delay(500);
  noTone(buzzer);
}

void manecillasReloj(){
  //Aumentar el contador de pasos
  conteoStep++;
  if (conteoStep >= numSteps) conteoStep = 0;
  setSalidaMotor(conteoStep);
}
 //Método para girar el motor a pasos en sentido contrario a las manecillas del reloj
void manecillasRelojInversa(){
  //Disminuir el contador de pasos
  conteoStep--;
  if (conteoStep < 0) conteoStep = numSteps - 1;
  setSalidaMotor(conteoStep);
}

void setSalidaMotor(int step)
{
  digitalWrite(motorN1, bitRead(stepsLookup[step], 0));
  digitalWrite(motorN2, bitRead(stepsLookup[step], 1));
  digitalWrite(motorN3, bitRead(stepsLookup[step], 2));
  digitalWrite(motorN4, bitRead(stepsLookup[step], 3));
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

//Valores dados a cada servo por medio de char.
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
