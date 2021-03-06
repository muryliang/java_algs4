import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;

public class STable<Key extends Comparable<Key>, Item> implements Iterable<Key>{
	private Node first;
	private int count;

	public STable() {
		count = 0;
		first = null;
	}

	private class Node {
		Key key;
		Item item;
		Node next;

		public Node(Key mkey, Item mitem, Node next) {
			key = mkey;
			item = mitem;
			this.next = next;
		}
	}

	public Item get(Key key) {
		for (Node x  = first; x != null; x = x.next)
			if (key.equals(x.key))
				return x.item;
		return null;
	}

	public boolean contains(Key key) {
		return get(key) != null;
	}
			
	public void put(Key key, Item item) {
		if (item == null) delete(key);
		for (Node x = first; x != null; x = x.next) {
			if (key.equals(x.key)) {
				x.item = item;
				return;
			}
		}
		first = new Node(key, item, first);
		count++;
	}

	public int size() {
		return count;
	}

	public boolean isEmpty() {
		return count == 0;
	}

	public void delete(Key key) {
		if (key.equals(first.key)) {
			first = first.next;
			count--;
			return;
		}
		Node prev = first;
		for(Node x = first.next ; x != null; x = x.next) {
			if (key.equals(x.key)) {
				prev.next = x.next;
				count--;
				return;
			}
			prev = x;
		}
	}

	public Key min() {
		if (size() == 0) return null;
		Node min = first;
		for (Node x = first.next; x != null; x = x.next) {
			if (min.key.compareTo(x.key) > 0)
				min = x;
		}
		return min.key;
	}

	public boolean less(Key key, Key key2) {
		return key.compareTo(key2) < 0;
	}

	public Key max() {
		if (size() == 0) return null;
		Node max = first;
		for (Node x = first.next; x != null; x = x.next) {
			if (max.key.compareTo(x.key) < 0)
				max = x;
		}
		return max.key;
	}

	public 	Key floor(Key key) {
		if (!contains(key)) return null;
		Key min = null;
		for (Node x = first; x != null;x = x.next) {
			if (less(x.key, key)) {
				if (min == null)
					min = x.key;
			} else {
				if (less(min, x.key))
					min = x.key;
			}
		}
		return min;
	}

	public 	Key ceil(Key key) {
		if (!contains(key)) return null;
		Key max = null;
		for (Node x = first; x != null;x = x.next) {
			if (less(key, x.key)) {
				if (max == null)
					max = x.key;
			} else {
				if (less(x.key, max))
					max = x.key;
			}
		}
		return max;
	}

	public int rank(Key key) {
		int count = 0;
		for (Node x = first; x != null; x= x.next) {
			if (less(x.key, key))
				count++;
		}
		return count;
	}

	public Iterator<Key> iterator() {
		return new ListIterator<Key>(first);
	}

	private class ListIterator<Key> implements Iterator<Key> {
		private Node nd;
		public ListIterator(Node first) {
			nd = first;
		}

		public void remove() {return;}
		public boolean hasNext() {
			return nd != null;
		}
		public Key next() {
			Key tmp = (Key)nd.key;
			nd = nd.next;
			return tmp;
		}
	}
//	public Key select(int k) {
}

