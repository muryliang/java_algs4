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
    private boolean percolated;
    private int opened;
    /* 
     * 0: blocked
     * 1: open
     * 2: top_combined
     * 4: bottom_combined
     */
    private byte[] status;
    private final WeightedQuickUnionUF qf;

    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException("can not allocate less than 0");
        status  = new byte[n*n];
        line = n;
        opened = 0;
        percolated = false;
        qf = new WeightedQuickUnionUF(n*n);
        for (int i = 0; i < line*line; i++) {
            status[i] = 0; // initialized to not opened
        }
    }

    private void checkIndex(int row, int col) {
        if (row < 1 || row > line || col < 1 || col > line)
            throw new IllegalArgumentException("can not get illegal position" + row + " " + col);
    }

    public void open(int row, int col) {
        checkIndex(row, col);
        if (isOpen(row, col))
            return;
        byte[] cur_status = {0,0,0,0};

        byte tmpvar = 1;
        if (row == 1) tmpvar |= 2;
        if (row == line) tmpvar |= 4;
        status[(row-1)*line+col-1] = tmpvar;

        if (row > 1 && isOpen(row-1, col)) {
            cur_status[0] = status[qf.find((row-2)*line + col-1)];
            qf.union((row-1)*line+col-1, (row-2)*line+col-1);
        }

        if (row < line && isOpen(row+1, col)) {
            cur_status[1] = status[qf.find(row*line + col-1)];
            qf.union((row-1)*line+col-1, row*line+col-1);
        }
        if (col > 1 && isOpen(row, col-1)) {
            cur_status[2] = status[qf.find((row-1)*line + col-2)];
            qf.union((row-1)*line+col-1, (row-1)*line+col-2);
        }

        if (col < line && isOpen(row, col+1)) {
            cur_status[3] = status[qf.find((row-1)*line + col)];
            qf.union((row-1)*line+col-1, (row-1)*line + col);
        }

        status[qf.find((row-1)*line+col-1)] = (byte)(tmpvar | cur_status[0] | cur_status[1] | cur_status[2] | cur_status[3]);
        if (status[qf.find((row-1)*line+col-1)] == 7)
           percolated = true;

        opened += 1;
    }

    public boolean isOpen(int row, int col) {
        checkIndex(row, col);
        return status[(row-1)*line + col-1] != 0;
    }

    public boolean isFull(int row, int col) {
        checkIndex(row, col);
        return (status[qf.find((row-1)*line + col-1)] & 3) == 3;
    }

    public int numberOfOpenSites() {
        return opened;
    }

    public boolean percolates() {
        return percolated;
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

