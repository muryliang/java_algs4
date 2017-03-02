import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

public class serial<Item> extends LinkedQueue<Item> implements Comparable {
	private int count;

	public serial() {
		count = 0;
	}

	public void insert(Item item) {
		count++;
		enqueue(item);
	}

	public int compareTo(serial b) {
		if (count < b.count) return -1;
		else if (count > b.count) return 1;
		return 0;
	}

	public void toString() {
		while(!isEmpty())
			StdOut.print(dequeue() + " ");
		StdOut.println();
	}

	public static void main(String[] args) {
		int pros = Integer.parseInt(args[0]);
		if (pros == 0)
			return;
		String[] item = StdIn.readAllStrings();
		int ilen = item.length;
		MinPQ<serial> pq = new MinPQ<serial>[pros];

		for (int i = 0; i < pros; i++)
			pq.insert(new serial());

		StdOut.println("start to put in items");
		for (int i = 0; i < ilen; i++) {
			serial tmp = pq.delMin();
			tmp.insert(item[i]);
			pq.insert(tmp);
		}
		for(serial i: pq) {
			StdOut.println(i);
		}
		return ;
	}
}
			
