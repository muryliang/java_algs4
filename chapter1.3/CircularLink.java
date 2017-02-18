import java.util.Iterator;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import java.util.NoSuchElementException;

public class CircularLink<Item> implements Iterable<Item>{
    private Node<Item> last;
    private int count;

    private class Node<Item> {
        Item item;
        Node<Item> next;
    }

    public CircularLink() {
        last = null;
        count = 0;
    }

    public boolean isEmpty() {
        return last == null;
    }

    public void enqueue(Item item) {
        if (isEmpty()) {
            last = new Node<Item>();
            last.item = item;
            last.next = last;
        } else {
            Node<Item> oldlast = last;
            last = new Node<Item>();
            last.item = item;
            last.next = oldlast.next;
            oldlast.next = last;
        }
        count++;
 //       int tmp = 0;
//        Node<Item> cur = last.next;
  //      for (; tmp < count; tmp++, cur = cur.next ) {
   //         StdOut.print(cur.item + " count:" + tmp + " ");
    //    StdOut.println("end");
     //   }
    }

    public Item dequeue() {
        if (isEmpty()) 
            throw new NoSuchElementException();
        Item res = last.item;
        if (last == last.next)
            last = null;
        else 
            last = last.next;
        return res;
    }

    public Iterator<Item> iterator() {
        return new ListIterator(last);
    }

    private class ListIterator<Item> implements Iterator<Item> {
        private Node<Item> cur ;
        private int flag;
        public ListIterator(Node<Item> last) {
            cur = last.next;
            flag = 0;
        }

        public void remove() {
            throw new NoSuchElementException();
        }
        public boolean hasNext() {
            if ((last.next == cur) &&( flag == 1)) {
                    return false;
            }
            flag = 1;
            return true;
        }

        public Item next() {
           // if (!hasNext()) throw new NoSuchElementException();
            Item item = cur.item;
            cur = cur.next;
            return item;
        }
    }

    public  static void  main(String[] args) {
        CircularLink<String> link = new CircularLink<String>();

        while (!StdIn.isEmpty()) {
            link.enqueue(StdIn.readString());
        }
        for (String s: link)
            StdOut.print(s + " ");
    }
}
