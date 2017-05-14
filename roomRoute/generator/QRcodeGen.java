/*
 * Class: QRcodeGen
 *
 * Description:This class allow the creation of a QRcode for our project of software ingeneering
 *
 * @Author : Gaigé nicolas
 * @Version : 1.0
 * @Date : may 2017
 */
import javax.swing.*; 
import java.awt.*; 
import java.awt.event.ActionListener; 
import java.awt.event.ActionEvent;
import java.lang.*;

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

public class QRcodeGen implements ActionListener { 

    // Create an instance of the calculator class to perform calculations.
    private Generator c = new Generator (); 

    // The buttons and text
    /*private JFrame frame = new JFrame ("Calculator"); 
    private JPanel[] panels = new JPanel [6]; 
    private JTextField textField = new JTextField(); 
    private JButton resetButton = new JButton(" C "); 
    private JButton[] numberButtons = new JButton[10]; 
    private JButton divideButton = new JButton ("/"); 
    private JButton multiplyButton = new JButton ("*"); 
    private JButton subtractButton = new JButton ("-"); 
    private JButton plusMinusButton = new JButton ("+/-"); 
    private JButton dotButton = new JButton (" . "); 
    private JButton addButton = new JButton ("+"); 
    private JButton equateButton = new JButton (" = "); 
    private JButton sqrtButton = new JButton ("sqrt");
    private JButton powButton = new JButton ("pow");
     */
    private JFrame frame = new JFrame ("QRcode Generator"); 
    private JPanel[] panels = new JPanel [8];  
    private JButton monday = new JButton("Monday");
    private JButton tuesday = new JButton("Tuesday");
    private JButton wednesday = new JButton("Wednesday");
    private JButton thursday = new JButton("Thursday");
    private JButton friday = new JButton("Friday");
    private JButton saturday = new JButton("Saturday");
    private JButton sunday = new JButton("Sunday");

    private JButton[] hour = new JButton[50]; 
    private JButton[] Block = new JButton[6];
    private JButton[] Floor = new JButton[3];
    private JButton[] Room = new JButton[4];
    private JButton generate = new JButton("generate");

    private String day="Monday";
    private int shour = 0;
    private int ehour = 0;
    private String Blocks = "A";
    private int Stages = 0;
    private int rooms = 3;
    private String data = "";

    static String qrCodeData = "Day: Tuesday\nTime: 09:00 to 11:00\nSubject: Software Engineering\nRoom: A2004";
    static String filePath = "myQRCode.png";
    static QR qr = new QR(qrCodeData, filePath);

    // 
    // Initial setup function
    //
    public void buildGUI () { 

        /*
         *  The calculator is laid out in a frame.  The frame contains a number of rows.
         *  Each row contains a number of buttons.
         */
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

        JPanel contentPane = (JPanel) frame.getContentPane(); 

        // initialize panels 
        for (int i = 0; i < panels.length; i++) { 
            panels[i] = new JPanel (); 
        } 

        

        
        
        panels[1].setLayout (new FlowLayout (FlowLayout.LEFT)); 
        panels[1].add (monday); 
        monday.setActionCommand ("MONDAY"); 
        monday.addActionListener (this);
        panels[1].add (tuesday); 
        tuesday.setActionCommand ("TUESDAY"); 
        tuesday.addActionListener (this);
        panels[1].add (wednesday); 
        wednesday.setActionCommand ("WEDNESDAY"); 
        wednesday.addActionListener (this);
        panels[1].add (thursday); 
        thursday.setActionCommand ("THURSDAY"); 
        thursday.addActionListener (this);
        panels[1].add (friday); 
        friday.setActionCommand ("FRIDAY"); 
        friday.addActionListener (this);
        panels[1].add (saturday); 
        saturday.setActionCommand ("SATURDAY"); 
        saturday.addActionListener (this);
        panels[1].add (sunday); 
        sunday.setActionCommand ("SUNDAY"); 
        sunday.addActionListener (this);

        for (int i = 0; i < 50; i++) { 
            if (i<25){
                hour[i] = new JButton (" " + i + " "); 
                hour[i].setActionCommand (String.valueOf(i)); 
                hour[i].addActionListener (this); 
                panels[2].add (hour[i]);
            }
            if (i>=25){
                hour[i] = new JButton (" " + (i-25) + " "); 
                hour[i].setActionCommand (String.valueOf(i)); 
                hour[i].addActionListener (this); 
                panels[3].add (hour[i]);
            }

        } 
   
        Block [0] = new JButton (" A ");
        Block[0].setActionCommand ("A");  
        Block [1] = new JButton (" B ");
        Block[1].setActionCommand ("B");  
        Block [2] = new JButton (" C ");
        Block[2].setActionCommand ("C");  
        Block [3] = new JButton (" D ");
        Block[3].setActionCommand ("D");  
        Block [4] = new JButton (" E ");
        Block[4].setActionCommand ("E");  
        Block [5] = new JButton (" F ");
        Block[5].setActionCommand ("F");  
        panels[4].setLayout (new FlowLayout (FlowLayout.LEFT)); 
        for (int i = 0; i < 6; i++) {

            Block[i].addActionListener (this); 
            panels[4].add (Block[i]);   
        } 

        Floor [0] = new JButton (" 0");
        Floor [1] = new JButton (" 1 ");
        Floor [2] = new JButton (" 2 ");
        panels[5].setLayout (new FlowLayout (FlowLayout.LEFT)); 
        for (int i = 0; i < 3; i++) {
            Floor[i].setActionCommand (String.valueOf(-i-1)); 
            Floor[i].addActionListener (this); 
            panels[5].add (Floor[i]);   
        } 

        Room [0] = new JButton (" 3 ");
        Room [1] = new JButton (" 4 ");
        Room [2] = new JButton (" 6 ");
        Room [3] = new JButton (" 7 ");
        panels[6].setLayout (new FlowLayout (FlowLayout.LEFT)); 
        for (int i = 0; i < 4; i++) {
            Room[i].setActionCommand (String.valueOf(i +100)); 
            Room[i].addActionListener (this); 
            panels[6].add (Room[i]);   
        }

        panels[7].add (generate); 
        generate.setActionCommand ("generate"); 
        generate.addActionListener (this);



        contentPane.setLayout (new BoxLayout (contentPane, BoxLayout.Y_AXIS)); 
        for (JPanel jPanel : panels) { 
            contentPane.add (jPanel); 
        } 

        frame.pack (); 
        frame.setVisible (true); 
    } 

