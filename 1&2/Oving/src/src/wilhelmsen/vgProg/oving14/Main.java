package src.wilhelmsen.vgProg.oving14;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Harald Wilhelmsen on 25. Feb 2015.
 */
public class Main {

	public static void main(String[] args) {
		Tribune[] trib = new Tribune[]{
											  new Staa(20, 200),
											  new Staa(50, 150),
											  new Sitte(5, 10, 150),
											  new Vip(5, 5, 500)
		};

		ArrayList<Billett> tickets = new ArrayList<Billett>();

		putThisArrayIntoList(trib[0].kjoepBilletter(4), tickets);
		putThisArrayIntoList(trib[1].kjoepBilletter(46), tickets);
		putThisArrayIntoList(trib[1].kjoepBilletter(10), tickets);
		putThisArrayIntoList(trib[2].kjoepBilletter(new String[]{"Ole", "mari", "herp", "derp"}), tickets);
		putThisArrayIntoList(trib[2].kjoepBilletter(4), tickets);
		putThisArrayIntoList(trib[2].kjoepBilletter(10), tickets);
		putThisArrayIntoList(trib[3].kjoepBilletter(2), tickets);
		putThisArrayIntoList(trib[3].kjoepBilletter(new String[]{"Ole", "mari", "herp", "derp"}), tickets);

		Arrays.sort(trib);

		for(Tribune t : trib) System.out.println(t);
		for(Billett b : tickets) System.out.println(b);

		System.out.println("----------------------------------------");
		//loadAndPrint("tribuner.txt")
		trib = (Tribune[]) load("tribunerFile");
		for(Tribune t : trib) System.out.println(t);
	}

	public static void save(Tribune[] tribuner, String file) {
		try (
					FileOutputStream stream = new FileOutputStream(new File(file));
					ObjectOutputStream objectOutput = new ObjectOutputStream(stream)
		){
			objectOutput.writeObject(tribuner);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	public static Object load(String file) {
		Object o = null;
		try (
					FileInputStream stream = new FileInputStream(new File(file));
					ObjectInputStream objectOutput = new ObjectInputStream(stream)
		){
			o =objectOutput.readObject();
		} catch(IOException e) {
			e.printStackTrace();
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		return o;
	}

	public static void putThisArrayIntoList(Billett[] arr, ArrayList<Billett> list) {
		if(arr == null) return;
		for(Billett b : arr) {
			list.add(b);
		}
	}
}
