import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.StdOut;

public class KdTree {
    private Node root;
    private int size;
    private int kk;

    public KdTree() {
        root = null;
        size = 0;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int size() {
        return size;
    }

    public void insert(Point2D p) {
        // 0 for horizon, 1 for vertical
        if (p == null) throw new IllegalArgumentException();
        root = insertInto(p, root, false);
        size += 1;
        kk = 0;
//        printall(root);
 //       StdOut.printf("over, kk is %d\n", kk);
    }

    private void printall(Node tmp) {
        if (tmp == null) return;
        printall(tmp.getLeft());
        kk += 1;
        printall(tmp.getRight());
    }

    private Node insertInto(Point2D p, Node cur, boolean direction) {
        if (cur == null) {
            return new Node(p, direction);
        }

        if (cur.isHorizon()) {
            if (p.y() <= cur.y()) { 
                cur.setLeft(insertInto(p, cur.getLeft(), false));
            }
            else {
                cur.setRight(insertInto(p, cur.getRight(), false));
            }
        }
        else {
            if (p.x() <= cur.x()) { 
                cur.setLeft(insertInto(p, cur.getLeft(), true));
            }
            else {
                cur.setRight(insertInto(p, cur.getRight(), true));
            }
        }
        return cur;
    }

    public boolean contains(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        Node search = new Node(p, true);
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
        pdraw(root);
    }

    private void pdraw(Node node) {
        node.draw();
        Node left = node.getLeft();
        Node right = node.getRight();
        if (left != null) pdraw(left);
        if (right != null) pdraw(right);
    }


    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new IllegalArgumentException();
        SET<Point2D> ps;
        ps = new SET<Point2D>();
        search(root, rect, ps);
//        StdOut.println(ps);
        return ps;
    }

    private void search(Node tmp, RectHV rect, SET<Point2D> ps) {
        if (tmp != null) {
            if (rect.contains(tmp.point())) {
                ps.add(tmp.point());
            }

            if (tmp.isHorizon()) {
                if (tmp.y() <= rect.ymin())
                    search(tmp.getRight(), rect, ps);
                else if(tmp.y() >= rect.ymax())
                    search(tmp.getLeft(), rect, ps);
                else {
                    search(tmp.getRight(), rect, ps);
                    search(tmp.getLeft(), rect, ps);
                }
            }
            // vertical
            else {
                if (tmp.x() <= rect.xmin())
                    search(tmp.getRight(), rect, ps);
                else if (tmp.x() >= rect.xmax())
                    search(tmp.getLeft(), rect, ps);
                else {
                    search(tmp.getRight(), rect, ps);
                    search(tmp.getLeft(), rect, ps);
                }
            }
        }
    }

    public Point2D nearest(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        return curNearest(p, root, root.point());
    }

    private Point2D curNearest(Point2D p, Node tmp, Point2D curMinDis) {
        if (tmp == null) return curMinDis;
        Point2D curmin = tmp.point();
        if (curmin.distanceTo(p) > curMinDis.distanceTo(p))
            curmin = curMinDis;
        // now curmin is the current min point
        if (tmp.isHorizon()) {
            if (p.y() <= tmp.y()) {
                curmin = curNearest(p, tmp.getLeft(), curmin);
                if (curmin.distanceTo(p) > tmp.y() - p.y()) {
                    curmin = curNearest(p, tmp.getRight(), curmin);
                }
                return curmin;
            }
            else {
                curmin = curNearest(p, tmp.getRight(), curmin);
                if (curmin.distanceTo(p) > p.y() - tmp.y()) {
                    curmin = curNearest(p, tmp.getLeft(), curmin);
                }
                return curmin;
            }
        }
        else {
            if (p.x() <= tmp.x()) {
                curmin = curNearest(p, tmp.getLeft(), curmin);
                if (curmin.distanceTo(p) > tmp.x() - p.x()) {
                    curmin = curNearest(p, tmp.getRight(), curmin);
                }
                return curmin;
            }
            else {
                curmin = curNearest(p, tmp.getRight(), curmin);
                if (curmin.distanceTo(p) > p.x() - tmp.x()) {
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

    public Point2D point() {
        return p;
    }
}
