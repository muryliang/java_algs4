import java.util.Iterator;

public class Board {
    private final int[][] blocks;
    private final int len;
    private int hamm, man;

    public Board(int[][] blocks) {
        if (blocks == null) throw new IllegalArgumentException();
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
                    x = (blocks[i][j]-1) / len;
                    if (y - j >= 0) 
                        man += y - j;
                    else 
                        man += j - y;
                    if (x - i >= 0) 
                        man += x - i;
                    else 
                        man += i - x;
                }
        return  man;

    }

    public boolean isGoal() {
        return manhattan() == 0;
    }

    public Board twin() {
        int[][] blk = new int[len][len];
        for (int i = 0; i < len; i++) 
            for (int j = 0; j < len; j++)
                blk[i][j] = blocks[i][j];
        int x1 = 0, y1 = 0;
        int x2 = 0, y2 = 1;
        if (blk[x1][y1] == 0 || blk[x2][y2] == 0) {
            x1++;
            x2++;
        }
        
        swap(blk, x1, y1, x2, y2);
        return new Board(blk);
    }

    public boolean equals(Object y) {
        if (this == y) return true;
        if (y == null) return false;
        if (this.getClass() != y.getClass()) return false;
        Board other = (Board) y;
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
        private int tmp;
        private int ways;
        private int[] count = {0, 0, 0, 0};

        public ListIterator() {
            blk = blocks;
            N = len;
            for (int i = 0; i < N; i++)
                for (int j = 0; j < N; j++)
                    if (blk[i][j] == 0) {
                        x = i;
                        y = j;
                    }
            ways = 4;
            tmp = 0;
            if (x == 0 || x == len -1) ways -= 1;
            if (y == 0 || y == len -1) ways -= 1;
        }

        public boolean hasNext() {
            return tmp != ways;
        }

        public Board next() {
            Board bd = null;
            int[][] tmpblk = new int[N][N];
            for (int i = 0; i < N; i++) 
                for (int j = 0; j < N; j++)
                    tmpblk[i][j] = blk[i][j];
//            System.out.printf("now x, y, %d %d\n", x, y);

            for (int i = 0; i < count.length; i++) {
                if (count[i] == 0) {
                    if (i == 0 && x != 0) {
                        swap(tmpblk, x, y, x-1, y);
                        bd =  new Board(tmpblk);
                        count[i] = 1;
                        tmp++;
                        break;
                    } 
                    else if (i == 1 && x != len-1) {
                        swap(tmpblk, x, y, x+1, y);
                        bd =  new Board(tmpblk);
                        count[i] = 1;
                        tmp++;
                        break;
                    } 
                    else if (i == 2 && y != 0) {
                        swap(tmpblk, x, y, x, y-1);
                        bd =  new Board(tmpblk);
                        count[i] = 1;
                        tmp++;
                        break;
                    } 
                    else if (i == 3 && y != len-1) {
                        swap(tmpblk, x, y, x, y+1);
                        bd =  new Board(tmpblk);
                        count[i] = 1;
                        tmp++;
                        break;
                    }
                    else {
                        count[i] = 1;
                    }
                }
            }
//            System.out.printf("returned %s tmp is %d, ways is %d\n", bd, tmp, ways);
            return bd;
        }

        public void remove() { }
    }

    private void swap(int[][] bk, int x, int y, int m, int n) {
        int tmp = bk[x][y];
        bk[x][y] = bk[m][n];
        bk[m][n] = tmp;
    }

    public String toString() {
        String str1 = String.format("%d\n", len);
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                str1 = str1 + String.format("%2d", blocks[i][j]);
            }
            str1 = str1 + String.format("\n");
        }
        return str1;
    }

    public static void main(String[] args) { }
}
