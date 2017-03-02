import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.ST;

/*show the most freq word*/

public class freqtest {
	public static void main(String[] args) {
		int len = Integer.parseInt(args[0]);
		STable<String, Integer> st = new STable<String, Integer>();
//		ST<String, Integer> st = new ST<String, Integer>();

		while (!StdIn.isEmpty()) {
			String key = StdIn.readString();
			if (key.length() < len) continue;
			if (st.contains(key))
				st.put(key, st.get(key) + 1);
			else
				st.put(key, 1);
		}

		String max = "";
		st.put(max, 0);
		for (String word : st)
			if (st.get(word) > st.get(max))
				max = word;
		StdOut.println(max + " " + st.get(max));
	}
}

