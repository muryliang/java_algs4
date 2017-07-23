import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item>  {

    private Node first, last;
    private int N;

    private class Node {
        Item item;
        Node prev;
        Node next;
    }

    public Deque() {
        first = null;
        last = null;
        N = 0;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.prev = null;
        first.next = oldfirst;
        if (isEmpty()) last = first;
        else oldfirst.prev = first;
        N++;
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        last.prev = oldlast;
        if (isEmpty()) first = last;
        else oldlast.next = last;
        N++;
    }

    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException();
        Item item = first.item;
        first = first.next;
        N--;
        if (isEmpty()) last = null;
        else first.prev = null;
        return item;
    }

    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException();
        Item item = last.item;
        last = last.prev;
        N--;
        if (isEmpty()) first = null;
        else last.next = null;
        return item;
    }

    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }    

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private static<T> void show(String str, Deque<T> de) {
        StdOut.println(str);
        for (T item: de) {
            StdOut.print(item + " ");
        }
        StdOut.println();
    }

    public static void main(String[] args) {
        Deque<String> de = new Deque<String>();
        String res;
        while (!StdIn.isEmpty()) {
            String str = StdIn.readString();
            switch (str.codePointAt(0)) {
            case 'l': de.addFirst(str.substring(1)); show("after add first", de);  break;
            case 'r': de.addLast(str.substring(1)); show("after add last", de); break;
            case 'L':  
                res = de.removeFirst();
                StdOut.println("removed " + res);
                show("after del first", de); 
                break;
            case 'R': 
                res = de.removeLast();
                StdOut.println("removed " + res);
                show("after del last", de); 
                break;
            default: throw new UnsupportedOperationException();
            }
        }
    }
}