    //
    //  Methods called when a button is pressed.
    //  This method is responsible for performing the calculation when the 
    //  buttons are pressed.
    // 
    public void actionPerformed (ActionEvent e) { 
        String actionCommand = e.getActionCommand(); 
        System.out.println(actionCommand);
        
        if (actionCommand == "generate"){

            data = "Day: " + day + "\nTime: "+ shour + ":00 to "+ ehour + ":00\nSubject: Softaware Engineering\nRoom: " + Blocks + Stages + "00" + rooms;    
            //data = "Day: Tuesday\nTime: 14:00 to 15:00\nSubject: Software Engineering\nRoom: 88888";
       
            System.out.println(data);
            String filePath = "myQRCode.png";
            Map hintMap = new HashMap();
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            try{
                qr.createQRCode(data, filePath, hintMap, 200, 200);
            }catch(Exception excep){            
            }
        }

        if (actionCommand == "MONDAY"){
            day = "Monday";            System.out.println(day);
        }
        if (actionCommand == "TUESDAY"){
            day = "tuesday";
                        System.out.println(day);
        }
        if (actionCommand == "WEDNESDAY"){
            day = "wednesday";
                        System.out.println(day);
        }
        if (actionCommand == "THURSDAY"){
            day = "Thursday";
                        System.out.println(day);
        }
        if (actionCommand == "FRIDAY"){
            day = "Friday";
                        System.out.println(day);
        }
        if (actionCommand == "SATURDAY"){
            day = "Saturday";
                        System.out.println(day);
        }
        if (actionCommand == "SUNDAY"){
            day = "Sunday";
            System.out.println(day);

        }

        int stocks = Integer.parseInt(actionCommand);

        if (stocks < 50 && stocks > 0){
            if (stocks < 25){
                shour = stocks;
            }
            if (stocks > 25){
                ehour = stocks-25;
            }
        }    

        if (actionCommand == "A" ||actionCommand == "B" ||actionCommand == "C" ||actionCommand == "D" ||actionCommand == "E" ||actionCommand == "F"){
            Blocks = actionCommand; 
        }

        if (stocks<0){
            Stages = -stocks-1;
        }

        if (stocks >=100){
            rooms = stocks-100;
            if (rooms == 0){rooms =3;}
            else if (rooms == 1){rooms = 4;}
            else if (rooms == 2){rooms = 6;}
            else if (rooms == 3){rooms = 7;}
        }


    
        if (actionCommand == null || actionCommand.trim().length() <= 0) { 
            return; 
        } 

        int number = -1; 
        try { 
            number = Integer.parseInt (actionCommand); 
        } catch (NumberFormatException e1) { 
        } 

    } 
    public static void main(String[] args) { 
        QRcodeGen gui = new QRcodeGen(); 
        gui.buildGUI (); 
    } 
}