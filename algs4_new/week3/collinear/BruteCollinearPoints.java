import java.util.Arrays;

public class BruteCollinearPoints {

    private int segments;
    private int count;
    private LineSegment[] lineseg;

    public BruteCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException();
        lineseg = new LineSegment[points.length];
        segments = 0;
        count = 0;

        for (int i = 0; i < points.length; i++)
            for (int j = i + 1; j < points.length; j++)
                for (int m = j + 1; m < points.length; m++)
                    for (int n = m + 1; n < points.length; n++) {
                        if (points[i].slopeTo(points[j]) == points[i].slopeTo(points[m]) 
                                && points[i].slopeTo(points[j]) == points[i].slopeTo(points[n])) {
                            Point[] tmp = {points[i], points[j], points[m], points[n]};
                            Arrays.sort(tmp);
                            lineseg[count++] = new LineSegment(tmp[0], tmp[3]);
                        }
                    }
    }

    public int numberOfSegments() {
        return segments;
    }

    public LineSegment[] segments() {
        return lineseg;
    }
}
