import java.io.File;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Queue;

public class fileio {
    private static Queue<String> qpath;
    public static void main(String[] args) {
        show_files_rec(args[0]); // depth priority
        StdOut.println();
        qpath = new Queue<String>();
        show_files_width(args[0]);
    }

    public static void show_files_rec(String path) {
        File root = new File(path);

        if (root.isFile()) {
//                StdOut.println(root.getAbsoluteFile());
                StdOut.println(root.getPath());
                return;
        }
        String[] subpath = root.list();

        for(String subname : subpath) {
            File subroot = new File(path + "/" +  subname);
            show_files_rec(path + "/" + subname);
        }
        StdOut.println(root.getPath());
    }

    
    public static void show_files_width(String path) {
        qpath.enqueue(path);

        while (!qpath.isEmpty() ) {
            String curpath = qpath.dequeue();
            File curfile = new File(curpath);
            if (curfile.isFile())
                StdOut.println(curfile.getPath());
            else {
                String[] subpath = curfile.list();
                for (String subroot : subpath)
                    qpath.enqueue(curpath + "/" + subroot);
                StdOut.println(curfile.getPath());
            }
        }
    }
}

