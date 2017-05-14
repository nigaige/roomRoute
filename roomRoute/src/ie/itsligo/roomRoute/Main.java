package ie.itsligo.roomRoute;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;


import javax.swing.JTextField;

import com.google.zxing.EncodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class Main {

		static JTextField textField = null;
		static String qrCodeData = "Day: Tuesday\nTime: 09:00 to 11:00\nSubject: Software Engineering\nRoom: A2004";
		static String filePath = "myQRCode.png";
		
		static Date d = new Date() ;
		
		static QR qr = new QR(qrCodeData, filePath);
		static Room room = new Room();
		static Directions directions = new Directions();

		@SuppressWarnings({ "unchecked", "rawtypes" })
		public static void main(String[] args) throws WriterException, IOException, NotFoundException {
			// Initial hardcoded data for test program 
			String qrCodeData = "Day: Monday\nTime: 23:00 to 24:00\nSubject: Software Engineering\nRoom: A5004";		//line to be modified for different input
			String filePath = "generator/myQRCode.png";
			
			Map hintMap = new HashMap(); 
			hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

			// create the QR barcode
			/*qr.createQRCode(qrCodeData, filePath, hintMap, 200, 200);
			System.out.println("QR Code image created successfully!");*/

			// read the barcode
			String QRdata = qr.readQRCode(filePath, hintMap);

			//System.out.println("The barcode reads : " + QRdata/*spRoom[1]*/);
			//find the hour
			
			// Find the room
			String QRdataSp[] = QRdata.split("\n");
			String dump[]=QRdataSp[3].split(": ");
			room.get(dump[1]);
	
			//display info on the date
			String dayQR = QRdataSp[0].split(" ")[1].substring(0,3);
			String hourSp[] = QRdataSp[1].split(" ");
			System.out.println("the event is on :" + dayQR );
			System.out.println("it starts at : " + hourSp[1] );
			System.out.println("and end at : " + hourSp[3]);
			
			//get today date information
			String comDate= d.toString();
			String today = comDate.split(" ")[0];
			String hour = comDate.split(" ")[3];
			
			//using the today date to know if the event have already happen, or in how long it s going to happen 
			if (!today.equals(dayQR)){
				System.out.println("the event is not today");				
			}
			String nhourQR = hourSp[1].split(":")[0];
			String nhour = hour.split(":")[0];
			
			try {
				int hQR = Integer.parseInt(nhour);
				int h = Integer.parseInt(nhourQR);
				int min = Integer.parseInt(hourSp[1].split(":")[1]);
				int minQR = Integer.parseInt(hour.split(":")[1]);
				
				if(hQR < h){
					if (minQR < min){
						System.out.println("the event start in " + (h-hQR) + ":" + (min-minQR));				
					}else{
						System.out.println("the event start in " + (h-hQR-1) + ":" + (60 + min-minQR));	
					}				
					//get info on the room
					System.out.println("The room is : " + room.show());
					String block = room.show().substring(0,1);
					System.out.println("The Block is the " + block + ", which is:");					
					
					//display info on the room
					switch(block){
						case "E":
							System.out.println("The engineering block" );
							break;
						case "B":
							System.out.println("The science block" );
							break;
						case "D":
							System.out.println("The business block" );
							break;
						case "F":
							System.out.println("The F block" );
							break;
						case "A":
							System.out.println("The Main block" );
							break;
						default:
							System.out.println("This block doesn't exit, proced to the office for information" );
							break;		
					}
					

					
					
					String theRoom = room.show();
					
					// get directions
					if (directions.validate(theRoom) == false) {
						System.out.println("The directions to this room are unknown");
					}
					else {
						System.out.println("DIRECTIONS");
						System.out.println(directions.toBuilding());
						System.out.println(directions.toFloor());
						System.out.println(directions.toLocation());
					}

				}else{
					System.out.println("Sorry,the event as already started");
				}
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				System.out.println("The QR code information got corrupted, please proceed to the office for information");
			}		
			
			
			
		}
}

