import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class StackOfStrings {

    private Node first = null;
    private class Node {
        String item;
        Node next;
    }

//    public StackOfStrings();

    public void push(String item) {
        Node node = new Node();
        node.item = item;
        node.next = first;
        first = node;
    }

    public String pop() {
        String item = first.item;
        first = first.next;
        return item;
    }

    public boolean isEmpty() {
        return first == null;
    }
    
    public static void main(String[] args) {
        StackOfStrings stack = new StackOfStrings();
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (s.equals("-")) StdOut.print(stack.pop());
            else                stack.push(s);
        }
    }
}
