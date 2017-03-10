import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.In;

public class graph {
	private Bag[] bag;
	int V;
	int E;

	public graph(int n) {
		bag = (Bag[])new Bag[n];
		V = n;
		E = 0;
		for (int i = 0; i < n; i++ )
			bag[i] = new Bag();
	}

	public graph(In in) {
		this(in.readInt());
		int e = in.readInt();
		for (int i = 0; i < e; i++) {
			int p = in.readInt();
			int q = in.readInt();
			addEdge(p, q);
		}
	}

	public int V() {
		return V;
	}

	public int E() {
		return E;
	}

	public void addEdge(int v, int w) {
		bag[v].add(w);
		bag[w].add(v);
		E++;
	}

	public String toString() {
		String s = V + " vertices" + E + " edges\n";
		for (int v = 0; v < V; v++) 
		{
			s += v + ": ";
			for (int w: this.adj(v))
				s += w + " ";
			s += "\n";
		}
		return s;
	}

	public Iterable<Integer> adj(int v) {
		return bag[v];
	}

	public static void main(String[] args) {
		/*
		In in = new In(args[0]);
		int v = in.readInt(); 
		int e = in.readInt();

		graph gh = new graph(v);
		for (int i = 0; i < e; i++) {
			int p = in.readInt();
			int q = in.readInt();
			gh.addEdge(p, q);
		}

		*/
		graph gh = new graph(new In(args[0]));
		StdOut.print(gh);
	}
}


