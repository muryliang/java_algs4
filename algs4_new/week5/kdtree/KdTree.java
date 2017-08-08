import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.Point2D;

public class KdTree {
    private Node root;
    public KdTree() {
        root = null;
    }

    public boolean isEmpty() {
    }

    public int size() {
    }

    public void insert(Point2D p) {
        // 0 for horizon, 1 for vertical
        if (p == null) throw new IllegalArgumentException();
        root = insertInto(p, root, false);
    }

    private Node insertInto(Point2D p, Node cur, boolean direction) {
        if (cur == null)
            return new Node(p, direction);

        if (cur.isHorizon()) {
            if (p.y() <= cur.y()) return insertInto(p, cur.getLeft(), false);
            else return insertInto(p, cur.getRight(), false);
        }
        else {
            if (p.x() <= cur.x()) return insertInto(p, cur.getLeft(), true);
            else return insertInto(p, cur.getRight(), true);
        }
    }

    public boolean contains(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        Node search = new Node(p, 1);
        Node tmp = root;
        while (tmp != null) {
            if (tmp.equals(search)) return true;
            if (tmp.isHorizon()) {
                if (p.x() <= tmp.x()) tmp = tmp.getLeft();
                else tmp = tmp.getRight();
            }
            else {
                if (p.y() <= tmp.y()) tmp = tmp.getLeft();
                else tmp = tmp.getRight();
            }
        }
        return false;
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
        double min = 0;
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

class Node {
    private Point2D p;
    private boolean Horizon;
    private Node left;
    private Node right;

    public Node(Point2D p, boolean Horizon) {
        this.p = p;
        this.Horizon = Horizon;
        this.left = null;
        this.right = null;
    }

    public boolean isHorizon() {
        return Horizon == true;
    }

    public double x() {
        return p.x();
    }

    public double y() {
        return p.y();
    }

    public Node getLeft() {
        return left;
    }

    public Node setLeft(Node node) {
        return this.left = node;
    }

    public Node getRight() {
        return right;
    }

    public Node setRight(Node node) {
        return this.right node;
    }

    public boolean equals(Node node) {
        return p.equals(node.p);
    }
}
