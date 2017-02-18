import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.NoSuchElementException;
import java.lang.Character;

public class Paranthese {
    private static int flag = 1;
    private static int count = 0;
    public static void main (String[] args) {
        JavaStack<Character> parastack = new JavaStack<Character>();
        Character readin, readout;

        while (!StdIn.isEmpty()) {
            readin = new Character(StdIn.readChar());
            if (readin.equals('\n'))
                count++;
//            StdOut.print("in stack: ");
 //           for (char t : parastack)
  //              StdOut.print(t + " ");
   //         StdOut.println();
            if (readin.equals('(') || readin.equals('[') || readin.equals('{'))
                parastack.push(readin);
            else if (readin.equals(')') || readin.equals(']') || readin.equals('}')) {
                if (parastack.isEmpty())
                    throw new NoSuchElementException();
                else {
                    readout = new Character(parastack.pop());
                    if (!((readin.equals(')') && readout.equals('(')) ||
                         (readin.equals(']') && readout.equals('[')) || 
                         (readin.equals('}') && readout.equals('{')))) {
                            StdOut.println("wrong match of " + readin + " " + readout);
                            StdOut.println("times " + count);
                            flag = 0;
                            return;
                         }
                }
            }
        }
        if (flag == 1)
            StdOut.println("now every thing is ok");
    }
}
