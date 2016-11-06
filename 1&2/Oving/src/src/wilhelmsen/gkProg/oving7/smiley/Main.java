package src.wilhelmsen.gkProg.oving7.smiley;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * Created by Harald on 4.10.15.
 */
public class Main {
  public static void main(String[] args) {
    JFrame frame = new JFrame("Smiley");
    frame.setVisible(true);
    frame.setSize(1000, 800);
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.add(new Smiley());
  }
}
