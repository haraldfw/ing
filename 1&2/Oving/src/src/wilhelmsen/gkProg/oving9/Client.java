package src.wilhelmsen.gkProg.oving9;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * Created by Harald on 5.10.15.
 */
public class Client {

  public Worker worker;
  public boolean exit = false;
  public JButton exitButton = new JButton("Exit on OK");
  public JButton editFName = new JButton("Edit");
  public JButton editLName = new JButton("Edit");
  public JButton editBirthyear = new JButton("Edit");
  public JButton editHiredYear = new JButton("Edit");
  public JButton editUid = new JButton("Edit");
  public JButton editMonthlyPay = new JButton("Edit");
  public JButton editTaxPrc = new JButton("Edit");

  public static void main(String[] args) throws Exception {
    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    Client client = new Client(getWorker());
    while (!client.exit) {
      client.showWorkerAndMenu();
    }
  }

  public Client(Worker worker) {
    this.worker = worker;
    WorkerActionListener actionListener = new WorkerActionListener(this);
    exitButton.addActionListener(actionListener);
    editFName.addActionListener(actionListener);
    editLName.addActionListener(actionListener);
    editBirthyear.addActionListener(actionListener);
    editHiredYear.addActionListener(actionListener);
    editUid.addActionListener(actionListener);
    editMonthlyPay.addActionListener(actionListener);
    editTaxPrc.addActionListener(actionListener);
  }

  private void showWorkerAndMenu() {
    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(10, 3));
    panel.add(new JLabel("First name: "));
    panel.add(new JLabel(worker.getPerson().getFirstName()));
    panel.add(editFName);

    panel.add(new JLabel("Last name: "));
    panel.add(new JLabel(worker.getPerson().getLastName()));
    panel.add(editLName);

    panel.add(new JLabel("Age: "));
    panel.add(new JLabel(String.valueOf(worker.getAge())));
    panel.add(editBirthyear);

    panel.add(new JLabel("Worker ID: "));
    panel.add(new JLabel(String.valueOf(worker.getUid())));
    panel.add(editUid);

    panel.add(new JLabel("Gross income per year: "));
    panel.add(new JLabel(String.valueOf(worker.getGrossPerYear())));
    panel.add(editMonthlyPay);

    panel.add(new JLabel("Tax-cut per year: "));
    panel.add(new JLabel(String.valueOf(worker.getYearlyTaxCut())));
    panel.add(new JLabel(""));

    panel.add(new JLabel("Tax cut per month: "));
    panel.add(new JLabel(String.valueOf(worker.getMonthlyTaxCut())));
    panel.add(editTaxPrc);

    panel.add(new JLabel("Was hired in year: "));
    panel.add(new JLabel(String.valueOf(worker.getHiredYear())));
    panel.add(editHiredYear);

    panel.add(new JLabel("Years hired: "));
    panel.add(new JLabel(String.valueOf(worker.getYearsHired())));
    panel.add(new JLabel(""));

    panel.add(new JLabel(""));
    panel.add(exitButton);

    JOptionPane.showMessageDialog(null, panel);
  }

  private static Worker getWorker() {
    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(7, 1));
    JTextField first = new JTextField("first name");
    JTextField last = new JTextField("last name");
    JTextField birth = new JTextField("brith year");
    JTextField id = new JTextField("worker id");
    JTextField hiredYear = new JTextField("hired in year");
    JTextField pay = new JTextField("monthly pay");
    JTextField tax = new JTextField("tax percentage");
    panel.add(first);
    panel.add(last);
    panel.add(birth);
    panel.add(id);
    panel.add(hiredYear);
    panel.add(pay);
    panel.add(tax);

    while (true) {
      try {
        int response = JOptionPane.showOptionDialog(
            null,
            panel,
            "Enter worker information",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            null,
            1
        );
        if(response == JOptionPane.CANCEL_OPTION) {
          System.exit(0);
        }
        Person p = new Person(first.getText(), last.getText(), Integer.parseInt(birth.getText()));
        Worker w = new Worker(
          p,
          Integer.parseInt(id.getText()),
          Integer.parseInt(hiredYear.getText()),
          Double.parseDouble(pay.getText()),
          Double.parseDouble(tax.getText()) / 100);
        return w;
      } catch (Exception e) {
        System.out.println("Invalid input, try again");
      }
    }
  }
}
