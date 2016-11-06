package src.wilhelmsen.vgProg.oving3;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Harald Wilhelmsen on 14. Jan 2015.
 */
class ConferenceCenter {

	private HashMap<String, Room> rooms;

	public ConferenceCenter() {
		rooms = new HashMap<String, Room>();
	}

	public Room getRoom(int roomNumber) {
		return rooms.get(String.valueOf(roomNumber));
	}

	/**
	 * Returns the room at the given index. This method is not really necessary:
	 * In a realistic situation, no one would need to know where the room
	 * is located in the array, only what number it has on it's door.
	 * Hence this method is a lot heavier than getRoom(Integer rmNr);
	 * @param index
	 * @return
	 */
	public Room getRoomByIndex(int index) {
		return (Room)rooms.entrySet().toArray()[index];
	}

	public boolean bookRoom(String customerName, String tlf,
						 int seats, AtTime fromTime, AtTime toTime) {
		Room r = getRoom(seats, fromTime, toTime);

		if(r == null) return false;

		r.book(fromTime, toTime, customerName, tlf);
		return true;
	}

	private Room getRoom(int seats, AtTime fromTime, AtTime toTime) {
		Room bestRoomSoFar = null;
		for(Map.Entry e : rooms.entrySet()) {
			Room r = (Room)e.getValue();
			System.out.println(r);
			if(seats <= r.seats && r.isFree(fromTime, toTime)) {
				// hvis det finnes et rom som har et nærmere antall seter til det klienten
				//	spør om blir det rommet prioritert
				if(bestRoomSoFar == null) {
					bestRoomSoFar = r;
				} else if(r.seats < bestRoomSoFar.seats) {
					bestRoomSoFar = r;
				}
			}
		}
		return bestRoomSoFar;
	}

	public boolean newRoom(int seats, int number) {
		if(rooms.get(String.valueOf(seats)) != null) return false;
		rooms.put(String.valueOf(seats), new Room(seats));
		return true;
	}

	@Override
	public String toString() {
		String s = "ConferenceCenter " + super.toString();
		s += "\n";
		for(Map.Entry e : rooms.entrySet()) {
			s += (Room) e.getValue();
			s += "\n";
		}
		return s;
	}

	private static int promptInt(String message) {
		boolean success = false;
		while(!success) {
			int i;
			try {
				i = Integer.parseInt(JOptionPane.showInputDialog(message));
				return i;
			} catch(Exception e) {
				success = false;
			}
		}
		return 0;
	}

	private static long promptLong(String message) {
		boolean success = false;
		while(!success) {
			long l;
			try {
				l = Long.parseLong(JOptionPane.showInputDialog(message));
				return l;
			} catch(Exception e) {
				success = false;
			}
		}
		return 0;
	}

	private static String promptString(String message) {
		String s = "";
		while(s.length() <= 0) {
			s = JOptionPane.showInputDialog(message);
		}
		return s;
	}

	public void promptNewRoom() {
		int roomNumber = 0;
		while(true) {
			int number = promptInt("Enter new room number: ");
			if(getRoom(number) == null) {
				roomNumber = number;
				break;
			}
		}
		int seats = promptInt("Enter seats");
		rooms.put(String.valueOf(roomNumber), new Room(seats));
	}

	public void promptBooking() {
		String name = promptString("Enter name:");
		String tlf = promptString("Enter cell-phone:");
		int seats = promptInt("Enter amount of seats:");
		long sDate = promptLong("Enter start-time in format YYYYMMDDHHMM:");
		long eDate = promptLong("Enter end-time in format YYYYMMDDHHMM:");
		boolean b = bookRoom(name, tlf, seats, new AtTime(sDate), new AtTime(eDate));
		JOptionPane.showMessageDialog(null, "Booking unsuccessful");
	}

	public static void main(String[] args) {
		ConferenceCenter c = new ConferenceCenter();
		/*
		c.newRoom(30, 0);
		c.newRoom(35, 1);
		c.newRoom(12, 2);
		c.newRoom(5, 3);
		c.newRoom(49, 4);
		c.bookRoom("Anna A", String.valueOf(12345678), 10, new AtTime(201512281230l), new AtTime(201512281830l));
		c.bookRoom("Anna A", String.valueOf(12345678), 10, new AtTime(201512281230l), new AtTime(201512281830l));
		c.bookRoom("Anna A", String.valueOf(12345678), 30, new AtTime(201512281230l), new AtTime(201512281830l));
		c.bookRoom("Anna A", String.valueOf(12345678), 5, new AtTime(201512281230l), new AtTime(201512281830l));
		c.bookRoom("Anna A", String.valueOf(12345678), 49, new AtTime(201512281230l), new AtTime(201512281830l));
		System.out.println(c);
		*/
		while(true) {
			int selection = -1;
			while(selection < 0 || selection > 3) {
				selection = promptInt("Actions:\n0. Status\n1. NewRoom\n2. Book Room\n3. Exit");
			}
			switch(selection) {
				case 0:
					System.out.println(c);
					JOptionPane.showMessageDialog(null, c);
					break;
				case 1:
					c.promptNewRoom();
					break;
				case 2: // case
					c.promptBooking();
					break;
				default: // case 3 exit
					return;
			}
		}
	}
}
