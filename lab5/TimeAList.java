import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class that collects timing information about AList construction.
 */
public class TimeAList {
    private static void printTimingTable(List<Integer> Ns, List<Double> times, List<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }




    public static void main(String[] args) {
        timeAListConstruction();
    }

    public static void timeAListConstruction() {
        // TODO: YOUR CODE HERE

        List<Double> Q2 = new ArrayList<>();

        AList a1 = new AList();
        Stopwatch sw1 = new Stopwatch();
        for (int i = 0; i < 1000; i++){
            a1.addLast(new Random().nextInt());
        }
        Q2.add(sw1.elapsedTime());
        Stopwatch sw2 = new Stopwatch();
        AList a2 = new AList();
        for (int i = 0; i < 2000; i++){
            a2.addLast(new Random().nextInt());
        }
        Q2.add(sw2.elapsedTime());
        Stopwatch sw3 = new Stopwatch();
        AList a3 = new AList();
        for (int i = 0; i < 4000; i++){
            a3.addLast(new Random().nextInt());
        }
        Q2.add(sw3.elapsedTime());
        Stopwatch sw4 = new Stopwatch();
        AList a4 = new AList();
        for (int i = 0; i < 8000; i++){
            a4.addLast(new Random().nextInt());
        }
        Q2.add(sw4.elapsedTime());
        Stopwatch sw5 = new Stopwatch();
        AList a5 = new AList();
        for (int i = 0; i < 16000; i++){
            a5.addLast(new Random().nextInt());
        }
        Q2.add(sw5.elapsedTime());
        Stopwatch sw6 = new Stopwatch();
        AList a6 = new AList();
        for (int i = 0; i < 32000; i++){
            a6.addLast(new Random().nextInt());
        }
        Q2.add(sw6.elapsedTime());
        Stopwatch sw7 = new Stopwatch();
        AList a7 = new AList();
        for (int i = 0; i < 64000; i++){
            a7.addLast(new Random().nextInt());
        }
        Q2.add(sw7.elapsedTime());
        Stopwatch sw8 = new Stopwatch();
        AList a8 = new AList();
        for (int i = 0; i < 128000; i++){
            a8.addLast(new Random().nextInt());
        }
        Q2.add(sw8.elapsedTime());
        List<Integer> Q1 = new ArrayList<>();
        int W = 1000;
        for(int i = 0; i < 8; i++){

            Q1.add(W);
            W = W * 2;
        }
        printTimingTable( Q1, Q2, Q1);
    }


}
