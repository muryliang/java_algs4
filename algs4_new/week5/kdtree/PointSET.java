import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.Point2D;

public class PointSET {
    private SET<Point2D> set;
    public PointSET() {
        set = new SET<Point2D>();
    }

    public boolean isEmpty() {
        return set.isEmpty();
    }

    public int size() {
        return set.size();
    }

    public void insert(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        set.add(p);
    }

    public boolean contains(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        return set.contains(p);
    }

    public void draw() {
        for (Point2D p: set) {
            p.draw();
        }
    }

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new IllegalArgumentException();
        SET<Point2D> ps;
        ps = new SET<Point2D>();
        for (Point2D p: set) {
            if (rect.contains(p))
                ps.add(p);
        }
        return ps;

    }

    public Point2D nearest(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        double min = Double.POSITIVE_INFINITY;
        Point2D minp = null;
        for (Point2D tmpp: set) {
            double tmpmin = tmpp.distanceTo(p);
            if (tmpmin < min) {
                min = tmpmin;
                minp = tmpp;
            }
        }
        return minp;
    }

    public static void main(String[] args) { }
}
