import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Topological;

public class ShortestCommonAncestor {

    private Digraph G;  // directed graph

    // constructor takes a rooted DAG as argument
    public ShortestCommonAncestor(Digraph G) {
        this.G = new Digraph(G);
        if (!isRooted(this.G)) {
            throw new IllegalArgumentException("Not rooted");
        }
    }

    // helper method
    private boolean isRooted(Digraph g) {
        Topological topological = new Topological(g);
        int count = 0;
        if (topological.hasOrder()) {
            for (int i = 0; i < g.V(); i++) {
                if (g.outdegree(i) == 0) {
                    count++;
                }
                if (count > 1) {
                    return false;
                }
            }
        }
        if (count == 1) return true;
        return false;
    }

    // length of shortest ancestral path between v and w
    public int length(int v, int w) {
        BreadthFirstDirectedPaths pathFromV = new BreadthFirstDirectedPaths(this.G, v);
        BreadthFirstDirectedPaths pathFromW = new BreadthFirstDirectedPaths(this.G, w);
        int shortestDistance = Integer.MAX_VALUE;
        int currentDistance = Integer.MAX_VALUE;
        for (int i = 0; i < G.V(); i++) {
            if ((pathFromV.hasPathTo(i)) && (pathFromW.hasPathTo(i))) {
                if ((pathFromV.distTo(i) != Integer.MAX_VALUE) && (pathFromW.distTo(i)
                        != Integer.MAX_VALUE)) {
                    currentDistance = pathFromV.distTo(i) + pathFromW.distTo(i);
                }
                else currentDistance = Integer.MAX_VALUE;
                if (currentDistance < shortestDistance) {
                    shortestDistance = currentDistance;
                }
            }
        }
        return shortestDistance;
    }

    // a shortest common ancestor of vertices v and w
    public int ancestor(int v, int w) {
        if ((v < 0 || v >= G.V()) && (w < 0 || w >= G.V())) {
            throw new IndexOutOfBoundsException();
        }
        BreadthFirstDirectedPaths pathFromV = new BreadthFirstDirectedPaths(this.G, v);
        BreadthFirstDirectedPaths pathFromW = new BreadthFirstDirectedPaths(this.G, w);
        int shortestDistance = Integer.MAX_VALUE;
        int shortestAncestor = Integer.MAX_VALUE;
        int currentDistance = Integer.MAX_VALUE;
        for (int i = 0; i < G.V(); i++) {
            if ((pathFromV.hasPathTo(i)) && (pathFromW.hasPathTo(i))) {
                if ((pathFromV.distTo(i) != Integer.MAX_VALUE) && (pathFromW.distTo(i)
                        != Integer.MAX_VALUE)) {
                    currentDistance = pathFromV.distTo(i) + pathFromW.distTo(i);
                }
                else currentDistance = Integer.MAX_VALUE;
                if (currentDistance < shortestDistance) {
                    shortestDistance = currentDistance;
                    shortestAncestor = i;
                }
            }
        }
        return shortestAncestor;
    }

    // length of shortest ancestral path of vertex subsets A and B
    public int lengthSubset(Iterable<Integer> subsetA, Iterable<Integer> subsetB) {
        BreadthFirstDirectedPaths fromSubsetA = new BreadthFirstDirectedPaths(this.G, subsetA);
        BreadthFirstDirectedPaths fromSubsetB = new BreadthFirstDirectedPaths(this.G, subsetB);
        int shortestDistance = Integer.MAX_VALUE;
        int currentDistance = Integer.MAX_VALUE;

        for (int i = 0; i < G.V(); i++) {
            if ((fromSubsetA.hasPathTo(i)) && (fromSubsetB.hasPathTo(i))) {
                if ((fromSubsetA.distTo(i) != Integer.MAX_VALUE) && (fromSubsetB.distTo(i)
                        != Integer.MAX_VALUE)) {
                    currentDistance = fromSubsetA.distTo(i) + fromSubsetB.distTo(i);
                }
                else currentDistance = Integer.MAX_VALUE;
                if (currentDistance < shortestDistance) {
                    shortestDistance = currentDistance;
                }
            }
        }
        return shortestDistance;
    }

    // a shortest common ancestor of vertex subsets A and B
    public int ancestorSubset(Iterable<Integer> subsetA, Iterable<Integer> subsetB) {
        BreadthFirstDirectedPaths fromSubsetA = new BreadthFirstDirectedPaths(G, subsetA);
        BreadthFirstDirectedPaths fromSubsetB = new BreadthFirstDirectedPaths(G, subsetB);
        int shortestDistance = Integer.MAX_VALUE;
        int shortestAncestor = Integer.MAX_VALUE;
        int currentDistance = Integer.MAX_VALUE;
        for (int i = 0; i < G.V(); i++) {
            if ((fromSubsetA.hasPathTo(i)) && (fromSubsetB.hasPathTo(i))) {
                if ((fromSubsetA.distTo(i) != Integer.MAX_VALUE) && (fromSubsetB.distTo(i)
                        != Integer.MAX_VALUE)) {
                    currentDistance = fromSubsetA.distTo(i) + fromSubsetB.distTo(i);
                }
                else currentDistance = Integer.MAX_VALUE;
                if (currentDistance < shortestDistance) {
                    shortestDistance = currentDistance;
                    shortestAncestor = i;
                }
            }
        }
        return shortestAncestor;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        ShortestCommonAncestor sca = new ShortestCommonAncestor(G);
        while (!StdIn.isEmpty()) {
            int v = StdIn.readInt();
            int w = StdIn.readInt();
            int length = sca.length(v, w);
            int ancestor = sca.ancestor(v, w);
            StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
        }
       
    }
}
