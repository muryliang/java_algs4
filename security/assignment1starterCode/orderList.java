import java.util.ArrayList;

public class orderList {
    public static void main(String[] args) {
        int num = Integer.parseInt(args[0]);
        ArrayList<ArrayList<Integer>> arr = dorec(num);
        System.out.println(arr);
    }

    public static ArrayList<ArrayList<Integer>> dorec(int num) {
        ArrayList<ArrayList<Integer>> arr = new ArrayList<ArrayList<Integer>>();
        if (num != 0) {
            for (ArrayList<Integer> tmp: dorec(num-1)) {
                for (int i = 0; i <= num; i++) {
                    ArrayList<Integer> ntmp = (ArrayList<Integer>) tmp.clone();
                    ntmp.add(i, num);
                    arr.add(ntmp);
                }
            }
        }
        else {
            ArrayList<Integer> tmp = new ArrayList<Integer>();
            tmp.add(num);
            arr.add(tmp);
        }
        return arr;
    }
}
