/**
 * Description: 
 * a program to output rmb representation of number
 * @author muryliang
 * @version 1.0
 */
public class rmb {
	/**
	 * given string, turn into two parts, integer and decimal fraction
	 * @return two parts
	 * @param number number in string format
	 */
	public String[] numToArray (String number) {
		double num = Double.parseDouble(number);
		long zheng = (long)num;
		int xiao = (int)Math.round((num - zheng) * 100);
		return new String[]{zheng + "", String.valueOf(xiao)};
	}

	/**
	 * given a at most four-number string, 
	 * turn it into 中文
	 */
	public String numToStr(String number) {
		String[] arr = {"十", "百", "千"};
		String res = "";
		int len = number.length();
		boolean saved_zero = false, start = false;
		for (int i = 0; i < len; i++) {
			int num = number.charAt(i) - 48;
			if (num == 0) { 
				if (i == len - 1 || !start)
					continue; // just skip trailing zero output or heading zero output
				else
					saved_zero = true; //use this to check if we have enconterred a zero,and output one 0 at the last0
			} else {
				start = true;
				String pad = "";
				if (saved_zero)
					pad = "零"; // use in case  一千零1
				if (i != len - 1) {
					res = res + pad + num + arr[len - 2 - i];
				} else {
					res = res + pad +  num;
				}
			}
		}
		if (res.equals(""))
			res = "0";
		return res;
	}

	/**
	 * split string nubmer into four-number array
	 */
	public String[] splitStr(String strinput) {
		int len = strinput.length();
		int splits = (len + 3) / 4;
		String[] arr = new String[splits];

		for (int i = 0; i < splits; i++) {
			arr[i] = strinput.substring(Integer.max(len - 4*(i+1), 0) , len - 4 * i);
		}
		return arr;
	}

	public static void main(String[] args) {
		rmb rr = new rmb();
		if (args.length != 1) {
			System.out.println("need one param");
			return;
		}
		String[] main_division = {"", "万", "亿"};
		String[] parts = rr.numToArray(args[0]);
		String[] division = rr.splitStr(parts[0]);
		String res = "";
		for (int i = 0; i < division.length; i++) {
				res = rr.numToStr(division[i]) + main_division[i] + res;
		}
		res += "点" + parts[1];
		System.out.println(res);
		return;
	}
}
