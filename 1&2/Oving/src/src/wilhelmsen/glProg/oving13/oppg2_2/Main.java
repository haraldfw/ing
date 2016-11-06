package src.wilhelmsen.glProg.oving13.oppg2_2;

import com.jogamp.opengl.GLCapabilities;

import javax.swing.*;

/**
 * Created by haraldfw on 10/30/15.
 */
public class Main {

  public static void main(String[] args) {
    JFrame window = new JFrame("JOGL Scene");
    GLCapabilities caps = new GLCapabilities(null);
    Oving13 panel = new Oving13(caps);
    window.setContentPane(panel);
    window.pack();
    window.setLocation(50,50);
    window.setResizable(false);
    window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    window.setVisible(true);
    panel.requestFocusInWindow();
  }

}
