import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Bag;

public class dgraph {
	private Bag<Integer>[] bag;
	private int V;
	private int E;

	public dgraph (In in) {
		V = in.readInt();
		int e = in.readInt();
		bag = (Bag[])new Bag[V];
		for (int i = 0; i < bag.length; i++) 
			bag[i] = new Bag<Integer>();

		for (int i = 0; i < e; i++) {
			int p = in.readInt();
			int q = in.readInt();
			this.addEdge(p, q);
			E++;
		}
	}

	public void addEdge(int p, int q) {
		bag[p].add(q);
	}

	public Iterable<Integer> adj(int v) {
		return bag[v];
	}

	public int V() {
		return V;
	}

	public int E() {
		return E;
	}

	public boolean contains(int v) {
		return v >= 0 && v < E && bag[v] != null;
	}

	public static void main(String[] args) {
		In in = new In(args[0]);

		dgraph gra = new dgraph(in);

		while (!StdIn.isEmpty()) {
			int s = StdIn.readInt();
			if (gra.contains(s)) {
				StdOut.print(s+ " :");
				for (int i: gra.adj(s)) {
					StdOut.print(" " + i);
				}
			}
			StdOut.println();
		}
		return;
	}
}


