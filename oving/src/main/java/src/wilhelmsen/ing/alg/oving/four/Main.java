package src.wilhelmsen.ing.alg.oving.four;

import src.wilhelmsen.ing.alg.oving.four.oppg1.Josephus;
import src.wilhelmsen.ing.alg.oving.four.oppg2.BracketParser;

/**
 * Created by Harald on 14.9.16.
 */
public class Main {

    public static void main(String[] args) {
        testBracketParser();
    }

    private static void testBracketParser() {
        System.out.println(BracketParser.isValid(
                "asdf{{df\ndf}}g(g(ra3s4\n5d345fd)(fascvsdf\n)[456[6[g\nbfabsdasd]234]asd]1[asdf123]123[//.]asd",
                "([{".toCharArray(),
                ")]}".toCharArray()));
    }

    private static void testJosephus() {
        final int soldiers = 10;
        final int start = 1;
        final int stepSize = 3;

        long sumTime = 0;
        final int runs = 1;
        for (int i = 0; i < runs; i++) {
            long timeStart = System.nanoTime();
            Josephus.run(soldiers, start, stepSize);
            long timeEnd = System.nanoTime();
            sumTime += timeEnd - timeStart;
        }

        System.out.println("Josephus with '" + runs + "' runs: " + ((sumTime / (double) runs) / 1000000.0) + " ms average");
    }
}
