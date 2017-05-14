/*
 * Class: Generator
 *
 * Description: This class is called when a button is pressed to stock the value for the QRcode
 * If the Button generate is pressed, the QRcode will be genrated
 *
 * @Author : Gaigé Nicolas
 * @Version : 1.0
 * @Date : may 2017
 */


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;

import javax.swing.JTextField;
import com.google.zxing.EncodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.WriterException;


public class Generator { 

    // values to be stored in the generator memory 
    private int day = 1; //1 = monday ... 
    private int sBlock = 1; //1 = block A...
    private int shour = 0;
    private int shourend = 0;
    private int stage = 0;
    private int sroom = 1;//1=3;2=4;3=6;4=7
    static String data = "";

    @SuppressWarnings({ "unchecked", "rawtypes" })
    static String qrCodeData = "Day: Tuesday\nTime: 09:00 to 11:00\nSubject: Software Engineering\nRoom: A2004";
    static String filePath = "myQRCode.png";
    static QR qr = new QR(qrCodeData, filePath);

    // the user hits a day button button 
    public void monday () {
        int day =1;
    } 

    public void tuesday () {
        int day =2;
    }

    public void wednesday () {
        int day =3;
    } 

    public void thursday () {
        int day =4;
    } 

    public void friday () {
        int day =5;
    } 

    public void saturday () {
        int day =6;
    } 

    public void sunday () {
        int day =7;
    } 

    public void hour (int hour){
        shour = hour;
    }

    public void hourend (int hour){
        shourend = hour;
    }

    public void Block (int Block){
        sBlock = Block;
    }

    public void stage (int stagen){
        stage = stagen;
    }

    public void room (int room){
        sroom = room;
    }

    public void generate(){
        data = "";
        data = data + "Day: ";
        //for the day      
        switch(day){
            case 1:
            data = data + "Monday";
            break;
            case 2:
            data = data + "Tuesday";
            break;
            case 3:
            data = data + "Wednesday";
            break;
            case 4:
            data = data + "Thursay";
            break;
            case 5:
            data = data + "Friday";
            break;
            case 6:
            data = data + "Saturday";
            break;
            case 7:
            data = data + "Sunday";
            break;
            default:
            data = data + "Monday";
            break;
        }
        data = data + "\nTime: " + shour + ":00 to " + shourend + ":00\nSubject: Software Engineering\nRoom: ";
        switch (sBlock){
            case 1:
            data = data + "A";
            break;
            case 2:
            data = data + "B";
            break;
            case 3:
            data = data + "C";
            break;
            case 4:
            data = data + "D";
            break;
            case 5:
            data = data + "E";
            break;
            case 6:
            data = data + "F";
            break;
            default:
            data = data + "A";
            break;
        }

        data = data + stage + "00";

        switch (sroom){
            case 1:
            data = data + "3";
            break;
            case 2:
            data = data + "4";
            break;
            case 3:
            data = data + "6";
            break;
            case 4:
            data = data + "7";
            break;
            default:
            data = data + "1";
            break;
        }
        String filePath = "myQRCode.png";
        Map hintMap = new HashMap();
        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

        // create the QR barcode
        try{
            qr.createQRCode(data, filePath, hintMap, 200, 200);
        }catch(Exception e){            
        }
    }
}