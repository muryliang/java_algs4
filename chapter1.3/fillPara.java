import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;

public class fillPara {
    public static void main(String[] args) {
        JavaStack<String> stack= new JavaStack<String>();
        String recv, out1, out2, out3;

        while (!StdIn.isEmpty()) {
            recv = StdIn.readString();
            if (!recv.equals(")"))
                stack.push(recv);
            else {
                out3 = stack.pop();
                out2 = stack.pop();
                out1 = stack.pop();
                stack.push("( " + out1 + " " + out2 + " " + out3 + " ) ");
            }
        }
        StdOut.println("result is :" + stack.pop());
    }
}

