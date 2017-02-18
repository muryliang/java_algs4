import java.util.Iterator;
import java.util.NoSuchElementException;

public class MybagArray<Item> implements Iterable<Item>{
    private Item[] itemarray;
    private int n;
    private int max;

    public MybagArray() {
        max = 5;
        itemarray = (Item[])new Object[max];
        n = 0;
    }

    public int getNum() {
        return n;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public boolean isFull() {
        return n == max;
    }

    public void add(Item item) {
        itemarray[n++] = item;
    }

    public Iterator<Item> iterator() {
        return new ListIterator<Item> (itemarray);
    }

    private class ListIterator<Item> implements Iterator<Item> {
        private Item[] current;
        private int count;

        public ListIterator (Item[] itemarray) {
            current = itemarray;
            count = 0;
        }

        public boolean hasNext() {
            return count != n;
        }
        public void remove() {
            throw new UnsupportedOperationException();
        }
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return current[count++];
        }
    }
}

