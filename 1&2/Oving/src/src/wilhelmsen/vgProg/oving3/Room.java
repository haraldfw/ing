package src.wilhelmsen.vgProg.oving3;

import java.util.ArrayList;

/**
 * Created by Harald Wilhelmsen on 14. Jan 2015.
 */
public class Room {

	private ArrayList<Reservation> reservations;

	public final int seats;

	public Room(int seats) {
		this.seats = seats;
		reservations = new ArrayList<Reservation>();
	}

	public void book(AtTime fromTime, AtTime toTime, String custName, String custTlf) {
		reservations.add(new Reservation(fromTime, toTime, new Customer(custName, custTlf)));
	}

	public boolean isFree(AtTime start, AtTime end) {
		for(Reservation r : reservations) {
			if(r.overlapp(start, end)) return false;
		}
		return true;
	}

	@Override
	public String toString() {
		String s = "Room " + super.toString();
		s += "\n";
		s += "\tSeats: " + seats;
		s += "\n\t";
		s += reservations.isEmpty() ? "No reservations" : "Reservations: ";
		for(Reservation r : reservations) {
			s += "\n";
			s += r;
		}
		return s;
	}
}
