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
        pDraw(root);
    }

    private void pdraw(Node node) {
        node.draw();
        node left = node.getLeft();
        node right = node.getRight();
        if (left != null) pdraw(left);
        if (right != null) pdraw(right);
    }


    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new IllegalArgumentException();
        SET<Point2D> ps;
        ps = new SET<Point2D>();
        search(root, rect, ps);
        return ps;
    }

    private void search(Node tmp, RectHV rect, SET<Point2D> ps) {
        if (tmp != null) {
            if (rect.contains(tmp)) {
                ps.add(tmp);
                search(tmp.getLeft(), rect, ps);
                search(tmp.getRight(), rect, ps);
            }
            else if (tmp.isHorizon()) {
                if (tmp.y() < rect.ymin())
                    search(tmp.getRight(), rect, ps);
                else
                    search(tmp.getLeft(), rect, ps);
            }
            else {
                if (tmp.x() < rect.xmin())
                    search(tmp.getRight(), rect, ps);
                else
                    search(tmp.getLeft(), rect, ps);
            }
        }
    }

    public Point2D nearest(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        double min = curNearest(p, root, Double.POSITIVE_INFINITY);
        return min;
    }

    private double curNearest(Point2D p, Node tmp, double curMinDis) {
        // if zero, just return
        if (curMinDis == 0.0) return curMinDis;
        double curmin =  tmp.distanceTo(p);
        if (curmin > curMinDis)
            curmin = curMinDis;
        if (tmp.isHorizon()) {
            if (p.y() <= tmp.y()) {
                curmin = curNearest(p, tmp.getLeft(), curmin);
                if (curmin > tmp.y() - p.y()) {
                    curmin = curNearest(p, tmp.getRight(), curmin);
                }
                return curmin;
            }
            else {
                curmin = curNearest(p, tmp.getRight(), curmin);
                if (curmin > p.y() - tmp.y()) {
                    curmin = curNearest(p, tmp.getLeft(), curmin);
                }
                return curmin;
            }
        }
        else {
            if (p.x() <= tmp.x()) {
                curmin = curNearest(p, tmp.getLeft(), curmin);
                if (curmin > tmp.x() - p.x()) {
                    curmin = curNearest(p, tmp.getRight(), curmin);
                }
                return curmin;
            }
            else {
                curmin = curNearest(p, tmp.getRight(), curmin);
                if (curmin > p.x() - tmp.x()) {
                    curmin = curNearest(p, tmp.getLeft(), curmin);
                }
                return curmin;
            }
        }
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
        return this.right = node;
    }

    public boolean equals(Node node) {
        return p.equals(node.p);
    }

    public double distanceTo(Point2D that) {
        return p.distanceTo(that);
    }

    public void draw() {
        p.draw();
        if (isHorizon()) {
            new Point2D(0, p.y()).drawTo(new Point2D(1, p.y()));
        }
        else {
            new Point2D(p.x(), 0).drawTo(new Point2D(p.x(), 1));
        }
    }
}
