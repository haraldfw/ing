package src.wilhelmsen.vgProg.oving6;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Harald Wilhelmsen on 22. Jan 2015.
 */
public class CurrencyConverter extends JPanel {

	DefaultListModel model;
	JList list1;
	JList list2;
	JLabel result;
	JTextField field;
	JButton newCurrency;

	public CurrencyConverter() {
		model = new DefaultListModel();

		addSampleData(model);

		list1 = new JList(model);
		list2 = new JList(model);

		newCurrency = new JButton("New Currency");
		newCurrency.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addNewCurrency();
			}
		});

		JScrollPane scrollPane1 = new JScrollPane(list1);
		list1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JScrollPane scrollPane2 = new JScrollPane(list2);
		list2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		result = new JLabel("Invalid");
		field = new JTextField();

		GroupLayout gl = new GroupLayout(this);
		gl.setHorizontalGroup(
								 gl.createParallelGroup(GroupLayout.Alignment.LEADING)
										 .addGroup(
														  gl.createSequentialGroup()
																  .addComponent(scrollPane1)
																  .addComponent(scrollPane2)
										 )
										 .addComponent(result)
										 .addComponent(field)
										 .addComponent(newCurrency)
		);
		gl.setVerticalGroup(
							   gl.createSequentialGroup()
									   .addGroup(
														gl.createParallelGroup(GroupLayout.Alignment.BASELINE)
																.addComponent(scrollPane1)
																.addComponent(scrollPane2))
									   .addComponent(result)
									   .addComponent(field)
									   .addComponent(newCurrency)
		);
		this.setLayout(gl);

		field.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				onUiUpdate();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				onUiUpdate();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				onUiUpdate();
			}
		});

		list1.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				onUiUpdate();
			}
		});
		list2.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				onUiUpdate();
			}
		});
	}

	private void onUiUpdate() {
		try {
			Currency c1 = ((Currency) list1.getSelectedValue());
			Currency c2 = ((Currency) list2.getSelectedValue());
			try {
				Double d = Double.parseDouble(field.getText());
				result.setText(d + " " + c1.isoCode + " = " + c1.convertTo(c2) * d + " " + c2.isoCode);
			} catch(Exception e) {
				result.setText("Invalid input. Only enter numbers");
			}
		} catch (Exception e) {
			result.setText("Please select two currencies");
		}
	}

	private void addSampleData(DefaultListModel model) {
		model.addElement(new Currency(1, 1, "NOK"));
		model.addElement(new Currency(1, 6.23, "USD"));
		model.addElement(new Currency(100, 88.96, "SEK"));
		model.addElement(new Currency(100, 117.159923, "DKK"));
		model.addElement(new Currency(100, 5.14, "JPY"));
	}

	private void addNewCurrency() {
		String iso = JOptionPane.showInputDialog("IsoCode for new currency");

		int unit = 1;
		while(true) {
			try {
				unit = Integer.parseInt(JOptionPane.showInputDialog("Unit for new currency(<unit> of this curr is <rate> of NOK)"));
				break;
			} catch (Exception ex) {
				System.out.println("Invalid input, try again");
			}
		}

		double rate = 1;
		while(true) {
			try {
				rate = Double.parseDouble(JOptionPane.showInputDialog("Conversion-rate to NOK for new currency in the unit given."));
				break;
			} catch (Exception ex) {
				System.out.println("Invalid input, try again");
			}
		}
		model.addElement(new Currency(unit, rate, iso));
	}

	private class Currency {
		public final double inNok;
		public final String isoCode;
		public final int unit;

		/**
		 * <unit> amount of <isoCode> = inNok
		 * @param unit	Amount of foreign curr to convert to nok
		 * @param inNok	The amount in nok
		 * @param isoCode	The isocode of the foreign currency
		 */
		public Currency(int unit, double inNok, String isoCode) {
			this.inNok = inNok;
			this.isoCode = isoCode;
			this.unit = unit;
		}

		public double convertTo(Currency curr) {
			return (inNok/unit)*(curr.unit/curr.inNok);
		}

		@Override
		public String toString() {
			return isoCode;
		}
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("List Model Example");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(new CurrencyConverter());
		frame.setSize(500, 400);
		frame.setVisible(true);
	}
}
