import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

public class PercolationStats {
    private final double[] rate;
    private final int loop;
    private  double dmean = Double.NaN;
    private  double dstddev = Double.NaN;

    public PercolationStats(int n, int trials) {
        if (trials <= 0) throw new IllegalArgumentException("try number error");
		rate = new double[trials];
		loop = trials;
		int x1, y1;
		for (int i = 0; i < trials; i++) {
			Percolation per = new Percolation(n);
			while (true) {
				x1 = StdRandom.uniform(n)+1;
				y1 = StdRandom.uniform(n)+1;
				if (!per.isOpen(x1, y1)) {
					per.open(x1, y1);
					if (per.percolates()) {
						rate[i] = (double) per.numberOfOpenSites()/(n*n);
						break;
					}
				}
			}
		}
	}
			
	public double mean() {
		if (Double.isNaN(dmean))
			dmean = StdStats.mean(rate);
		return dmean; 
	}

	public double stddev() {
		if (Double.isNaN(dstddev))
			dstddev = StdStats.stddev(rate);
		return dstddev;
	}

	public double confidenceLo() {
		return dmean - 1.96*dstddev/Math.sqrt(loop);
	}

	public double confidenceHi() {
		return dmean + 1.96*dstddev/Math.sqrt(loop);
	}

	public static void main(String[] args) {
		int line = Integer.parseInt(args[0]);
		int loop = Integer.parseInt(args[1]);
		Stopwatch timer1 = new Stopwatch();
		PercolationStats per = new PercolationStats(line, loop);
		double time1 = timer1.elapsedTime();
		StdOut.println("time                    = "+ time1);
		StdOut.println("mean                    = "+ per.mean());
		StdOut.println("stddev                  = "+ per.stddev());
		StdOut.println("95% confidence interval = "+ "["+ per.confidenceLo()+"," +  per.confidenceHi() +"]");
	}
}
