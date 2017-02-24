import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class quickfind {
    int[] arr;
    int count;
    public quickfind(int N) {
        arr = new int[N];
        for (int i = 0; i < N; i++)
            arr[i] = i;
        count = N;
    }

    public int find(int p) {
        return arr[p];
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q) ;
    }

    public void union(int p, int q) {
        int pid = find(p);
        int qid = find(q);

        if (pid == qid) return;

        for(int i = 0; i < arr.length; i++)
            if (arr[i] == pid)
                arr[i] = qid;
        count--;
    }
    public int cnt() {
        return count;
    }

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int pair = Integer.parseInt(args[1]);
        quickfind ins = new quickfind(N);

        int[] first = new int[pair];
        int[] second = new int[pair];

        for(int i = 0; i < pair; i++) {
            first[i] = StdRandom.uniform(0,N);
            second[i] = StdRandom.uniform(0,N);
        }

        StdOut.println("start match");
        stopwatch prev = new stopwatch();
        for (int i = 0; i < pair; i++) {
            if (ins.connected(first[i], second[i])) ;
        //            StdOut.println(first[i] + " " + second[i] + "connected");
            else {
               ins.union(first[i],second[i]); 
         //       StdOut.println(first[i] + " " + second[i] + " now connected");
            }
        }
        StdOut.println("the count end is " + ins.cnt());
        StdOut.println("and time is " + prev.elapsedTime());
    }
}


