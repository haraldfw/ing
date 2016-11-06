package src.wilhelmsen.gkProg.oving9;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

/**
 * Created by Harald on 19.10.15.
 */


class WorkerActionListener implements ActionListener {
  
  Client client;

  public WorkerActionListener(Client client) {
    this.client = client;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if(e.getSource() == client.exitButton) {
      client.exit = true;
    } else if(e.getSource() == client.editFName) {
      String newInput = JOptionPane.showInputDialog(null, "Enter new first name");
      client.worker.getPerson().setFirstName(newInput);
    } else if(e.getSource() == client.editLName) {
      String newInput = JOptionPane.showInputDialog(null, "Enter new last name");
      client.worker.getPerson().setLastName(newInput);

    } else if(e.getSource() == client.editHiredYear) {
      String newInput = JOptionPane.showInputDialog(null, "Enter new hired year");
      client.worker.setHiredYear(Integer.parseInt(newInput));

    } else if(e.getSource() == client.editUid) {
      String newInput = JOptionPane.showInputDialog(null, "Enter new worker ID");
      client.worker.setUid(Integer.parseInt(newInput));

    } else if(e.getSource() == client.editBirthyear) {
      String newInput = JOptionPane.showInputDialog(null, "Enter new birth-year");
      client.worker.getPerson().setBirthYear(Integer.parseInt(newInput));

    } else if(e.getSource() == client.editMonthlyPay) {
      String newInput = JOptionPane.showInputDialog(null, "Enter new montrly pay");
      client.worker.setMonthlyPay(Double.parseDouble(newInput));

    } else if(e.getSource() == client.editTaxPrc) {
      String newInput = JOptionPane.showInputDialog(null, "Enter new tax percentage");
      client.worker.setTaxCut(Double.parseDouble(newInput) / 100);
    }
  }
  /*
  exitButton = new JButton("Exit");
  private static JButton editFName = new JButton("Edit");
  private static JButton editLName = new JButton("Edit");
  private static JButton editBirthyear = new JButton("Edit");
  private static JButton editHiredYear = new JButton("Edit");
  private static JButton editMonthlyPay = new JButton("Edit");
  private static JButton editTaxPrc = new JButton("Edit");
   */
}
