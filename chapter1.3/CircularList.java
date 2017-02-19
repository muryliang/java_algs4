import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class CircularList<Item> implements Iterable<Item> {
    private int count;
    private Node<Item> head;
    private Node<Item> last;

    private class Node<Item> {
        Item item;
        Node<Item> next;
    }

    public CircularList() {
        head = null;
        last = null;
        count = 0;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public int size() {
        return count;
    }

    public void addtail (Item item) {
        if (head == null) {
            head = new Node<Item>();
            head.next = head;
            head.item = item;
            last = head;
            count++;
        } else {
            Node<Item> tail = new Node<Item>();
            tail.item = item;
            tail.next = last.next;
            last.next = tail;
            last = tail;
            count++;
        }
    }
}
