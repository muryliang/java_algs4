import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;

public class Stack<Item> implements Iterable<Item>{

    private Node first = null;
    private class Node {
        Item item;
        Node next;
    }

//    public StackOfStrings();

    public void push(Item item) {
        Node node = new Node();
        node.item = item;
        node.next = first;
        first = node;
    }

    public Item pop() {
        Item item = first.item;
        first = first.next;
        return item;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() { return current != null;}
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
        public void remove() {}
    }
    
    public static void main(String[] args) {
        Stack<String> stack = new Stack<String>();
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (s.equals("-")) StdOut.print(stack.pop());
            else                stack.push(s);
        }
        StdOut.println("remainnig:  ");
        for (String s: stack) 
            StdOut.println(s);
    }
}
