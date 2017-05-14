package ie.itsligo.roomRoute;

public class Room {
	private String room;

	public Room() {
		
	}
	
	public String get(String data)
	{
		room = data;
		// TODO extract the room number eg E2004 from the data passed in
		// Here you write the code to parse the data string and extract
		// the room
		return(room);
	}
	public String show(){
		return room;
		
		
		
	}

}
