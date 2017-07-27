import java.util.Arrays;

public class FastCollinearPoints {

    private LineSegment[] seg;
    private int linecount = 0;

    public FastCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException();
        for (Point check: points)
            if (check == null)
                throw new IllegalArgumentException();

        LineSegment[] line = new LineSegment[points.length];
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
                if (end == tmp.length || tmp[end] != tmp[start]) {
                    if (end - start >= 3) {
                        Arrays.sort(tmp, start, end);
                        if (points[i].compareTo(tmp[start]) < 0) 
                            line[linecount++] = new LineSegment(points[i], tmp[end-1]);
                        else if (points[i].compareTo(tmp[end-1]) > 0)
                            line[linecount++] = new LineSegment(points[start], tmp[i]);
                    }
                    start = end;
                    end++;
                }
            }
        }             

        LineSegment[] seg = new LineSegment[linecount];
        for (int i = 0; i < linecount; i++)
            seg[i] = line[i];
    }


    public int numberOfSegments() {
        return linecount;
    }

    public LineSegment[] segments() {
        return seg;
    }
}
