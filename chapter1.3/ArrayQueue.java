import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import java.util.NoSuchElementException;
import java.util.Iterator;

public class ArrayQueue<Item> implements Iterable<Item>{
    private Item[] itemarray;
    private int first, last;
    private int count;
    private int number;

    public ArrayQueue () {
        first = last = 0;
        count = 5;
        number = 0;
        itemarray = (Item[])new Object[count];
    }

    public void enqueue(Item item) {
        if (isFull()) resize();
        itemarray[first++] = item;
        number++;
        if (first == count)
            first = 0;
    }

    public Item dequeue() {
        Item res;
        if (isEmpty()) throw new NoSuchElementException();
        res = itemarray[last++];
        if (last == count)
            last = 0;
        number--;
//        if (number <= count/4 && (count/2 != 0))
 //           count /= 2;
        return res;
    }

    public boolean isFull() {
        return number == count;
    }
    public boolean isEmpty() {
        return number == 0;
    }

    public void resize() {
        int newcount = count * 2;
        int oldpos;

        Item[] oldarray = itemarray;
        itemarray = (Item[])new Object[newcount];
        oldpos =  last;
        for (int num = 0 ; num < count; num++) {
            itemarray[num] = oldarray[last++];
            if (oldpos == count)
                oldpos = 0;
        } 
        first = count;
        last = 0;
        count = newcount;
    }

    public Iterator<Item> iterator() {
        return new ListIterator<Item>(itemarray, last);
    }

    private class ListIterator<Item> implements Iterator<Item> {
        private int start;
        private int iter;
        private Item[] array;
        public ListIterator(Item[] suparray, int pos) {
            array = suparray;
            start = pos;
            iter = 0;
        }

        public void remove () { throw new NoSuchElementException();}

        public boolean hasNext() {
            return iter < number;
        }
        public Item next() {
            Item item;
            if (!hasNext()) throw new NoSuchElementException();
            item = array[start++];
            if (start == count)
                start = 0;
            iter++;
            return item;
        }
    }

    public static void main(String[] args) {
        ArrayQueue<String> queue = new ArrayQueue<String>();

        while (!StdIn.isEmpty()) {
            queue.enqueue(StdIn.readString());
        }

        for (String i: queue)
            StdOut.print(i + " ");
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        StdOut.println();
        for (String i: queue)
            StdOut.print(i + " ");
        StdOut.println();
    }
}
