import java.util.*;

public class tryInput {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		System.out.println("input a number: ");
		double res = scan.nextDouble();
		System.out.printf("the number you input is %f\n", res);
	}
}
