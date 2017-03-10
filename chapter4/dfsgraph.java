import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;

public class dfsgraph {
	boolean[] marked;
	Queue<Integer> queue;
	int count;

	public dfsgraph(graph gh, int s) {
		marked = new boolean[gh.V()];
		queue = new Queue<Integer>();
		bfs(gh, s);
	}

	public boolean marked(int s){
		return marked[s];
	}

	public void dfs(graph gh, int s) {
		marked[s] = true;
		count++;
		for (int w : gh.adj(s))
			if (!marked[w])
				dfs(gh, w);
	}

	public void bfs(graph gh, int s) {
		queue.enqueue(s);
		while (!queue.isEmpty()) {
			int v = queue.dequeue();
			marked[v] = true;
			count++;
			for (int w : gh.adj(v))
				if (!marked(w))
					queue.enqueue(w);
		}
	}

	public int count() {
		return count;
	}

	public static void main(String[] args) {
		graph gh = new graph(new In(args[0]));
		int s = Integer.parseInt(args[1]);
		dfsgraph dh = new dfsgraph(gh, s);
		for (int v = 0; v < gh.V(); v++) {
			if (dh.marked(v))
				StdOut.print(v + " ");
		}
		StdOut.println();
		if (dh.count() != gh.V())
			StdOut.print("Not ");
		StdOut.println("connected");
	}
}
