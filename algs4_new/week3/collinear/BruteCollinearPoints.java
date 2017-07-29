import java.util.Arrays;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {

    private int count;
    private LineSegment[] lineseg;

    public BruteCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException();
        for (int i = 0; i < points.length; i++)
            if (points[i] == null)
                throw new IllegalArgumentException();
        Point[] tmppoints = Arrays.copyOf(points, points.length);
        Arrays.sort(tmppoints);
        for (int i = 1; i < tmppoints.length; i++)
            if (tmppoints[i].compareTo(tmppoints[i-1]) == 0)
                throw new IllegalArgumentException();

        LineSegment[] line = new LineSegment[2*points.length];
        count = 0;

        for (int i = 0; i < points.length; i++)
            for (int j = i + 1; j < points.length; j++)
                for (int m = j + 1; m < points.length; m++)
                    for (int n = m + 1; n < points.length; n++) {
                        if (points[i].slopeTo(points[j]) == points[i].slopeTo(points[m]) 
                                && points[i].slopeTo(points[j]) == points[i].slopeTo(points[n])) {
                            Point[] tmp = {points[i], points[j], points[m], points[n]};
                            Arrays.sort(tmp);
                            line[count++] = new LineSegment(tmp[0], tmp[3]);
                        }
                    }
        lineseg = new LineSegment[count];
        for (int i = 0; i < count; i++)
            lineseg[i] = line[i];
    }

    public int numberOfSegments() {
        return lineseg.length;
    }

    public LineSegment[] segments() {
        return Arrays.copyOf(lineseg, lineseg.length);
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

        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment: collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
