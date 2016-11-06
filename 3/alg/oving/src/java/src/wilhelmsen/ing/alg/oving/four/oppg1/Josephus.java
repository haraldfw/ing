package src.wilhelmsen.ing.alg.oving.four.oppg1;

/**
 * Created by Harald on 14.9.16.
 */
public class Josephus {

    public static void run(int soldiers, int start, int stepSize) {
        CircularList l = new CircularList(soldiers, start, stepSize);
        while(!l.done()) {
            l.update();
        }
    }
}
