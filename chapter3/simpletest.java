import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.ST;

/* show every word's last time index*/

public class simpletest {
	public static void main(String[] args) {
//		STable<String, Integer> st = new STable<String, Integer>();
		ST<String, Integer> st = new ST<String, Integer>();

		for (int i = 0; !StdIn.isEmpty(); i++) {
			String key = StdIn.readString();
			st.put(key, i);
		}
		for (String s: st) 
			StdOut.println(s + " " + st.get(s));
	}
}
