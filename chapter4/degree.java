import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.BreadthFirstPaths;
import edu.princeton.cs.algs4.SymbolGraph;
import edu.princeton.cs.algs4.Graph;
public class degree {
	public static void main(String[] args) {
		SymbolGraph sg = new SymbolGraph(args[0], args[1]);
		Graph G = sg.G();
		String source = args[2];
		if (!sg.contains(source)) {
			StdOut.println(source + "not in  database");
			return;
		}

		int s = sg.index(source);
		BreadthFirstPaths bfs = new BreadthFirstPaths(G, s);
		while (!StdIn.isEmpty()) {
			String sink = StdIn.readLine();
			if (sg.contains(sink)) {
				int t = sg.index(sink);
				if (bfs.hasPathTo(t))
					for (int v : bfs.pathTo(t))
						StdOut.println(" " + sg.name(v));
				else StdOut.println("can not find " + t);
			}
			else StdOut.println("not  in a database");

		}
	}
}
