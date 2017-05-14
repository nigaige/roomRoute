package ie.itsligo.roomRoute;



import java.io.File;
import java.io.IOException;
import java.io.*;
import sun.audio.*;





public class Directions {
	final int BUILDING_A = 1; 
	final int BUILDING_B = 2;
	final int BUILDING_C = 3;
	final int BUILDING_D = 4;
	final int BUILDING_E = 5;
	final int BUILDING_F = 6;
	final int BUILDING_ERROR = 7;
	final int ROOM3 = 8;
	final int ROOM4 = 9;
	final int ROOM67 = 10;
	final int ROOM_ERROR = 11;
	final int STAGE_0 = 12;
	final int STAGE_1 = 13;
	final int STAGE_2 = 14;
	final int STAGE_ERROR = 15;
		
	
	public void playit(int soundRequired){
		String fn = null;
		File sound; 
		
		switch (soundRequired) {
			case BUILDING_A:
				fn = "./src/sound/Building_A.wav";
				break;
			case BUILDING_B:
				fn = "./src/sound/Building_B.wav";
				break;
			case BUILDING_C:
				fn = "./src/sound/Building_C.wav";
				break;
			case BUILDING_D:
				fn = "./src/sound/Building_D.wav";
				break;
			case BUILDING_E:
				fn = "./src/sound/Building_E.wav";
				break;
			case BUILDING_F:
				fn = "./src/sound/Building_F.wav";
				break;
			case BUILDING_ERROR:
				fn = "./src/sound/Building_Error.wav";
				break;
			case ROOM3:
				fn = "./src/sound/Room_3.wav";
				break;
			case ROOM4:
				fn = "./src/sound/Room_4.wav";
				break;
			case ROOM67:
				fn = "./src/sound/Room_67.wav";
				break;
			case ROOM_ERROR:
				fn = "./src/sound/Room_Error.wav";
				break;
			case STAGE_0:
				fn = "./src/sound/Stage_0.wav";
				break;
			case STAGE_1:
				fn = "./src/sound/Stage_1.wav";
				break;
			case STAGE_2:
				fn = "./src/sound/Stage_2.wav";
				break;
			case STAGE_ERROR:
				fn = "./src/sound/Stage_Error.wav";
				break;	
			default:
				break;
		}
		try {
		// Open an audio input stream.
		sound = new File(fn);
		InputStream inSound = new FileInputStream(sound);
		AudioStream audioStream = new AudioStream(inSound);
		AudioPlayer.player.start(audioStream);
		Thread.sleep(6000);
		/*AudioInputStream audioIn = AudioSystem.getAudioInputStream(sound);
		Clip clip = AudioSystem.getClip();
		// Open audio clip and load samples from the audio input stream.
		
		clip.open(audioIn);
		clip.start();*/

		// plays
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Throwable e) {
			e.printStackTrace();
		}

	}

	private final int ROOM_LENGTH = 5; // size of the room string
	private char building ;
	private char floor;
	private String locationOnFloor = null;

	public Directions() {

	}

	
	public char getBuilding() {
		return building;
	}


	public void setBuilding(char building) {
		this.building = building;
	}


	public char getFloor() {
		return floor;
	}


	public void setFloor(char floor) {
		this.floor = floor;
	}


	public String getLocationOnFloor() {
		return locationOnFloor;
	}

	public void setLocaationOnFloor(String locationOnFloor) {
		this.locationOnFloor = locationOnFloor;
	}

	/*
	 * This method takes in a room eg E2004 and splits up into the correct block
	 * (Engineering, Science, Business, etc) The correct floor The correct room
	 * number
	 */
	public boolean validate(String room) {
		if (room.length() != ROOM_LENGTH) {
			return false;
		}
		if (Character.isLetter(room.charAt(0)) == false) {
			return false; // room must start with a letter
		}
		for (int i = 1; i < ROOM_LENGTH; i++) {
			if (Character.isDigit(room.charAt(i)) == false) {
				return false; // room must start with a letter
			}
		}
		
		// all ok - store the info
		building = room.charAt(0);
		floor = room.charAt(1);
		locationOnFloor = room.substring(2);
		
		return true;
	}
	
	/*
	 * Get directions to building
	 */
	public String toBuilding() {
		String directions = null;
		switch (this.building) {
		case 'A':
			playit(BUILDING_A);
			directions = "From reception, walk straight ahead and then turn to your right";
			break;
		case 'B':
			playit(BUILDING_B);
			directions = "From reception, go up main stairs on your left and turn right.  Walk for 40m past Library until you read the Booknest";
			break;
		case 'C':
			playit(BUILDING_C);
			directions = "From reception, go up main stairs on your left and turn right.  Walk for 40m past Library until you read the Booknest, then turn left and conrinute through long corridor";
			break;
		case 'D':
			playit(BUILDING_D);
			directions = "rom reception, go up main stairs on your left and turn right.  Walk for 40m past Library until you read the Booknest, then turn left and conrinute through long corridor";
			break;
		case 'E':
			playit(BUILDING_E);
			directions = "From reception, move the the centre of reception and turn left into the engineering building";
			break;
		case 'F':
			playit(BUILDING_F);
			directions = "From reception, walk outside and turn to your right.  Walk past the engineering building and the F block is straigt in front";
			break;
		default:
			playit(BUILDING_ERROR);
			directions = "Sorry, that building in not recognised";
			break;
			
		}
		return(directions);
	}
	
	/*
	 * Get directions to floor
	 */
	public String toFloor() {
		String directions = null;
		switch (this.floor) {
		case '0':
			playit(STAGE_0);
			directions = "Stay on this floor";
			break;
		case '1':
			playit(STAGE_1);
			directions = "Ascend the stairs or take the lift to the first floor";			
			break;
		case '2':
			playit(STAGE_2);
			directions = "Ascend two flight of stairs or take the lift to the second floor";			
			break;
		default:
			playit(STAGE_ERROR);
			directions = "Sorry, floor " + this.floor + " is not recognised";
			break;
			
		}
		return(directions);
	}

	/*
	 * Get directions to floor
	 */
	public String toLocation() {
		String directions = null;
		switch (this.locationOnFloor) {
		case "006":
			playit(ROOM67);
			directions = "This is a room to the right on this level";
			break;
		case "007":
			playit(ROOM67);
			directions = "This is a room to the right on this level";			
			break;
		case "003":
			playit(ROOM3);
			directions = "This is the last room to the right on this level";			
			break;
		case "004":
			playit(ROOM4);
			directions = "This is the second last room to the right on this level";			
			break;
		default:
			playit(ROOM_ERROR);
			directions = "Sorry, that room in not recognised";
			break;			
		}
		return(directions);
	}
}
