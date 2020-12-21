/**
 * Created by hug.
 */
public class StopwatchDemo {
    /** Computes the nth Fibonacci number using a slow naive recursive strategy.*/
    private static int fib(int n) {
        if (n < 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return fib(n - 1) + fib(n - 2);
    }

    public static void main(String[] args) {
        Stopwatch sw = new Stopwatch();
        int f1 = fib(35);
        double timeInSeconds01 = sw.elapsedTime();
        int f2 = fib(36);
        double timeInSeconds02 = sw.elapsedTime();



        Stopwatch sw3 = new Stopwatch();
        int f34 = fib(35);
        double timeInSeconds03 = sw3.elapsedTime();


        Stopwatch sw4 = new Stopwatch();
        int f4= fib(36);
        double timeInSeconds04 = sw4.elapsedTime();

        System.out.println("Time taken : " + timeInSeconds01 +"Time taken : " +timeInSeconds02+"Time taken : " +timeInSeconds03+"Time taken : " +timeInSeconds04);
    }
}
