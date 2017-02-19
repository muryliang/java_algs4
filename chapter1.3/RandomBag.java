import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

public class RandomBag<Item> {
    private Item[] itemarray;
    private int count;
    private int max;

    public RandomBag () {
        max = 20;
        itemarray = (Item[])new Object[max];
        count = 0;
    }

    public boolean isEmpty() {
        return count == 0;
    }
    public boolean isFull() {
        return count == max;
    }
    public int size() {
        return count;
    }

    public void add (Item item) {
        if (!isFull()) itemarray[count++] = item;
    }

    public Item[] shuffle() {
        Item[] copy = (Item[])new Object[count];
        for (int i = 0; i < count; i++)
            copy[i] = itemarray[i];

        for (int i = 0; i < count; i++)
            for (int j = i+1; j < count; j++)
                if (StdRandom.bernoulli(0.5)) {
                    Item tmp = copy[j];
                    copy[j] = copy[i];
                    copy[i] = tmp;
                }
        return copy;
    }

    public static void main(String[] args) {
        RandomBag<String> bag = new RandomBag<String>();

        while(!StdIn.isEmpty()){
            bag.add(StdIn.readString());
        }

        Object[] copy = bag.shuffle();
        for(int i = 0; i < copy.length; i++)
            StdOut.print((String)copy[i] + " ");
        StdOut.println();
    }
}

