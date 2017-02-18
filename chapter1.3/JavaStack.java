import java.util.Iterator;
import java.util.NoSuchElementException;

public class JavaStack<Item> implements Iterable<Item> {
    private Node<Item> first;
    private int n;

    private class Node<Item> {
        Item item;
        Node<Item> next;
    }

    public JavaStack() {
        first = null;
        n = 0;
    }

    public void push(Item item) {
        Node<Item> oldfirst = first;
        first = new Node<Item>();
        first.item = item;
        first.next = oldfirst;
        n++;
    }

    public Item pop() {
        if (isEmpty()) throw new NoSuchElementException();
        Item res = first.item;
        first = first.next;
        n--;
        return res;
    }

    public Item top() {
        if (isEmpty()) throw new NoSuchElementException();
        Item res = first.item;
        return res;
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public Iterator<Item> iterator() {
        return new ListIterator<Item>(first);
    }

    private class ListIterator<Item> implements Iterator<Item> {
        private Node<Item> current;

        public ListIterator (Node<Item> first) {
            current = first;
        }

        public boolean hasNext() {
            return current != null;
        }
        public void remove () {
            throw new NoSuchElementException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item res = current.item;
            current = current.next;
            return res;
        }
    }
}
        
