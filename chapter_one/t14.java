public class t14 {
    public static void main(String[] args) {
        int num = Integer.parseInt(args[0]);
        int count = 0;

        for(; num != 0; num /= 2)
            count++;
        count-=1;

        System.out.println("result is " + count);
    }
}
