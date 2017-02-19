import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.NoSuchElementException;

public class RingBuffer<Item> {
    private Item[] buffer;
    private int count;
    private int head;
    private int tail;
    private int max;


    public RingBuffer(int num) {
        buffer = (Item[])new Object[num];
        count = 0;
        head = tail = 0;
        max = num;
    }

    public boolean isEmpty() {
        return count == 0;
    }
    public boolean isFull() {
        return count == max;
    }

    public int add(Item item) {
        if (isFull()) return -1;
        buffer[head++] = item;
        head %= max;
        count++;
        return 0;
    }
    public Item fetch() {
        if (isEmpty()) return null;
        Item item = buffer[tail++];
        tail %= max;
        count--;
        return item;
    }

    public static void main(String[] args) {
        RingBuffer<Integer> buff = new RingBuffer<Integer>(8);
        int[] arr = StdIn.readAllInts();

        for(int i = 0; i < arr.length; i++)
            buff.add(arr[i]);

        int a = buff.fetch();
        int b = buff.fetch();
        int c = buff.fetch();
        StdOut.println(a );
        StdOut.println(b );
        StdOut.println(c);
        for(int i = 0; i < arr.length; i++)
            buff.add(arr[i]);
        a = buff.fetch();
        b = buff.fetch();
        c = buff.fetch();
        StdOut.println(a );
        StdOut.println(b );
        StdOut.println(c);
        a = buff.fetch();
        b = buff.fetch();
        c = buff.fetch();
        StdOut.println(a );
        StdOut.println(b );
        StdOut.println(c);
    }
}

        

        


