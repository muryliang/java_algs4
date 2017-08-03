import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;
import java.util.Comparator;

public class Solver {
    private int moves;
    private Node last;
    private boolean sovable;

    // need to consider the swapped one that also should use this method at the same time, so we can know
    // unsovalbe, one of these two must be solvable
    public Solver(Board initial) {
        if (initial == null) throw new IllegalArgumentException();
        moves = -1;
        last = null;
        sovable = false;
        Node tmp = new Node(null, 0, initial);
        MinPQ<Node> pq = new MinPQ<Node>(new Comparator<Node>() {
            public int compare(Node a, Node b) {
                if (a.getCurr().manhattan() + a.getMove() < b.getCurr().manhattan() + b.getMove())
                    return -1;
                else if (a.getCurr().manhattan() + a.getMove() == b.getCurr().manhattan() + b.getMove())
                    return 0;
                else 
                    return 1;
            }
        });
        pq.insert(tmp);
        Node cur;

        // for the twin
        Node tmp2 = new Node(null, 0, initial.twin());
        MinPQ<Node> pq2 = new MinPQ<Node>(new Comparator<Node>() {
            public int compare(Node a, Node b) {
                if (a.getCurr().manhattan() < b.getCurr().manhattan())
                    return -1;
                else if (a.getCurr().manhattan() == b.getCurr().manhattan())
                    return 0;
                else 
                    return 1;
            }
        });
        pq2.insert(tmp2);
        Node cur2;
        while (true) {
            cur = pq.delMin();
            cur2 = pq2.delMin();
//            System.out.printf("after del pq size %d, pq2 size %d\n", pq.size(), pq2.size());
            if (cur.getCurr().isGoal()) {
                last = cur;
                sovable = true;
                break;
            }
//            System.out.printf("pickout second\n %s\n", cur2.getCurr());
            if (cur2.getCurr().isGoal()) {
                sovable = false;
                last = null;
                break;
            }
            for (Board bd: cur.getCurr().neighbors()) {
                if (cur.getPrev() == null || !bd.equals(cur.getPrev().getCurr())) {
                    Node temp = new Node(cur, cur.getMove()+1, bd);
                    pq.insert(temp);
                }
            }

            for (Board bd2: cur2.getCurr().neighbors()) {
                if (cur2.getPrev() == null || !bd2.equals(cur2.getPrev().getCurr())) {
                    Node temp2 = new Node(cur2, cur2.getMove()+1, bd2);
                    pq2.insert(temp2);
                }
            }
 //           System.out.printf("after insert pq size %d, pq2 size %d\n", pq.size(), pq2.size());
        }
    }

    public boolean isSolvable() {
        return sovable;
    }

    public int moves() {
        return last.getMove();
    }

    public Iterable<Board> solution() {
        return new Iterable<Board>() {
            public Iterator<Board> iterator() {
                return new ListIterator();
            }
        };
    }

    private class ListIterator implements Iterator<Board> {
        private int count;
        private Board[] bd;

        public ListIterator() {
            count = 0;
            Node node = last;
            bd = new Board[node.getMove() + 1];
            for (int i = bd.length - 1; i >= 0; i--) {
                bd[i] = node.getCurr();
                node = node.getPrev();
            }
        }

        public boolean hasNext() {
            return count != bd.length;
        }

        public void remove() { }

        public Board next() {
            return bd[count++];
        }
    }

    public static void main(String[] args) {
    // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}

class Node {
    private Node prev;
    private int moves;
    private Board curr;

    public Node(Node prev, int moves, Board curr) {
        this.prev = prev;
        this.moves = moves;
        this.curr = curr;
    }

    public Node getPrev() {
        return prev;
    }

    public Board getCurr() {
        return curr;
    }

    public int getMove() {
        return moves;
    }
}

