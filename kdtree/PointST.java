import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.Stack;

public class PointST<Value> {
    // private RB-BST
    private RedBlackBST<Point2D, Value> st;

    // construct an empty symbol table of points
    public PointST() {
        st = new RedBlackBST<>();
    }

    // is the symbol table empty?
    public boolean isEmpty() {
        return st.isEmpty();
    }

    // number of points
    public int size() {
        return st.size();
    }

    // associate the value val with point p
    public void put(Point2D p, Value val) {
        if (p == null) {
            throw new IllegalArgumentException("Point is null");
        }
        if (val == null) {
            throw new IllegalArgumentException("Value is null");
        }
        st.put(p, val);
    }

    // value associated with point p
    public Value get(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException("Point is null");
        }
        return st.get(p);
    }

    // does the symbol table contain point p?
    public boolean contains(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException("Point is null");
        }
        return st.contains(p);
    }

    // all points in the symbol table
    public Iterable<Point2D> points() {
        return st.keys();
    }

    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) {
            throw new IllegalArgumentException("rect is null");
        }
        // creating a stack to put all the points that are inside the rect.
        Stack<Point2D> pointsInside = new Stack<>();
        for (Point2D points : points()) {
            if (rect.contains(points)) {
                pointsInside.push(points);
            }
        }
        return pointsInside;
    }

    // a nearest neighbor of point p; null if the symbol table is empty
    public Point2D nearest(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException("Point is null");
        }
        // keeping track of the champion and the distance
        Point2D champion = null;
        double nearestDistance = Double.POSITIVE_INFINITY;

        for (Point2D points : points()) {
            double currentDistance = points.distanceSquaredTo(p);
            if (currentDistance < nearestDistance) {
                champion = points;
                nearestDistance = currentDistance;
            }
        }
        return champion;
    }

    // unit testing (required)
    public static void main(String[] args) {
        PointST<String> testing = new PointST<>();
        Point2D p1 = new Point2D(2.0, 3.0);
        testing.put(p1, "Point 1");
        System.out.println("The size is: " + testing.size());

        Point2D p2 = new Point2D(2.0, 4.0);
        testing.put(p2, "Point 2");
        System.out.println("The size is: " + testing.size());

        Point2D p3 = new Point2D(3.0, 4.0);
        testing.put(p3, "Point 3");
        System.out.println("The size is: " + testing.size());

        Point2D p4 = new Point2D(2.0, 3.5);
        testing.put(p4, "Point 4");
        System.out.println("The size is: " + testing.size());

        Point2D p5 = new Point2D(1.0, 3.0);
        testing.put(p5, "Point 5");
        System.out.println("The size is: " + testing.size());

        Point2D p6 = new Point2D(1.9, 3.0);
        testing.put(p6, "Point 6");
        System.out.println("The size is: " + testing.size());


        System.out.println("Level Order Traversal: ");
        for (Point2D point : testing.points()) {
            System.out.println(point);
        }
        System.out.println("Is empty?: " + testing.isEmpty());
        System.out.println("getting Point 1: " + testing.get(p1));
        System.out.println("getting Point 2: " + testing.get(p2));
        System.out.println("getting Point 3: " + testing.get(p3));
        System.out.println("getting Point 4: " + testing.get(p4));

        System.out.println("Contains Point 4? : " + testing.contains(p4));

        Point2D p7 = new Point2D(0.12, 0.23);
        System.out.println("Contains Point 8? : " + testing.contains(p7));

        RectHV query = new RectHV(0.0, 0.0, 2.0, 2.0);

        for (Point2D point : testing.range(query)) {
            System.out.println("Points in query rect: " + point);
        }

        Point2D near = testing.nearest(p7);
        System.out.println("Point Nearest (" + p7.x() + ", " + p7.y() + "): "
                                   + near);
    }
}

