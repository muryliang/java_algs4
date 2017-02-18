import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Mybag<Item> implements Iterable<Item>{
    private Node<Item> first ;
    private int n;
    private int max;

    private class Node<Item> {
        Item item;
        Node<Item> next;
    }

        public Mybag(int max) {
            first = null;
            n = 0;
            this.max = max;
        }
        public Mybag() {
            first = null;
            n = 0;
            max = 5;
        }

        public int getNum() {
            return n;
        }

        public boolean isEmpty() {
            return first == null;
        }

        public boolean isFull() {
            return n == max;
        }

        public void add(Item item) {
            Node<Item> oldfirst = first;
            first = new Node<Item>();
            first.item = item;
            first.next = oldfirst;
            n++;
        }

        public Iterator<Item> iterator() {
            return new ListIterator<Item> (first);
        }

        private class ListIterator<Item> implements Iterator<Item> {
            private Node<Item> current;

            public ListIterator (Node<Item> first) {
                current = first;
            }

            public boolean hasNext() {
                return current != null;
            }
            public void remove() {
                throw new UnsupportedOperationException();
            }
            public Item next() {
                if (!hasNext()) throw new NoSuchElementException();
                Item item = current.item;
                current = current.next;
                return item;
            }
        }
}

