package hw2;

import edu.princeton.cs.introcs.StdStats;

import static edu.princeton.cs.introcs.StdRandom.*;

public class PercolationStats {
    int[] numOfOpen;
    int num;
    public PercolationStats(int N, int T, PercolationFactory pf) {
        num = T;
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        numOfOpen = new int[T];
        // perform T independent experiments on an N-by-N grid
        for (int i = 0; i < T; i++) {
            Percolation exp = pf.make(N);
            while (!exp.percolates()) {
                int row = uniform(0, N);
                int col = uniform(0, N);
                if (!exp.isOpen(row, col)) {
                    exp.open(row, col);
                }
            }
            numOfOpen[i] = exp.numberOfOpenSites();
        }
    }
    public double mean() {
        // sample mean of percolation threshold
        return StdStats.mean(numOfOpen);
    }
    public double stddev() {
        // sample standard deviation of percolation threshold
        return StdStats.stddev(numOfOpen);
    }
    public double confidenceLow() {
        // low endpoint of 95% confidence interval
        return mean() - 1.96 * stddev() / Math.sqrt(num);
    }
    public double confidenceHigh() {
        // high endpoint of 95% confidence interval
        return mean() + 1.96 * stddev() / Math.sqrt(num);
    }
}
