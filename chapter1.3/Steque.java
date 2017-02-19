import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Steque<Item> implements Iterable<Item> {
    Node<Item> first;
    private int size;

    private class Node<Item> {
        Item item;
        Node<Item> next;
    }

    public Steque () {
        first = null;
        size = 0;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return size;
    }

    public void enqueue(Item item) {
        Node<Item> oldfirst;
        oldfirst = first;
        first = new Node<Item>();
        first.item = item;
        first.next = oldfirst;
        size++;
    }

    public void push(Item item) {
        enqueue(item);
    }

    public Item pop() {
        if (isEmpty()) throw new NoSuchElementException();
        Item res = first.item;
        first = first.next;
        return res;
    }

    public Iterator<Item> iterator() {
        return new ListIterator<Item>(first);
    }

    private class ListIterator<Item> implements Iterator<Item> {
        private Node<Item> cur;

        public ListIterator(Node<Item> old) {
            cur = old;
        }
        public void remove() { throw new NoSuchElementException();}

        public boolean hasNext() {
            return cur != null;
        }
        public Item next() {
            Item res = cur.item;
            cur = cur.next;
            return res;
        }
    }

    public static void main(String[] args) {
        Steque<String> queue = new Steque<String>();

        while(!StdIn.isEmpty()) {
            queue.push(StdIn.readString());
        }

        for(String s: queue)
            StdOut.print(s + " ");
        queue.pop();
        queue.pop();
        queue.pop();
        StdOut.println();
        for(String s: queue)
            StdOut.print(s + " ");
        StdOut.println();
    }
}
