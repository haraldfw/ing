package src.wilhelmsen.gkProg.oving7.smiley;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * Created by Harald on 4.10.15.
 */
public class Smiley extends JPanel {

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.setColor(new Color(255, 220, 0)); // face
    g.fillOval(200, 100, 600, 600);
    g.setColor(new Color(255, 255, 255));
    g.fillOval(330, 210, 140, 140); // eyes
    g.fillOval(530, 210, 140, 140);
    g.setColor(new Color(22, 22, 22));
    g.fillOval(415, 225, 40, 40); // pupils
    g.fillOval(615, 225, 40, 40);
    g.setColor(new Color(136, 24, 70));
    g.fillArc(280, 200, 400, 400, 0, -180); // mouth
    // borders
    g.setColor(new Color(22, 22, 22));
    g.drawOval(330, 210, 140, 140); // eyes
    g.drawOval(530, 210, 140, 140);
    g.drawArc(280, 200, 400, 400, 0, -180); // mouth
    g.drawOval(200, 100, 600, 600); // face
  }
}
