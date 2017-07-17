import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

public class PercolationStats {
	private double[] rate;
	private int loop;

	public PercolationStats(int n, int trials) {
		rate = new double[trials];
		loop = trials;
		int x1, y1;
		for (int i = 0; i < trials; i++) {
			Percolation per = new Percolation(n);
			while (true) {
				x1 = StdRandom.uniform(n)+1;
				y1 = StdRandom.uniform(n)+1;
				if (!per.isOpen(x1, y1)) {
					per.open(x1,y1);
					if (per.percolates()) {
						rate[i] = (float)per.numberOfOpenSites()/(n*n);
						break;
					}
				}
			}
		}
	}
			
	public double mean() {
		return StdStats.mean(rate);
	}

	public double stddev() {
		return StdStats.stddev(rate);
	}

	public double confidenceLo() {
		return mean() - 1.96*stddev()/Math.sqrt(loop);
	}

	public double confidenceHi() {
		return mean() + 1.96*stddev()/Math.sqrt(loop);
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
