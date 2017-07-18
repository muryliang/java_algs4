/******************************************************************************
 *  Compilation: 
 *  [optional lines]
 *  Execution: 
 *  [optional lines]
 ******************************************************************************/
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final int line;
    private int opened;
    private byte[] status;
    private final WeightedQuickUnionUF qf;
    private final WeightedQuickUnionUF ff;

    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException("can not allocate less than 0");
        status  = new byte[n*n+2];
        line = n;
        opened = 0;
        qf = new WeightedQuickUnionUF(n*n+2);
        ff = new WeightedQuickUnionUF(n*n+2);
        for (int i = 0; i < line*line+2; i++) {
            status[i] = 0; // initialized to not opened
        }
        status[line*line] = 1;
        status[line*line+1] = 1;
    }

    private void checkIndex(int row, int col) {
        if (row < 1 || row > line || col < 1 || col > line)
            throw new IllegalArgumentException("can not get illegal position" + row + " " + col);
    }

    public void open(int row, int col) {
        checkIndex(row, col);
        if (isOpen(row, col))
            return;

        status[(row-1)*line + col-1] = 1;
        if (row == 1) {
            qf.union((row-1)*line+col-1, line*line);
            ff.union((row-1)*line+col-1, line*line);
        }

        if (row > 1 && isOpen(row-1, col)) {
            qf.union((row-1)*line+col-1, (row-2)*line+col-1);
            ff.union((row-1)*line+col-1, (row-2)*line+col-1);
        }

        if (row < line && isOpen(row+1, col)) {
            qf.union((row-1)*line+col-1, row*line+col-1);
            ff.union((row-1)*line+col-1, row*line+col-1);
        }
        if (col > 1 && isOpen(row, col-1)) {
            qf.union((row-1)*line+col-1, (row-1)*line+col-2);
            ff.union((row-1)*line+col-1, (row-1)*line+col-2);
        }

        if (col < line && isOpen(row, col+1)) {
            qf.union((row-1)*line+col-1, (row-1)*line+col);
            ff.union((row-1)*line+col-1, (row-1)*line+col);
        }

        // bottom line's unit is full, so connect to the last virtual
        if (row == line)
            qf.union((row-1)*line+col-1, line*line+1);
        opened += 1;
    }

    public boolean isOpen(int row, int col) {
        checkIndex(row, col);
        return status[(row-1)*line + col-1] == 1;
    }

    public boolean isFull(int row, int col) {
        checkIndex(row, col);
        return isOpen(row, col) && ff.connected(line*line, (row-1)*line + col-1);
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
            // get random from [1,n]
            x1 = StdRandom.uniform(n)+1;
            y1 = StdRandom.uniform(n)+1;
            if (!per.isOpen(x1, y1)) {
                per.open(x1, y1);
                StdOut.println("open " + x1 + " " + y1);
                if (per.percolates()) {
                    StdOut.println("percolated now " + per.numberOfOpenSites() + "/" + n*n + " " +  (double) per.numberOfOpenSites()/(n*n));
                    break;
                }
            }
        }
    }
}

