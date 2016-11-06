package src.wilhelmsen.gkProg.oving10;

/**
 * Created by haraldfw on 10/15/15.
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Client extends JFrame {

  private StudentOverviewWithArray overview = getNewOverview();
  private JButton registerButton;
  private JButton incButton;
  private JTextArea infoArea;

  public static void main(String[] args) {
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (Exception e) {

    }
    Client client = new Client();
    client.init();
  }

  public Client() {
    super("Student overview");

    registerButton = new JButton("Register student");
    incButton = new JButton("Increment assigment");

    ClientActions actionListener = new ClientActions();
    registerButton.addActionListener(actionListener);
    incButton.addActionListener(actionListener);
    infoArea = new JTextArea();
  }

  public void init() {
    setVisible(true);
    setSize(1000, 1000);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    getContentPane().add(getMainMenu());
    refreshInfo();
  }

  private JPanel getMainMenu() {
    JPanel panel = new JPanel(new BorderLayout());

    JScrollPane scroll = new JScrollPane(infoArea,
        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    panel.add(scroll, BorderLayout.CENTER);
    JPanel btnPanel = new JPanel(new GridLayout(1, 2));
    btnPanel.add(registerButton);
    btnPanel.add(incButton);
    panel.add(btnPanel, BorderLayout.PAGE_END);
    return panel;
  }

  private void showRegisterDialog() {
    JTextField nameField = new JTextField("name");
    JPanel panel = new JPanel(new GridLayout(1, 1));
    panel.add(nameField);
    JOptionPane.showMessageDialog(
        null,
        panel,
        "Input new student name",
        JOptionPane.QUESTION_MESSAGE
    );
    overview.registerStudent(nameField.getText());
  }

  private StudentOverviewWithArray getNewOverview() {
    StudentOverviewWithArray overview = new StudentOverviewWithArray();
    int studentCount = 10;
    for (int i = 0; i < studentCount; i++) {
      String name = String.valueOf(i);
      overview.registerStudent(name, (int) (Math.random() * studentCount));
    }
    return overview;
  }

  private void refreshInfo() {
    infoArea.setText(overview.toString());
  }

  private class ClientActions implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      if (e.getSource() == registerButton) {
        showRegisterDialog();
        refreshInfo();
      } else if (e.getSource() == incButton) {
        String input = JOptionPane.showInputDialog("Enter student name");
        try {
          if (!overview.incrementAssignmentCount(
              input,
              Integer.parseInt(JOptionPane.showInputDialog("Enter amount")))) {
            JOptionPane.showMessageDialog(null, "No student by that name exists");
          }
          refreshInfo();
        } catch (NumberFormatException exc) {
          JOptionPane.showMessageDialog(null, "Please enter a valid number");
        }
      }
    }
  }
}
