package src.wilhelmsen.vgProg.oving5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Harald on 20.1.15.
 *
 * Oppgave 3 a)
 * 	Et interface kan sammenlignes med en mal. Hvis et object implementerer et interface,
 * 	må det inneholde alle metodene interface betegner, hvis ikke får man kompileringsfeil.
 * 	Dette kan brukes.
 * 	Et interface betegner at en klasse inneholder alle metodene det interface.
 *
 * Oppgave 3 b)
 * 	Man vil ikke få de arvede metodene, fordi den ikke har en superklasse. Den vil ikke
 * 	fungere som en JFrame, of det eneste som vil skje om man lager en ny instans er at
 * 	man vil ha en til instans, det vil ikke komme noe vindu eller lignende.
 */
public class CurrencyConverter extends JFrame {

	static double nokToSek = 1.06446818;

	public CurrencyConverter() {
		super();
		setVisible(true);
		setSize(new Dimension(500, 150));

		Container pane = getContentPane();
		GroupLayout gl = new GroupLayout(pane);

		setLayout(gl);

		JTextField textField = new JTextField("Amount");
		JLabel result = new JLabel("<result>");
		result.setFont(new Font("Serif", Font.PLAIN, 25));

		CurrencyButton toNok = new CurrencyButton(textField, result, 1/nokToSek, "NOK");
		CurrencyButton toSek = new CurrencyButton(textField, result, nokToSek, "SEK");

		gl.setHorizontalGroup(
									 gl.createParallelGroup(GroupLayout.Alignment.LEADING)
											 .addComponent(textField)
											 .addComponent(result)
											 .addGroup(
															  gl.createSequentialGroup()
																	  .addComponent(toNok)
																	  .addComponent(toSek)
											 )
		);
		gl.setVerticalGroup(
								   gl.createSequentialGroup()
										   .addComponent(textField)
										   .addComponent(result)
										   .addGroup(
															gl.createParallelGroup(GroupLayout.Alignment.BASELINE)
																	.addComponent(toNok)
																	.addComponent(toSek))
		);
	}

	private class CurrencyButton extends JButton {
		/**
		 * @param textField	Textfield the user enters his numbers in
		 * @param label	The label in which to print result to
		 * @param conversion	The conversion scale
		 * @param currId	The currency-code(NOK, SEK etc.)
		 */
		public CurrencyButton(final JTextField textField, final JLabel label, final double conversion, final String currId) {
			super("to " + currId);
			setVisible(true);
			addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent evt) {
					double d = 0;
					try {
						d = Double.parseDouble(textField.getText());
					} catch(NumberFormatException e) {
						e.printStackTrace();
						JOptionPane.showMessageDialog(null, "Invalid number, try again");
						textField.setText("");
					}
					d *= conversion;
					label.setText(String.valueOf((double)Math.round(d*100)/100 + " " + currId));
				}
			});
		}
	}

	public static void main(String[] args) {
		new CurrencyConverter();
	}
}
