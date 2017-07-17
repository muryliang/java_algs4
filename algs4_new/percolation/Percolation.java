import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	private int line, opened;
	private boolean[] status;
	private WeightedQuickUnionUF qf;

	public Percolation(int n) {
		if (n <= 0) throw new IllegalArgumentException("can not allocate less than 0");
		status  = new boolean[n*n+2];
		line = n;
		opened = 0;
		qf = new WeightedQuickUnionUF(n*n+2);
		for (int i = 0; i < line*line+2; i++) {
			status[i] = false; //initialized to not opened
		}
		for (int i =0; i < line; i++) {
			qf.union(i, line*line);
			qf.union((line-1)*line+i, line*line+1);
		}
	}

	public void open(int row, int col) {
		if (row < 1 || row > line || col < 1 || col > line)
			throw new IllegalArgumentException("can not get illegal position" + row + " " + col);
		if (isOpen(row, col))
			return;
		status[(row-1)*line + col-1] = true;

		//connect to surround
		if (row > 1 && isOpen(row-1, col))
			qf.union((row-1)*line+col-1, (row-2)*line+col-1);
		if (row < line && isOpen(row+1, col))
			qf.union((row-1)*line+col-1, (row)*line+col-1);
		if (col > 1 && isOpen(row, col-1))
			qf.union((row-1)*line+col-1, (row-1)*line+col-2);
		if (col < line && isOpen(row, col+1))
			qf.union((row-1)*line+col-1, (row-1)*line+col);
		opened += 1;
	}

	public boolean isOpen(int row, int col) {
		if (row < 1 || row > line || col < 1 || col > line)
			throw new IllegalArgumentException("can not get illegal position" + row + " " + col);
		return status[(row-1)*line + col-1];
	}

	public boolean isFull(int row, int col) {
		if (row < 1 || row > line || col < 1 || col > line)
			throw new IllegalArgumentException("can not get illegal position" + row + " " + col);
		return isOpen(row, col) && qf.connected(line*line, (row-1)*line + col-1);
	}

	public int numberOfOpenSites() {
		return opened;
	}

	public boolean percolates() {
		return qf.connected(line*line, line*line+1);
	}

	public static void main(String[] args) {
		int n = StdIn.readInt();
		int x1, y1;
		Percolation per = new Percolation(n);
		while (true) {
			//get random from [1,n]
			x1 = StdRandom.uniform(n)+1;
			y1 = StdRandom.uniform(n)+1;
			if (!per.isOpen(x1, y1)) {
				per.open(x1, y1);
				StdOut.println("open " + x1 + " " + y1);
				if (per.percolates()) {
					StdOut.println("percolated now " + per.numberOfOpenSites() + "/" + n*n + " " +  (float) per.numberOfOpenSites()/(n*n));
					break;
				}
			}
		}
	}
}

