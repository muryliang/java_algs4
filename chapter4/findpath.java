import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

public class findpath {
	private boolean[] marked;
	private Queue<Integer> queue;
	private int source ;
	private int[] ver;

	public findpath(graph gh, int s) {
		marked = new boolean[gh.V()];
		queue = new Queue<Integer>();
		ver = new int[gh.V()];
		source = s;
		bfs(gh, s);
	}

	public boolean marked(int s){
		return marked[s];
	}

	public void dfs(graph gh, int s) {
		marked[s] = true;
		for (int w : gh.adj(s))
			if (!marked[w]) {
				ver[w] = s;
				dfs(gh, w);
			}
	}

	public boolean hasPathTo(int v) {
		return marked[v];
	}

	Iterable<Integer> pathTo(int v ){
		if (!this.hasPathTo(v)) return null;
		Stack<Integer> stack = new Stack<Integer>();
		while (v != source) {
			stack.push(v);
			v = ver[v];
		}
		stack.push(source);
		return stack;
	}

	public void bfs(graph gh, int s) {
		queue.enqueue(s);
		marked[s] = true;
		while (!queue.isEmpty()) {
			int v = queue.dequeue();
			marked[v] = true;
			for (int w : gh.adj(v))
				if (!marked(w)) {
					ver[w] = v;
					marked[w] = true;
					queue.enqueue(w);
				}
		}
	}

	public static void main(String[] args) {
		graph gh = new graph(new In(args[0]));
		int s = Integer.parseInt(args[1]);
		findpath dh = new findpath(gh, s);
		for(int i = 0; i < gh.V(); i++) {
			if (dh.hasPathTo(i)) {
				for (int x : dh.pathTo(i)) {
					if (x == s) StdOut.print(x);
					else StdOut.print("-" + x);
				}
				StdOut.println();
			}
		}
	}
}
