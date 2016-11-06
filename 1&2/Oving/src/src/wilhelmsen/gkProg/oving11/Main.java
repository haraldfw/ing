package src.wilhelmsen.gkProg.oving11;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by haraldfw on 10/29/15.
 */
public class Main {
  public static void main(String[] args) {
    double balance;
    File balanceFile = new File(getAbsolutePath("balance"));
    try {

      balance = Double.parseDouble(new BufferedReader(new FileReader(balanceFile)).readLine());
      double newBalance = balance;
      List<String> lines = Files.readAllLines(Paths.get(getAbsolutePath("transaction")));

      for (String line : lines) {
        if (line.isEmpty()) {
          continue;
        }
        double change;
        try {
          change = Double.parseDouble(line.substring(2));
        } catch (NumberFormatException e) {
          continue;
        }
        newBalance += Character.compare(line.charAt(0), 'U') == 0 ? -change : change;
      }
      if (newBalance > 0) {
        System.out.println("New balance: " + newBalance);
        FileOutputStream stream = new FileOutputStream(balanceFile);
        stream.write(String.valueOf(newBalance).getBytes());
        stream.close();
      } else {
        System.out.println("New balance is negative. Balance file unchanged");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static String getAbsolutePath(String fileName) {
    return "src/src/wilhelmsen/gkProg/oving11/" + fileName;
  }
}
