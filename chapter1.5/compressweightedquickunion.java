import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class compressweightedquickunion {
    int[] arr;
    int[] weight;
    int count;
    public compressweightedquickunion(int N) {
        arr = new int[N];
        weight = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = i;
            weight[i] = 1;
        }
        count = N;
    }

    public int find(int p) {
        while (p != arr[p]) p = arr[p];
        return p;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q) ;
    }

    public void union(int p, int q) {
        int pid = find(p);
        int qid = find(q);
        int tmp;
        if (pid == qid) return;

        if (weight[pid] < weight[qid])  {
            arr[pid] = qid;
            weight[qid] += weight[pid];
            while (arr[p] != qid) {
                tmp = arr[p];
                arr[p] = qid;
                p = tmp;
            }
            while (arr[q] != qid) {
                tmp = arr[q];
                arr[q] = qid;
                q = tmp;
            }
        }
        else { 
            arr[qid] = pid;
            weight[pid] += weight[qid];
            while (arr[p] != pid) {
                tmp = arr[p];
                arr[p] = pid;
                p = tmp;
            }
            while (arr[q] != pid) {
                tmp = arr[q];
                arr[q] = pid;
                q = tmp;
            }
        }
        count--;
    }
    public int cnt() {
        return count;
    }

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int pair = Integer.parseInt(args[1]);
        compressweightedquickunion ins = new compressweightedquickunion(N);

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


