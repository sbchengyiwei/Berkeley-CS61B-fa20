import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class that collects timing information about SLList getLast method.
 */
public class TimeSLList {
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
        timeGetLast();
    }

    public static void timeGetLast() {


        List<Double> Q2 = new ArrayList<>();

        SLList a1 = new SLList();
        for(int i = 0; i < 1000; i ++){
            a1.addLast(new Random().nextInt());
        }
        Stopwatch sw1 = new Stopwatch();
        for(int i = 0; i < 10000; i ++){
            a1.getLast();
        }
        Q2.add(sw1.elapsedTime());


        SLList a2 = new SLList();
        for(int i = 0; i < 2000; i ++){
            a2.addLast(new Random().nextInt());
        }
        Stopwatch sw2 = new Stopwatch();
        for (int i = 0; i < 10000; i++){
            a2.getLast();
        }
        Q2.add(sw2.elapsedTime());


        SLList a3 = new SLList();
        for(int i = 0; i < 4000; i ++){
            a3.addLast(new Random().nextInt());
        }
        Stopwatch sw3 = new Stopwatch();
        for (int i = 0; i < 10000; i++){
            a3.getLast();
        }
        Q2.add(sw3.elapsedTime());


        SLList a4 = new SLList();
        for(int i = 0; i < 8000; i ++){
            a4.addLast(new Random().nextInt());
        }
        Stopwatch sw4 = new Stopwatch();
        for(int i = 0; i < 10000; i ++){
            a4.getLast();
        }
        Q2.add(sw4.elapsedTime());


        SLList a5 = new SLList();
        for(int i = 0; i < 16000; i ++){
            a5.addLast(new Random().nextInt());
        }
        Stopwatch sw5 = new Stopwatch();
        for (int i = 0; i < 10000; i++){
            a5.getLast();
        }
        Q2.add(sw5.elapsedTime());


        SLList a6 = new SLList();
        for(int i = 0; i < 32000; i ++){
            a6.addLast(new Random().nextInt());
        }
        Stopwatch sw6 = new Stopwatch();
        for (int i = 0; i < 10000; i++){
            a6.getLast();
        }
        Q2.add(sw6.elapsedTime());

        SLList a7 = new SLList();
        for(int i = 0; i < 64000; i ++){
            a7.addLast(new Random().nextInt());
        }
        Stopwatch sw7 = new Stopwatch();
        for (int i = 0; i < 10000; i++){
            a7.getLast();
        }
        Q2.add(sw7.elapsedTime());


        SLList a8 = new SLList();
        for(int i = 0; i < 128000; i ++){
            a8.addLast(new Random().nextInt());
        }
        Stopwatch sw8 = new Stopwatch();
        for (int i = 0; i < 10000; i++){
            a8.getLast();
        }
        Q2.add(sw8.elapsedTime());



        List<Integer> Q1 = new ArrayList<>();
        int W = 1000;
        for(int i = 0; i < 8; i++){

            Q1.add(W);
            W = W * 2;
        }
        List<Integer> Q3 = new ArrayList<>();
        int K = 10000;
        for(int i = 0; i < 8; i++){

            Q3.add(K);

        }

        printTimingTable( Q1, Q2, Q3);
    }


}
