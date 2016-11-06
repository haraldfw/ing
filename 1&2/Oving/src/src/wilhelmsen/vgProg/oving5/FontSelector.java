package src.wilhelmsen.vgProg.oving5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Harald on 20.1.15.
 */
public class FontSelector extends JFrame {

    public FontSelector() {
        super();
        setVisible(true);
        setSize(new Dimension(500, 200));

        Container pane = getContentPane();
        GroupLayout gl = new GroupLayout(pane);

        setLayout(gl);

        JLabel label = new JLabel("the quick brown fox jumps over the lazy dog");
        label.setFont(new Font("Serif", Font.PLAIN, 25));

        FontButton serif = new FontButton(label, new Font("Serif", Font.PLAIN, 25));
        FontButton sans = new FontButton(label, new Font("SansSerif", Font.PLAIN, 25));
        FontButton mono = new FontButton(label, new Font("Monospaced", Font.PLAIN, 25));
        FontButton dialog = new FontButton(label, new Font("Dialog", Font.PLAIN, 25));

        gl.setHorizontalGroup(
                                     gl.createParallelGroup(GroupLayout.Alignment.LEADING)
                                             .addComponent(label)
                                             .addGroup(
                                                              gl.createSequentialGroup()
                                                                      .addComponent(serif, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                      .addComponent(sans, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                      .addComponent(mono, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                      .addComponent(dialog, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                             )
        );
        gl.setVerticalGroup(
                                   gl.createSequentialGroup()
                                           .addComponent(label)
                                           .addGroup(
                                                            gl.createParallelGroup()
                                                                    .addComponent(serif)
                                                                    .addComponent(sans)
                                                                    .addComponent(mono)
                                                                    .addComponent(dialog))
        );
    }

    class FontButton extends JButton {
        /**
         * Adds a button to the given label which changes the font of the label on click
         * @param label The label which to affect
         * @param font  The font in which to change to
         */
        public FontButton(final JLabel label, final Font font) {
            super(font.getFontName());
            setVisible(true);
            addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    label.setFont(font);
                }
            });
        }
    }

    public static void main(String[] args) {
        new FontSelector();
    }
}
