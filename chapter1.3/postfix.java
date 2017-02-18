import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;

public class postfix {
    public static void main(String[] args) {
        JavaStack<String> operator = new JavaStack<String>();
        JavaStack<String> data = new JavaStack<String>();
        JavaStack<String> res = new JavaStack<String>();
        String recv, top;
        
        while(!StdIn.isEmpty()) {
            recv = StdIn.readString();
            if (Character.isDigit(recv.charAt(0)))
                data.push(recv);
            else if (recv.equals("("))
                operator.push(recv);
            else if (recv.equals(")")) {
                while (!(top = operator.pop()).equals("(")) 
                    data.push(top);
            } else {
                if (recv.equals("+") || recv.equals("-")) {
                    while ( !operator.isEmpty() && !(top = operator.top()).equals("("))
                        data.push(operator.pop());
                    operator.push(recv);
                }
                else if (recv.equals("*") || recv.equals("/")) {
                    while (!operator.isEmpty() && !operator.top().equals("+") && !operator.top().equals("-") &&
                            operator.equals("("))
                        data.push(operator.pop());
                    operator.push(recv);
                }
            }
        }
        while (!operator.isEmpty())
            data.push(operator.pop());
        for (String s: data)
            res.push(s);
        for (String s: res)
            StdOut.print(s + " ");
        StdOut.println();
    }
}

                

