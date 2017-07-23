import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item>  {

    private int N;
    private Item[] set;


    public RandomizedQueue() {
        set = (Item[]) new Object[1];
        N = 0;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (size() == set.length) resize(2*set.length);
//        StdOut.println("length of array is " + set.length + " " + size());
        set[N++] = item;
    }

    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();
        if (!isEmpty() && size() == set.length/4) resize(set.length/2);
        int index = StdRandom.uniform(N);
        Item res = set[index];
        set[index] = set[N-1];
        set[N-1] = null;
        N--;
        return res;
    }

    private void resize(int len) {
        Item[] copy = (Item[]) new Object[len];
        for (int i = 0; i < N; i++)
            copy[i] = set[i];
        set = copy;
    }


    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException();
        return set[StdRandom.uniform(N)];
    }

    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {

        private Item[] current;
        private int count;

        public RandomizedQueueIterator() {
            current = (Item[])new Object[N];
            for (int i = 0; i < N; i++) {
                current[i] = set[i];
            }
            count = 0;
        }

        public boolean hasNext() {
            return count != N;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            int index = StdRandom.uniform(0,N-count)+count; // [count, N)
            if (index != count) {
                Item tmp = current[count];
                current[count] = current[index];
                current[index] = tmp;
            }
            return current[count++];
        }    

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private static<T> void show(String str, RandomizedQueue<T> de) {
        StdOut.println(str);
        for (T item: de) {
            StdOut.print(item + " ");
        }
        StdOut.println();
    }

    public static void main(String[] args) {
        RandomizedQueue<String> de = new RandomizedQueue<String>();
        String res;
        while (!StdIn.isEmpty()) {
            String str = StdIn.readString();
            if (str.equals("-")) {
                res = de.dequeue();
                show("after dequeue " + res + ",  size is " + de.size(), de);
            } else {
                de.enqueue(str);
                show("after enqueue " + str + ",  size is " + de.size(), de);
            }
        }
        StdOut.println("print random sample");
        for (int i = 0; i < de.size(); i++) 
            StdOut.print(de.sample() + " ");
        StdOut.println();
    }
}
