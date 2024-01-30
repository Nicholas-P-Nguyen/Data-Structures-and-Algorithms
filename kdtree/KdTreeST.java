import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.Stack;

public class KdTreeST<Value> {

    // Root of 2D tree
    private Node root;
    // Size of 2D tree
    private int size;

    private class Node {
        private Point2D p;     // the point
        private Value val;     // the symbol table maps the point to this value
        private RectHV rect;   // the axis-aligned rectangle corresponding to this node
        private Node leftBottom;       // the left/bottom subtree
        private Node rightTop;       // the right/top subtree

        // Constructor for Node
        public Node(Point2D p, Value val, RectHV rect) {
            this.p = p;
            this.val = val;
            this.rect = rect;
        }
    }


    // construct an empty symbol table of points
    public KdTreeST() {
        size = 0;
        root = null;
    }

    // is the symbol table empty?
    public boolean isEmpty() {
        return (size == 0);
    }

    // number of points
    public int size() {
        return size;
    }

    // associate the value val with point p
    public void put(Point2D p, Value val) {
        if (p == null) throw new IllegalArgumentException("p is null");
        if (val == null) throw new IllegalArgumentException("val is null");

        // Construct initial rectangle
        double negInf = Double.NEGATIVE_INFINITY;
        double posInf = Double.POSITIVE_INFINITY;
        RectHV initRect = new RectHV(negInf, negInf, posInf, posInf);

        root = put(root, p, val, initRect, true);
    }

    // private helper method for put()
    private Node put(Node current, Point2D p, Value val, RectHV rect,
                     boolean isVertical) {
        // Insert new point
        if (current == null) {
            size++;
            return new Node(p, val, rect);
        }

        // Overwrite value in duplicate case
        if (current.p.equals(p)) {
            current.val = val;
            return current;
        }

        if (isVertical) {
            if (current.p.x() <= p.x()) {
                rect = new RectHV(current.p.x(), rect.ymin(), rect.xmax(),
                                  rect.ymax());
                current.rightTop = put(current.rightTop, p, val, rect,
                                       !isVertical);
            }
            else {
                rect = new RectHV(rect.xmin(), rect.ymin(), current.p.x(),
                                  rect.ymax());
                current.leftBottom = put(current.leftBottom, p, val, rect,
                                         !isVertical);
            }
        }
        else {
            if (current.p.y() <= p.y()) {
                rect = new RectHV(rect.xmin(), current.p.y(), rect.xmax(),
                                  rect.ymax());
                current.rightTop = put(current.rightTop, p, val, rect,
                                       !isVertical);
            }
            else {
                rect = new RectHV(rect.xmin(), rect.ymin(), rect.xmax(),
                                  current.p.y());
                current.leftBottom = put(current.leftBottom, p, val, rect,
                                         !isVertical);
            }
        }
        return current;
    }

    // value associated with point p
    public Value get(Point2D p) {
        if (p == null) throw new IllegalArgumentException("p is null");

        return get(root, p, true);
    }

    // private helper method for get()
    private Value get(Node current, Point2D p, boolean isVertical) {
        if (current == null) return null;

        if (p.equals(current.p)) {
            return current.val;
        }

        if (isVertical) {
            if (current.p.x() <= p.x()) {
                return get(current.rightTop, p, !isVertical);
            }
            else {
                return get(current.leftBottom, p, !isVertical);
            }
        }
        else {
            if (current.p.y() <= p.y()) {
                return get(current.rightTop, p, !isVertical);
            }
            else {
                return get(current.leftBottom, p, !isVertical);
            }
        }
    }

    // does the symbol table contain point p?
    public boolean contains(Point2D p) {
        if (p == null) throw new IllegalArgumentException("p is null");

        return (get(p) != null);
    }

    // all points in the symbol table
    public Iterable<Point2D> points() {
        Queue<Node> queue = new Queue<Node>();
        Queue<Point2D> points = new Queue<Point2D>();
        queue.enqueue(root);
        while (!queue.isEmpty()) {
            Node current = queue.dequeue();
            if (current == null) continue;
            points.enqueue(current.p);
            queue.enqueue(current.leftBottom);
            queue.enqueue(current.rightTop);
        }
        return points;
    }

    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new IllegalArgumentException("rect is null");

        Stack<Point2D> stack = new Stack<>();
        return range(root, rect, stack);
    }

    // private helper method for range()
    private Iterable<Point2D> range(Node current, RectHV rect, Stack<Point2D> stack) {
        // return if the current node is null
        if (current == null) {
            return null;
        }
        // return to the stack if the bounding box does not intersect query rect
        if (!current.rect.intersects(rect)) {
            return stack;
        }
        // if the point belongs to the query
        if (rect.contains(current.p)) {
            stack.push(current.p);
        }
        // go left
        if (current.leftBottom != null) {
            range(current.leftBottom, rect, stack);
        }
        // go right
        if (current.rightTop != null) {
            range(current.rightTop, rect, stack);
        }
        return stack;
    }

    // a nearest neighbor of point p; null if the symbol table is empty
    public Point2D nearest(Point2D p) {
        if (p == null) throw new IllegalArgumentException("p is null");
        if (isEmpty()) return null;

        Point2D nearestPoint = root.p;
        return nearest(root, p, nearestPoint, true);
    }

    // private helper method for nearest()
    private Point2D nearest(Node current, Point2D p, Point2D nearestPoint,
                            boolean isVertical) {
        // return if the current Node is null
        if (current == null) {
            return nearestPoint;
        }

        // return if the bounding box is not closer than best so far
        double distance = nearestPoint.distanceSquaredTo(p);
        if (distance < current.rect.distanceSquaredTo(p)) {
            return nearestPoint;
        }
        // checking if the point is closer than the best so far
        double currentDistance = current.p.distanceSquaredTo(p);
        if (currentDistance < distance) {
            nearestPoint = current.p;
        }
        // checking to go left or right
        if (isVertical) {
            if (current.p.x() > p.x()) {
                nearestPoint = nearest(current.leftBottom, p, nearestPoint,
                                       !isVertical);
                nearestPoint = nearest(current.rightTop, p, nearestPoint,
                                       !isVertical);
            }
            else {
                nearestPoint = nearest(current.rightTop, p, nearestPoint,
                                       !isVertical);
                nearestPoint = nearest(current.leftBottom, p, nearestPoint,
                                       !isVertical);
            }
        }
        else {
            if (current.p.y() > p.y()) {
                nearestPoint = nearest(current.leftBottom, p, nearestPoint,
                                       !isVertical);
                nearestPoint = nearest(current.rightTop, p, nearestPoint,
                                       !isVertical);
            }
            else {
                nearestPoint = nearest(current.rightTop, p, nearestPoint,
                                       !isVertical);
                nearestPoint = nearest(current.leftBottom, p, nearestPoint,
                                       !isVertical);
            }
        }
        return nearestPoint;
    }


    // unit testing (required)
    public static void main(String[] args) {
        KdTreeST<String> testing = new KdTreeST<>();
        System.out.println("is empty: " + testing.isEmpty());
        System.out.println("The size is: " + testing.size());
        // points for test
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
