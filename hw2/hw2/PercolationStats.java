package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    private double[] results;
    private int length;

    /**
    Perform T independent experiments on an N-by-N grid
     */
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        this.length = T;
        this.results = new double[T];
        int totalSite = N * N;
        for (int i = 0; i < T; i += 1) {
            int count = 0;
            Percolation p = pf.make(N);
            while (!p.percolates()) {
                int row = StdRandom.uniform(N);
                int col = StdRandom.uniform(N);
                if (!p.isOpen(row, col)) {
                    p.open(row, col);
                    count += 1;
                }
            }
            results [i] = (double) count / totalSite;
            // if no double cast, the division will two integers will round down to a integer
        }
    }

    /**
    sample mean of percolation threshold
     */
    public double mean() {
        return StdStats.mean(results);
    }

    /**
     Sample standard deviation of percolation threshold
     */
    public double stddev() {
        return StdStats.stddev(results);
    }

    /**
     * Return the low bound of 95% confidence interval
     */
    public double confidenceLow() {
        return mean() - 1.96 * (stddev() / Math.sqrt(length));
    }

    /**
     * Return the high bound of 95% confidence interval
     */
    public double confidenceHigh() {
        return mean() + 1.96 * (stddev() / Math.sqrt(length));
    }

}

