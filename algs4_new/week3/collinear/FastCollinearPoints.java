import java.util.Arrays;
import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {

    private LineSegment[] seg;
    private int linecount = 0;

    public FastCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException();
        for (Point check: points)
            if (check == null)
                throw new IllegalArgumentException();

        Pair[] line = new Pair[2*points.length];
        Point[] tmp = new Point[points.length-1];

        int count, start, end;
        for (int i = 0; i < points.length; i++) {
            count = 0;
            for (int j = 0; j < points.length; j++) {
                if (j != i)
                    tmp[count++] = points[j];
            }
            Arrays.sort(tmp, points[i].slopeOrder());
            
            // start to check equal slope
            start = 0;
            end = 1;
            while (end <= tmp.length) {
                if (end == tmp.length || points[i].slopeTo(tmp[end]) != points[i].slopeTo(tmp[start])) {
                    if (end - start >= 3) {
                        Arrays.sort(tmp, start, end);
                        if (points[i].compareTo(tmp[start]) < 0)  {
                            line[linecount++] = new Pair(points[i], tmp[end-1]);
                        }
                        else if (points[i].compareTo(tmp[end-1]) > 0) {
                            line[linecount++] = new Pair(tmp[start], points[i]);
                        }
                        else {
                            line[linecount++] = new Pair(tmp[start], tmp[end-1]);
                        }
                        /*
                        for (int k = 0; k < end-start; k++)
                            StdOut.printf("-%s-", tmp[start + k]);
                        StdOut.printf("-%s-\n", points[i]);
                        */
                    }
                    start = end;
                }
                end++;
            }
        }             
//        StdOut.println("success out, linecount " + linecount);
        Arrays.sort(line, 0, linecount,  Pair.pairCmp());
        if (linecount != 0) {
            int index = 1;
            for (int k = 1; k < linecount; k++)
                if (line[k].compareTo(line[k-1]) != 0)
                    index++;
            seg = new LineSegment[index];
            seg[0] = new LineSegment(line[0].getP(), line[1].getQ());
            index = 1;
            for (int k = 1; k < linecount; k++)
                if (line[k].compareTo(line[k-1]) != 0)
                    seg[index++] = new LineSegment(line[k].getP(), line[k].getQ());
        } else 
            seg = new LineSegment[0];

    }

    public int numberOfSegments() {
        return linecount;
    }

    public LineSegment[] segments() {
        return seg;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p: points) {
            p.draw();
        }

        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment: collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}

class Pair implements Comparable<Pair> {
    
    private Point p;
    private Point q;
    

    public Pair(Point p, Point q) {
        this.p = p;
        this.q = q;
    }

    public Point getP() {
        return p;
    }

    public Point getQ() {
        return q;
    }
    
    public int compareTo(Pair that) {
        int res = p.compareTo(that.p);
        if (res == 0)
            res = q.compareTo(that.q);
        return res;
    }

    public static Comparator<Pair> pairCmp() {
        return new Comparator<Pair>() {
            public int compare(Pair a, Pair b) {
                return a.compareTo(b);
            }
        };
    }
}

