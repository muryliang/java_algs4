import java.util.Iterator;

public class Board {
    private final int[][] blocks;
    private final int len;
    private int hamm, man;

    public Board(int[][] blocks) {
        len = blocks.length;
        this.blocks = new int[len][len];
        for (int i = 0; i < len; i++) 
            for (int j = 0; j < len; j++)
                this.blocks[i][j] = blocks[i][j];
        hamm = -1;
        man = -1;
    }

    public int dimension() {
        return len;
    }

    public int hamming() {
        if (hamm != -1)
            return hamm;
        hamm = 0;
        int mod = len*len;
        for (int i = 0; i < len; i++)
            for (int j = 0; j < len; j++)
                if (i*len+j != mod && blocks[i][j] != i*len+j+1)
                    hamm++;
        return hamm;
    }

    public int manhattan() {
        if (man != -1)
            return man;
        man = 0;
        int x, y;
        for (int i = 0; i < len; i++)
            for (int j = 0; j < len; j++) 
                if (blocks[i][j] != 0) {
                    y = (blocks[i][j]-1) % len;
                    x = (blocks[i][j]-1) % len;
                    man += y - j >= 0 ? y - j : j - y;
                    man += x - i >= 0 ? x - i : i - x;
                }
        return  man;

    }

    public boolean isGoal() {
        return hamming() == 0;
    }

    public Board twin() {
        int[][] blk = new int[len][len];
        for (int i = 0; i < len; i++) 
            for (int j = 0; j < len; j++)
                blk[i][j] = blocks[i][j];
        int x1 = 0, y1 = 0;
        int x2 = 0, y2 = 1;
        if (blk[x1][y1] == 0)
            x1++;
        else if (blk[x2][y2] == 0)
            x2++;
        
        int tmp = blk[x1][y1];
        blk[x1][y1] = blk[x2][y2];
        blk[x2][y2] = tmp;
        return new Board(blk);
    }

    public boolean equals(Object y) {
        if (this == y) return true;
        if (y == null) return false;
        if (this.getClass() != y.getClass()) return false;
        Board other = (Board)y;
        for (int i = 0; i < len; i++)
            for (int j = 0; j < len; j++)
                if (blocks[i][j] != other.blocks[i][j])
                    return false;
        return true;
    }

    public Iterable<Board> neighbors() {
        return new Iterable<Board>() {
            public Iterator<Board> iterator() {
                return new ListIterator();
            }
        };
    }

//    private class iteratorclass implements Iterable<Board> {
 //       public Iterable<

    private class ListIterator implements Iterator<Board> {
        private int[][] blk;
        private int N;
        private int x, y;
        private int count;

        public ListIterator() {
            blk = blocks;
            N = len;
            for (int i = 0; i < N; i++)
                for (int j = 0; j < N; j++)
                    if (blk[i][j] == 0) {
                        x = i;
                        y = j;
                    }
            count = 0;
        }

        public boolean hasNext() {
            return count != 4;
        }

        public Board next() {
            Board bd = null;
            int[][] tmpblk = new int[N][N];
            for (int i = 0; i < N; i++) 
                for (int j = 0; j < N; j++)
                    tmpblk[i][j] = blk[i][j];
            switch (count) {
            case 0:
                if (x != 0) {
                    swap(tmpblk, x, y, x-1, y);
                    count ++;
                    bd =  new Board(tmpblk);
                } 
                count ++;
                // break through
            case 1: 
                if (x != len-1) {
                    swap(tmpblk, x, y, x+1, y);
                    count ++;
                    bd =  new Board(tmpblk);
                }
                count ++;
                // break through
            case 2:
                if (y != 0) {
                    swap(tmpblk, x, y, x, y-1);
                    count ++;
                    bd =  new Board(tmpblk);
                }
                count ++;
                //break through
            case 3:
                if (y != len-1) {
                    swap(tmpblk, x, y, x, y+1);
                    count ++;
                    bd =  new Board(tmpblk);
                }
                count ++;
                break;
            default:
                break;
             }
             return bd;
        }

        public void remove() {}
    }

    private void swap(int[][] bk, int x, int y, int m, int n) {
        int tmp = bk[x][y];
        bk[x][y] = bk[m][n];
        bk[m][n] = tmp;
    }

    public String toString() {
        String str1 = String.format("%d\n", len);
        for (int i = 0; i < len; i++)
            for (int j = 0; j < len; j++)
                str1 = str1 + String.format("%2d", blocks[i][j]);
            str1 = str1 + String.format("\n");
        return str1;
    }

    public static void main(String[] args) {}
}
