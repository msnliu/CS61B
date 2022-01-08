package bearmaps;

import java.util.List;

public class KDTree implements PointSet {
    private static final boolean horizontal = true;
    private Node root;

    private class Node {
        private Point point;
        private Node left;
        private Node right;
        // private boolean orientation;

        public Node(Point p) {
            point = p;
            // orientation = o;
        }
    }

    public KDTree(List<Point> points) {
        for (Point p : points) {
            root = add(p, root, horizontal);
            // System.out.println("---");
        }
    }

    private Node add(Point p, Node n, boolean orientation) {
        // System.out.println(orientation);
        if (n == null) {
            return new Node(p);
        }
        if (p.equals(n.point)) {
            return n;
        }
        int cmp = compare(p, n.point, orientation);
        if (cmp < 0) {
            n.left = add(p, n.left, !orientation);
        } else {
            n.right = add(p, n.right, !orientation);
        }
        return n;
    }

    private int compare(Point a, Point b, boolean orientation) {
        if (orientation == horizontal) {
            return Double.compare(a.getX(), b.getX());
        }
        return Double.compare(a.getY(), b.getY());
    }

    @Override
    public Point nearest(double x, double y) {
        Point goal = new Point(x, y);
        return nearestHelper(root, goal, root, horizontal).point;
    }

    public Node nearestHelper(Node n, Point goal, Node best, boolean orientation) {
        // System.out.println(n);
        /**
        if (n != null) {
            System.out.println(n.point);
            System.out.println(orientation);
        } */
        Node goodSide;
        Node badSide;
        if (n == null) {
            return best;
        }
        double currDist = Point.distance(n.point, goal);
        double bestDist = Point.distance(best.point, goal);
        if (Double.compare(currDist, bestDist) < 0) {
            best = n;
        }
        int cmp = compare(goal, n.point, orientation);
        if (cmp < 0) {
            goodSide = n.left;
            badSide = n.right;
        } else {
            goodSide = n.right;
            badSide = n.left;
        }
        // next comparison should use !orientation
        // however orientation at current level is just orientation itself !!!
        best = nearestHelper(goodSide, goal, best, !orientation);
        // recursion 归badside
        // orientation in meaningful does not need to negate !!!
        if (meaningful(n, orientation, goal, bestDist)) {
            best = nearestHelper(badSide, goal, best, !orientation);
            // System.out.println(n.point);
            // System.out.println(1);
        }
        return best;
    }

    public boolean meaningful(Node n, boolean orientation, Point goal, double bestDist) {
        Point pseudoPoint = greenline(n, orientation, goal);
        double currDist = Point.distance(pseudoPoint, goal);
        if (Double.compare(currDist, bestDist) < 0) {
            return true;
        }
        return false;
    }

    public Point greenline(Node n, boolean orientation, Point goal) {
        if (orientation) {
            return new Point(n.point.getX(), goal.getY());
        } else {
            return new Point(goal.getX(), n.point.getY());
        }
    }

    public static void main(String[] args) {
        Point p1 = new Point(2, 3); // constructs a Point with x = 1.1, y = 2.2
        Point p2 = new Point(4, 2);
        Point p3 = new Point(4, 2);
        Point p4 = new Point(4, 5);
        Point p5 = new Point(3, 3);
        Point p6 = new Point(1, 5);
        Point p7 = new Point(4, 4);

        KDTree kd = new KDTree(List.of(p1, p2, p3, p4, p5, p6, p7));
    }
}
