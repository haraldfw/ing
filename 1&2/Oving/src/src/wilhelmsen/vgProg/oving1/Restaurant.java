package src.wilhelmsen.vgProg.oving1;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Harald Wilhelmsen on 08. Jan 2015.
 */
public class Restaurant {

	private String name;
	private long timeFounded;

	private Table[] tables;

	public static void main(String[] args) {
		Restaurant res = new Restaurant("Restaurant-name");

		res.bookSeveralTables("Carl Barks", 3, System.currentTimeMillis(), System.currentTimeMillis() + 2 * 3600 * 1000);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm");

		Date atDate = null;
		Date atDate2 = null;

		try {
			atDate = sdf.parse("2015-01-10 20-45");
			atDate2 = sdf.parse("2015-01-12 15-00");
		} catch(Exception e) {
			e.printStackTrace();
		}
		res.bookSingleTable("Single Sally", 1, atDate.getTime());
		res.bookSingleTable("Single Dingle", 4, atDate.getTime() + 8000000);
		System.out.println(res);
	}

	public Restaurant(String name) {
		this.name = name;
		timeFounded = System.currentTimeMillis();
		// Initiates the table-array with a test-set of tables
		tables = new Table[]{
									new Table(3),
									new Table(6),
									new Table(4),
									new Table(2),
									new Table(5),
									new Table(8),
									new Table(3),
									new Table(1),
									new Table(3),
									new Table(2),
									new Table(4)
		};
	}

	public void bookSingleTable(String toName, int seats, long fromTime, long toTime) {
		Table t = getTable(seats, fromTime, toTime);
		if(t == null) return;
		t.book(toName, fromTime, toTime);
	}

	public void bookSingleTable(String toName, int seats, long fromTime) {
		bookSingleTable(toName, seats, fromTime, fromTime + 2 * 3600 * 1000);
	}

	/**
	 * This method books the specified amount of tables to the given name.
	 * 	Not sure why this method is needed, and the exercise did not specify
	 * 	the number of seats each table needed, so I will make the assumption that
	 * 	one-seat tables will do. The algorithm will attempt to book as many tables
	 * 	as it can, and will return all successfully booked tables.
	 * @param toName	The name to book the table to
	 * @param amountOfTables	The amount of tables the person wanted
	 * @return	ArrayList of booked tables. Null if bookAnyNumber is false and the specified
	 * amount of tables could not be found.
	 */
	public boolean bookSeveralTables(String toName, int amountOfTables, long fromTime, long toTime) {
		boolean success = true;
		for(int i = 0; i < amountOfTables; i++) {
			Table t = getTable(1, fromTime, toTime);
			// If no free table at desired time found, set success to false.
			if(t == null) success = false;
			else {
				t.book(toName, fromTime, toTime);
			}
		}

		return success;
	}

	public boolean bookSeveralTables(String toName, int amountOfTables, long fromTime) {
		return bookSeveralTables(toName, amountOfTables, fromTime, fromTime + 2*3600000);
	}

	/**
	 * Finds a table with at least the specified amount of seats given
	 * @param minSeats	Minimum amount of seats on the table
	 * @param fromTime	The time at which the person wants the table
	 * @return A free table with at least the required amount of seats. Null if no such table exists or it is taken
	 */
	public Table getTable(int minSeats, long fromTime, long toTime) {
		Table free = null;
		for(Table t : tables) {
			if(t.isFree(fromTime, toTime) && t.seats >= minSeats && (free == null || free.seats > t.seats)) {
				free = t;
			}
		}
		return free;
	}

	public int getAmountOfTablesTaken(long time) {
		int i = 0;
		for(Table t : tables) if(!t.isFree(time)) i++;
		return i;
	}

	public int getAmountOfFreeTables(long time) {
		int i = 0;
		for(Table t : tables) if(t.isFree(time)) i++;
		return i;
	}

	public long timeRunningInDays() {
		return (System.currentTimeMillis() - timeFounded)/(1000*60*60*24);
	}

	@Override
	public String toString() {
		String s = new String("Welcome to " + name + "!\n" +
									  "We have been running for " + timeRunningInDays() + " days!\n");
		s += "Free tables: " + getAmountOfFreeTables(System.currentTimeMillis()) + "/" + tables.length;
		s += "\n";
		s += "Tables: ";
		int i = 0;
		for(Table t : tables) {
			i++;
			s += "\nTable " + i + t + "\n";
		}
		s += "\n";
		String checkFor = "Carl Barks";
		int[] ints = getBookedBy(checkFor);
		s += "Reserved table right now by " + checkFor + ": " + "[";
		for(i = 0; i < ints.length; i++) {
			if(i > 0) s += ", ";
			s += ints[i];
		}
		s += "]";
		return s;
	}

	public int[] getBookedBy(String name) {
		ArrayList<Integer> intList = new ArrayList<Integer>();

		for(int i = 0; i < tables.length; i++) {
			if(tables[i].isReservedBy(name)) intList.add(i);
		}
		int[] intArray = new int[intList.size()];
		for(int i = 0; i < intList.size(); i++) {
			intArray[i] = intList.get(i);
		}
		return intArray;
	}
}
