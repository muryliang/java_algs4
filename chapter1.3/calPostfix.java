import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.NoSuchElementException;

public class calPostfix {
    public static void main(String[] args) {
        JavaStack<String> data = new JavaStack<String>();
        String recv;
        int out1, out2, res;

        while (!StdIn.isEmpty()) {
            recv = StdIn.readString();
            if (Character.isDigit(recv.charAt(0)))
                data.push(recv);
            else {
                out2 = Integer.parseInt(data.pop());
                out1 = Integer.parseInt(data.pop());
                switch (recv.charAt(0)) {
                case '+': data.push(Integer.toString(out1 + out2));break;
                case '-': data.push(Integer.toString(out1 - out2));break;
                case '*': data.push(Integer.toString(out1 * out2));break;
                case '/': data.push(Integer.toString(out1 / out2));break;
                default : throw new NoSuchElementException();
                }
            }
        }
        StdOut.println(data.pop());
    }
}
