import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import java.util.NoSuchElementException;
import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    private Node<Item> first;
    private Node<Item> last;
    private int count;

    private class Node<Item> {
        Item item;
        Node<Item> prev;
        Node<Item> next;
    }

    public Deque() {
        first = null;
        last = null;
        count = 0;
    }

    public boolean isEmpty() {
        return first == null ;
    }

    public  int size() {
        return count;
    }

    public void pushLeft(Item item) {
        Node<Item> oldfirst = first;
        if (isEmpty())
            last = first = new Node<Item>();
        else 
            first = new Node<Item>();
        first.item = item;
        first.prev = null;
        first.next = oldfirst;
        if (oldfirst != null)
            oldfirst.prev = first;
        count ++;
    }

    public  Item popLeft() {
        if (isEmpty()) throw new NoSuchElementException();
        Item res;
        res = first.item;
        count--;
        if (count == 0)
            last = first = null;
        else {
            first  = first.next;
            first.prev = null;
        }
        return res;
    }

    public void pushRight(Item item) {
        Node<Item> oldlast = last;
        if (isEmpty()) 
            last = first = new Node<Item>();
        else
            last = new Node<Item>();
        last.item = item;
        last.prev = oldlast;
        last.next = null;
        if (oldlast != null)
            oldlast.next = last;
        count ++;
    }

    public Item popRight() {
        Item res;
        if (isEmpty()) throw new NoSuchElementException();
        count--;
        res = last.item;
        if (count == 0)
            first = last = null;
        else {
            last = last.prev;
            last.next = null;
        }
        return res;
    }

    public Iterator<Item> iterator() {
        return new ListIterator<Item>(first, count);
    }

    private class ListIterator<Item> implements Iterator<Item> {
        private Node<Item> cur;
        private int size;

        public ListIterator(Node<Item> first, int count) {
            cur = first;
            size = count;
        }

        public void remove() {throw new NoSuchElementException();}

        public boolean hasNext() {
            return size != 0;
        }
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item res = cur.item;
            cur = cur.next;
            size--;
            return res;
        }
    }

    public static void main(String[] args) {
        Deque<String> queue = new Deque<String> ();
        String[] strarray= StdIn.readAllStrings();
        int count = strarray.length;

        for (int i = 0; i < count; i++)
            queue.pushLeft(strarray[i]);
        for(String s : queue)
            StdOut.print(s + " ");
        StdOut.println();
        queue.popLeft();
        queue.popLeft();
        queue.popLeft();
        queue.popLeft();
        for(String s : queue)
            StdOut.print(s + " ");
        StdOut.println();

        for (int i = 0; i < count; i++)
            queue.pushRight(strarray[i]);
        for(String s : queue)
            StdOut.print(s + " ");
        StdOut.println();
        queue.popRight();
        queue.popRight();
        queue.popRight();
        queue.popRight();
        for(String s : queue)
            StdOut.print(s + " ");
        StdOut.println();
        queue.popLeft();
        queue.popLeft();
        queue.popLeft();
        queue.popLeft();
        queue.popLeft();
        queue.popLeft();
        for(String s : queue)
            StdOut.print(s + " ");
        StdOut.println();
    }
}
