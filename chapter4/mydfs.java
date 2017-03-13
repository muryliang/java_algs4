import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;

public class mydfs {
	private boolean marked[];
	private int ver[];
	private int source;

	public mydfs(dgraph gra, int s) {
		marked = new boolean[gra.V()];
		ver = new int[gra.V()];
		source = s;
		mdfs(gra, s);
	}

	public void mdfs(dgraph gra, int s) {
		marked[s] = true;
		for (int i : gra.adj(s)) {
			if (!marked[i]) {
				ver[i] = s;
				mdfs(gra, i);
			}
		}
	}

	public boolean connected(int v) {
		return marked(v);
	}

	public boolean marked(int v) {
		return marked[v];
	}

	public Iterable<Integer> path(int v) {
		Stack<Integer> stack = new Stack<Integer>();
		if (!marked[v]) return stack;
		while (v != source) {
			stack.push(v);
			v = ver[v];
		}
		stack.push(source);
		return stack;
	}

	public static void main(String[] args) {
		In in = new In(args[0]);
		dgraph gra = new dgraph(in);
		mydfs  dfs = new mydfs(gra, Integer.parseInt(args[1]));
		
		for (int i = 0; i < gra.V(); i++) {
			StdOut.print(i + " :");
			for (int j: dfs.path(i))
				StdOut.print(" " + j);
			StdOut.println();
		}
	}
}
