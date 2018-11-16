package Recursos;

public class MovesValues {
    
    public int defineRanges(int value){
        if(value>0 && value<=10){
            return 10;
        }else if(value>10 && value<=20){
            return 20;
        }else if(value>20 && value<=30){
            return 30;
        }else if(value>30 && value<=40){
            return 40;
        }else if(value>40 && value<=50){
            return 50;
        }else if(value>50 && value<=60){
            return 60;
        }else if(value>60 && value<=70){
            return 70;
        }else if(value>70 && value<=80){
            return 80;
        }else if(value>80 && value<=90){
            return 90;
        }else if(value>90 && value<=100){
            return 100;
        }else if(value>100 && value<=110){
            return 110;
        }else if(value>110 && value<=120){
            return 120;
        }else if(value>120 && value<=130){
            return 130;
        }else if(value>130 && value<=140){
            return 140;
        }else if(value>140 && value<=150){
            return 150;
        }else if(value>150 && value<=160){
            return 160;
        }else if(value>160 && value<=170){
            return 170;
        }else if(value>170 && value<=180){
            return 180;
        }else if(value>180 && value<=190){
            return 190;
        }else if(value>190 && value<=200){
            return 200;
        }else if(value>200 && value<=210){
            return 210;
        }else if(value>210 && value<=220){
            return 220;
        }else if(value>220 && value<=230){
            return 230;
        }else if(value>230 && value<=240){
            return 240;
        }else if(value>240 && value<=250){
            return 250;
        }else if(value>250 && value<=260){
            return 260;
        }else if(value>260 && value<=270){
            return 270;
        }else if(value>270 && value<=280){
            return 280;
        }else if(value>280 && value<=290){
            return 290;
        }else if(value>290 && value<=300){
            return 300;
        }else if(value>300 && value<=310){
            return 310;
        }else if(value>310 && value<=320){
            return 320;
        }else if(value>320 && value<=330){
            return 330;
        }else if(value>330 && value<=340){
            return 340;
        }else if(value>340 && value<=350){
            return 350;
        }else if(value>350 && value<=360){
            return 360;
        }else{return 0;}
    }
    public char convertToChar(int number){
        switch(number){
            case 0:
                return 'a';
            case 10:
                return 'b';
            case 20:
                return 'c';
            case 30:
                return 'd';    
            case 40:
                return 'e';
            case 50:
                return 'f';
            case 60:
                return 'g';
            case 70:
                return 'h';
            case 80:
                return 'i';
            case 90:
                return 'j';
            case 100:
                return 'k';
            case 110:
                return 'l';
            case 120:
                return 'm';
            case 130:
                return 'n';
            case 140:
                return 'o';
            case 150:
                return 'p';
            case 160:
                return 'q';
            case 170:
                return 'r';
            case 180:
                return 's';
            case 190:
                return 't';
            case 200:
                return 'u';
            case 210:
                return 'v';
            case 220:
                return 'x';
            case 230:
                return 'y';
            case 240:
                return 'z';
            case 250:
                return 'A';
            case 260:
                return 'B';
            case 270:
                return 'C';
            case 280:
                return 'D';
            case 290:
                return 'E';
            case 300:
                return 'F';
            case 310:
                return 'G';
            case 320:
                return 'H';
            case 330:
                return 'I';
            case 340:
                return 'J';
            case 350:
                return 'K';
            case 360:
                return 'L';
            default:
                return '>';
        }
    }
    public int convertToInt(char letter){
        switch(letter){
            case 'a':
                return 0;
            case 'b':
                return 10;
            case 'c':
                return 20;
            case 'd':
                return 30;    
            case 'e':
                return 40;
            case 'f':
                return 50;
            case 'g':
                return 60;
            case 'h':
                return 70;
            case 'i':
                return 80;
            case 'j':
                return 90;
            case 'k':
                return 100;
            case 'l':
                return 110;
            case 'm':
                return 120;
            case 'n':
                return 130;
            case '0':
                return 140;
            case 'p':
                return 150;
            case 'q':
                return 160;
            case 'r':
                return 170;
            case 's':
                return 180;
            case 't':
                return 190;
            case 'u':
                return 200;
            case 'v':
                return 210;
            case 'x':
                return 220;
            case 'y':
                return 230;
            case 'z':
                return 240;
            case 'A':
                return 250;
            case 'B':
                return 260;
            case 'C':
                return 270;
            case 'D':
                return 280;
            case 'E':
                return 290;
            case 'F':
                return 300;
            case 'G':
                return 310;
            case 'H':
                return 320;
            case 'I':
                return 330;
            case 'J':
                return 340;
            case 'K':
                return 350;
            case 'L':
                return 360;
            default:
                return -1;
        }
    }
}
