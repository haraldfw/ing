package src.wilhelmsen.oving;

import javax.swing.JOptionPane;

public class Kap2 {

	public static void main(String[] args) {
		oppgave1();
		oppgave2();
		oppgave3();
	}
	
	private static void oppgave3() { // seconds to hours, minutes and seconds
		float inputSeconds;
		while(true) {// This loop will not complete until the user provides a valid value
			try{	 // Reason begin the try/catch will not break the loop unless the parseFloat completes
				inputSeconds = Float.parseFloat(JOptionPane.showInputDialog("How many seconds?"));
				break; // Breaks loop
			} catch(Exception e) {
				System.out.println("Invalid value"); 
			}
		}
		int hours = (int)(inputSeconds/(60*60));
		int minutes = (int) ((inputSeconds/60 - hours*60));
		float seconds = inputSeconds - hours*60*60 - minutes*60;
		JOptionPane.showMessageDialog(null, 
				inputSeconds + " seconds is " 
				+ hours + " hours " 
				+ minutes + " minutes "  
				+ seconds + " seconds "
						);
	}
	
	private static void oppgave2() { // Hours, minutes and seconds to seconds
		float inputHours;
		while(true) {// This loop will not complete until the user provides a valid value
			try{	 // Reason begin the try/catch will not break the loop unless the parseFloat completes
				inputHours = Float.parseFloat(JOptionPane.showInputDialog("How many hours?"));
				break; // Breaks loop
			} catch(Exception e) {
				System.out.println("Invalid value");
			}
		}
		
		float inputMinutes;
		while(true) {// This loop will not complete until the user provides a valid value
			try{	 // Reason begin the try/catch will not break the loop unless the parseFloat completes
				inputMinutes = Float.parseFloat(JOptionPane.showInputDialog("How many minutes?"));
				break; // Breaks loop
			} catch(Exception e) {
				System.out.println("Invalid value");
			}
		}
		
		float inputSeconds;
		while(true) {// This loop will not complete until the user provides a valid value
			try{	 // Reason begin the try/catch will not break the loop unless the parseFloat completes
				inputSeconds = Float.parseFloat(JOptionPane.showInputDialog("How many seconds?"));
				break; // Breaks loop
			} catch(Exception e) {
				System.out.println("Invalid value");
			}
		}
		JOptionPane.showMessageDialog(null, "Total seconds: " + (inputHours*60*60 + inputMinutes*60 + inputSeconds));
	}
	
	private static void oppgave1() { // Inches to centimeters
		// Oppgave 1
		float inputValue;
		while(true) {// This loop will not complete until the user provides a valid value
			try{	 // Reason begin the try/catch will not break the loop unless the parseFloat completes
				inputValue = Float.parseFloat(JOptionPane.showInputDialog("Please enter valid inches-value"));
				break; // Breaks loop
			} catch(Exception e) {
				System.out.println("Invalid value");
			}
		}
		JOptionPane.showMessageDialog(null, inputValue + " inche(s) is " + inputValue*2.54f + " centimeters");
	}
	
	private static void area() {
		float width = 0;
		
		while(true) {// This loop will not complete until the user provides a valid value
			try{	 // Reason begin the try/catch will not break the loop unless the parseFloat completes
				width = Float.parseFloat(JOptionPane.showInputDialog("Enter valid width"));
				break; // Breaks loop
			} catch(Exception e) {
				System.out.println("Invalid width");
			}
		}
		
		float height = 0;
		
		while(true) {
			try{
				height = Float.parseFloat(JOptionPane.showInputDialog("Enter valid height"));
				break;
			} catch(Exception e) {
				System.out.println("Invalid height");
			}
		}
		JOptionPane.showMessageDialog(null, "Area is: " + width*height);
	}

}
