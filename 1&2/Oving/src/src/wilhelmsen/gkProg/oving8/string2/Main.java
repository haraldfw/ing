package src.wilhelmsen.gkProg.oving8.string2;

import javax.swing.*;

/**
 * Created by Harald on 5.10.15.
 */
public class Main {

  public static void main(String[] args) {
    String2 s = new String2(
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce fermentum ligula sit "
            + "amet dolor aliquam, quis euismod purus consequat. Aliquam imperdiet nisl in nisl "
            + "sollicitudin, vitae consectetur lectus tincidunt. Mauris a mauris et arcu maximus "
            + "commodo. Donec non commodo nibh, vel placerat nunc. Donec commodo eu magna a "
            + "accumsan. Cras id libero vitae ipsum imperdiet dignissim. Nunc risus ipsum, "
            + "consectetur ut arcu sagittis, mattis bibendum ex. Ut nec tristique mi, vitae "
            + "rutrum metus. Curabitur auctor commodo nisi a tempor. Integer non lacus in neque "
            + "ornare rhoncus. Duis suscipit tristique blandit. In nisl massa, blandit eu "
            + "pulvinar non, varius in dui. Donec felis leo, ornare sit amet malesuada in, "
            + "bibendum non nisl. Pellentesque habitant morbi tristique senectus et netus et "
            + "malesuada fames ac turpis egestas. Fusce orci nulla, sagittis");
    //String2 s = new String2(JOptionPane.showInputDialog("Type your text"));
    System.out.println(s.firstLetterEveryWord());
    System.out.println(s.without("am"));
  }

}
