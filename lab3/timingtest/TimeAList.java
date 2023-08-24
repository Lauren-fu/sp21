package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeAList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
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
        AList<Integer> Ns = new AList<> ();
        AList<Double> times = new AList<> ();
        AList<Integer> opCounts = new AList<> ();
        for(int N = 1000; N <= 1280000; N = N * 2) {
            timeAListConstructionHelper(N, Ns, times, opCounts);
        }
        printTimingTable(Ns, times, opCounts);
    }

    private static void timeAListConstructionHelper(int N, AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        Ns.addLast(N);
        opCounts.addLast(N);
        AList<Integer> lst = new AList<> ();
        Stopwatch sw = new Stopwatch();
        for(int i = 1; i <= N; i += 1) {
            lst.addLast(1);
        }
        double timeInSeconds = sw.elapsedTime();
        times.addLast(timeInSeconds);
    }
}
