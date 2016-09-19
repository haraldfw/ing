package src.wilhelmsen.ing.alg.oving.four.oppg1;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Harald on 14.9.16.
 */
public class CircularList {

    private int stepSize;
    private List<Integer> elements;

    int iterations = 0;

    private int lastKilled;

    CircularList(int numElems, int start, int stepSize) {
        this.lastKilled = start - 1;
        this.stepSize = stepSize;
        elements = new ArrayList<Integer>();
        for (int i = 0; i < numElems; i++) {
            elements.add(i + 1);
        }
    }

    void update() {
        lastKilled = (lastKilled + stepSize) % elements.size();
        elements.remove(lastKilled);
        iterations++;
    }

    boolean done() {
        if (elements.size() <= 1) {
//            System.out.println("Soldier '" + elements.get(0) + "' survived. Iterations: " + iterations);
            return true;
        }
        return false;
    }
}
