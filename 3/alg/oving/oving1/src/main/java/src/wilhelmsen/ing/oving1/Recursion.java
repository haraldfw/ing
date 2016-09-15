package src.wilhelmsen.ing.oving1;

/**
 * Created by Harald Floor Wilhelmsen on 24.08.2016.
 */
public class Recursion {
    private final int x = 2;

    private double recCount1(int n){
        if (n == 0)
            return 1;
        return x*recCount1(n-1);
    }

    private double javaPow(int n){
        return Math.pow(x,n);
    }

    private double recCount2(int n){
        if (n == 0)
            return 1;
        if (n % 2 == 0)
            return recCount2(n/2)*recCount2(n/2);
        return x*recCount2(n-1);
    }

    private double javaPow2(int n){
        if (n % 2 != 0)
            return x*Math.pow(x*x,(n-1)/2);
        return Math.pow(x*x,n/2);
    }

    public static void main(String[] args) {
        Recursion recursion = new Recursion();
        int n = 20;
        long timeStarted;
        long timeEnded;

        timeStarted = System.nanoTime();
        recursion.javaPow(n);
        timeEnded = System.nanoTime();
        System.out.println("javapow: \t" + (timeEnded-timeStarted) / 1000 + " \tmicroseconds");

        timeStarted = System.nanoTime();
        recursion.recCount1(n);
        timeEnded = System.nanoTime();
        System.out.println("rec1:   \t" + (timeEnded-timeStarted) / 1000 + " \tmicroseconds");

        timeStarted = System.nanoTime();
        recursion.javaPow2(n);
        timeEnded = System.nanoTime();
        System.out.println("javapow2: \t" + (timeEnded-timeStarted) / 1000 + " \tmicroseconds");

        timeStarted = System.nanoTime();
        recursion.recCount2(n);
        timeEnded = System.nanoTime();
        System.out.println("rec2:   \t" + (timeEnded-timeStarted) / 1000 + " \tmicroseconds");
    }
}
