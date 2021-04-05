/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {

    private double pstar;
    private double deviation;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0)
            throw new IllegalArgumentException("Arguments can't be <= 0");
        else {
            double[] p;
            p = new double[trials];
            for (int k = 0; k < trials; k++) {
                Percolation a = new Percolation(n);
                while (!a.percolates()) {
                    int i = StdRandom.uniform(1, n + 1);
                    int j = StdRandom.uniform(1, n + 1);
                    a.open(i, j);
                }
                p[k] = (double) a.numberOfOpenSites() / (n * n);
                pstar = pstar + p[k];
            }
            pstar = pstar / trials;
            for (int k = 0; k < trials; k++) {
                deviation = deviation + (p[k] - pstar) * (p[k] - pstar);
            }
            deviation = deviation / (trials - 1);
            deviation = Math.sqrt(deviation);
        }
    }

    public double mean() {
        return pstar;
    }

    public double stddev() {
        return deviation;
    }

    public double confidenceLo() {
        double l;
        l = pstar - deviation;
        return l;
    }

    public double confidenceHi() {
        double h;
        h = pstar + deviation;
        return h;
    }

    public static void main(String[] args) {
        int n = 20;
        int trials = 30;
        PercolationStats a = new PercolationStats(n, trials);
        System.out.printf("mean = %f\n", a.mean());
        System.out.printf("stddev = %f\n", a.stddev());
        System.out.printf("95 confidence interval = [%f, %f]", a.confidenceLo(), a.confidenceHi());
    }
}
