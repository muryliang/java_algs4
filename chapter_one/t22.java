//package edu.princeton.cs.algs4;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.In;
import java.util.Arrays;

public class t22 {

    private static int flag = 0;
    private static int high = 0;
    private static int low = 0;
    public static int rank(int num, int[] list, int lo, int hi, int depth) {

        int mid;
        
//        for(int i = 0; i < depth; i++) StdOut.print(' ');
 //       StdOut.printf("%d %d\n", lo, hi);

        if (lo > hi) {
            high = lo;
            low = hi;
            return -1;
        }
        mid = lo + (hi - lo) / 2;
        if (num < list[mid]) return rank(num, list, lo, mid-1, depth+1);
        else if (num > list[mid]) return rank(num, list, mid+1, hi, depth+1);
        else {  
             return mid;
        }

    }

    public static int findless(int num, int[] list) {
        int res = rank(num, list, 0, list.length-1, 0);
        int lowres = 0;

        if (res == -1)
            return low;
        lowres = rank(num -1, list, 0, res-1, 0);
        if (lowres == -1)
            lowres = low;
        while(list[++lowres] != num) ;
        return lowres-1 ;
    }

    public static int findmore(int num, int[] list) {
        int res = rank(num, list, 0, list.length-1, 0);
        int highres = 0;

        if (res == -1)
            return high;
        highres = rank(num -1, list, res+1, list.length-1, 0);
        if (highres == -1)
            highres = high;
        while(list[--highres] != num) ;
        return highres+1 ;
    }
/*
    public static void main(String[] args) {
        In in = new In(args[1]);
        int[] origlist = in.readAllInts();
        int[] list;
        int number, ret, count = 1;

        Arrays.sort(origlist);
        for (int i = 1; i < origlist.length; i++)
            if (origlist[i] != origlist[i-1])
                count++;
        list = new int[count];
        list[0] = origlist[0];
        for (int i = 1, j = 1; i < origlist.length; i++) {
           if (origlist[i] != origlist[i-1]) {
               list[j++] = origlist[i];
           }
        }
        for (int i = 0; i < list.length; i++)
            StdOut.printf("%d ", list[i]);

        if (args[0].charAt(0) == '+')
            flag = 1;
        else
            flag = 2;

        StdOut.println("now flag is " + flag);
        while(!StdIn.isEmpty()) {
            number = StdIn.readInt();
            ret = rank(number, list, 0, list.length-1, 0);
            if ((flag == 1) && (ret == -1))
                StdOut.printf("find result %d\n", number);
            if ((flag == 2) && (ret != -1))
                StdOut.printf("find result %d\n", number);
        }
    }
    */
    public static void main(String[] args) {
        In in = new In(args[0]);
        int[] list = in.readAllInts();
        int number, ret;

        Arrays.sort(list);
        StdOut.println("after sort: ");
        for (int i = 0; i < list.length; i++) 
            StdOut.printf("%d ", list[i]);
        StdOut.println("");
        while (!StdIn.isEmpty()) {
            number = StdIn.readInt();
            ret = findless(number, list);
            StdOut.printf("have %d items less than %d\n", ret+1, number);
            ret = findmore(number, list);
            StdOut.printf("after %d ,items are more than %d\n", ret+1, number);
        }
    }
}

        
