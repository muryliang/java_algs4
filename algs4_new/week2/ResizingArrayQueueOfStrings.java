import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class ResizingArrayQueueOfStrings {

    private String[] s;
    private int head, tail;
    private int N = 0;

    public ResizingArrayQueueOfStrings() {
        s = new String[1];
        head = 0;
        tail = 0;
    }

    public void enqueue(String item) {
        if (N == s.length) resize(2*s.length);
        s[tail++] = item;
        if (tail == s.length) tail = 0;
        N++;
    }

    public String dequeue() {
        String item =  s[head];
        s[head++] = null;
        if (head == s.length) head = 0;
        N--;
        if (N > 0 && N == s.length/4) resize(s.length/2);
        return item;
    }

    public void resize(int capacity) {
        String[] copy = new String[capacity];
        if (head < tail) {
            for (int i = head, j = 0; i < tail; i++, j++)
                copy[j] = s[i];
        } else {
            int from = head;
            int to = 0;
            for (; from < s.length; from++, to++) 
                copy[to] = s[from];
            for (from = 0; from < tail; from++, to++)
                copy[to] = s[from];
        }
        s = copy;
        head = 0;
        tail = N;
    }

    public boolean isEmpty() {
        return N == 0;
    }
    
    public static void main(String[] args) {
        ResizingArrayQueueOfStrings stack = new ResizingArrayQueueOfStrings();
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (s.equals("-")) StdOut.print(stack.dequeue());
            else                stack.enqueue(s);
        }
    }
}
