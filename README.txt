			Brazo Robótico.

  INSTITUTO TECNOLÓGICO NACIONAL DE MÉXICO
    	INSTITUTO TECNOLÓGICO DE LEÓN

    	Materia: Sistemas Programables.

    	   Fecha: 20/Nov/2018

Integrantes: 
	- Alvarez Castro Cynthia Aideé.
	- Hernández Márquez Isaac Ulises.
	- Pérez Segura Isaac.

Objetivo:
	Desarrollar paso a paso un brazo robótico para 
	controlar, programar y darle funciones básicas
	de un robot industrial.

Materiales a utilizar:
 	- 6 Leds de colores.
 	- Jumpers (hembra-macho y macho-macho).
 	- 4 Resistencias de 1k y  2 resistencias de 2k.
 	- 1 botón.
 	- Brazo robótico en piezas.
 	- 4 servo motores de 5v.
 	- 1 motor paso a paso.
 	- 1 buzzer.

Nota Importante:
	Antes de hacer el funcionamiento, se debe pasar el
	programa llamado inicio a arduino para preparar la 
	memoria EPROMM.

	El limite de movimientos que se pueden almacenar son 498.

Funcionamiento:
	El brazo robótico estará en modo standby al momento
	de inicializar. 
	Cuando se abra el programa en java, el puerto serial 
	estará listo para mandarle información al arduino.

	Nuestra interfaz cuenta con sliders para cada servo.
	Al igual, consta de una tabla para mandarle instrucciones
	y que las ejecute el arduino sin necesidad de dárselos
	manualmente. También, se puede importar y exportar las 
	instrucciones a un archivo de texto.

	¿Qué pasa si se desconecta del puerto serial? Sencillo,
	el programa de arduino está hecho para eso. Si se llega
	a cambiar el puerto COM o cerrar el programa, este 
	seguirá con la instrucción dada por el usuario anteriormente,
	donde dicha instrucción se guarda en la EEPROM del arduino.

	Gracias al buzzer y a los leds, podemos saber qué servo
	es el que se mueve. Cada uno de ellos tiene un tono 
	distinto así como su color de led.

