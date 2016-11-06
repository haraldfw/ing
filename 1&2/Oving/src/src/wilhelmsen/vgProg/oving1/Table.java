package src.wilhelmsen.vgProg.oving1;

import java.util.Date;
import java.util.HashMap;

/**
 * Created by Harald Wilhelmsen on 08. Jan 2015.
 */
public class Table {


	public final int seats;
	private final HashMap<String, Reservation> reservations;

	public Table(int seats) {
		this.seats = seats;
		reservations = new HashMap<String, Reservation>();
	}

	/**
	 * Return whether this table is free at the given time and date
	 * @param fromTime	The date in which to check if the table is free
	 * @return	True if the table is free
	 */
	public boolean isFree(long fromTime, long toTime) {
		boolean isFree = false;
		if(reservations.isEmpty()) return true;
		for(HashMap.Entry<String, Reservation> e : reservations.entrySet()) {
			if(toTime < e.getValue().start || fromTime > e.getValue().end) return true;
		}
		return isFree;
	}

	/**
	 * Return whether the table is free right now
	 * @return	True if free, false otherwise
	 */
	public boolean isFree() {
		return isFree(System.currentTimeMillis());
	}

	public boolean isFree(long atTime) {
		return isFree(atTime, atTime + 2*3600*1000);
	}

	/**
	 * Books this table for two hours(real time) from the given time
	 * @param fromTime
	 * @return
	 */
	public boolean book(String name, long fromTime) {
		if(!isFree(fromTime)) return false;
		Reservation r = new Reservation(fromTime);
		reservations.put(name, r);
		return true;
	}

	public boolean book(String name, long fromTime, long toTime) {
		if(!isFree(fromTime, toTime)) return false;
		Reservation r = new Reservation(fromTime, toTime);
		reservations.put(name, r);
		return true;
	}

	@Override
	public String toString() {
		String s = new String(", " + seats + " seats\nStatus: " + (isFree() ? "Open" : "Taken"));
		if(!reservations.isEmpty())s += "\nReservations: ";
		for(HashMap.Entry<String, Reservation> e : reservations.entrySet()) {
			s += "\n" + e.getKey() + " " + e.getValue();
		}
		return s;
	}

	public boolean isReservedBy(String name) {
		for(HashMap.Entry<String, Reservation> e : reservations.entrySet()) {
			if(e.getKey().equals(name)) return true;
		}
		return false;
	}

	private class Reservation {
		private final long start;
		private final long end;

		/**
		 * Books table for two hours starting at given time
		 * @param start	Start at time
		 */
		private Reservation(long start) {
			this.start = start;
			this.end = start + 2*60*60*1000;
		}

		private Reservation(long start, long end) {
			this.start = start;
			this.end = end;
		}

		private boolean isExpired() {
			return System.currentTimeMillis() > end;
		}

		private boolean isExpired(long atTime) {
			return atTime > end;
		}

		private boolean isActive() {
			return System.currentTimeMillis() > start && !isExpired();
		}

		@Override
		public String toString() {
			return "[" + new Date(start) + " - " + new Date(end) + "]";
		}
	}
}
