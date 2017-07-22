import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class LinkQueueOfStrings {

    private Node first = null;
    private Node last = null;
    private class Node {
        String item;
        Node next;
    }

//    public StackOfStrings();

    public void enqueue(String item) {
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last;
        else oldlast.next = last;
    }

    public String dequeue() {
        String item = first.item;
        first = first.next;
        if (isEmpty()) last = null;
        return item;
    }

    public boolean isEmpty() {
        return first == null;
    }
    
    public static void main(String[] args) {
        LinkQueueOfStrings stack = new LinkQueueOfStrings();
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (s.equals("-")) StdOut.print(stack.dequeue());
            else                stack.enqueue(s);
        }
    }
}
