import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.*;

public class DijkstraSP {
    private DirectedEdge[] eageTo;
    private double[] distTo;
    private IndexMinPQ<Double> pq;

    public DijkstraSP(EdgeWeightedDigraph G, int s) {
        eageTo = new DirectedEdge[G.V()];
        distTo = new double[G.V()];
        pq = new IndexMinPQ<Double>(G.V());

        for (int v = 0; v < G.V(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;
        distTo[s] = 0;

        G = changeWeight(G);
        pq.insert(s, 0.0);
        while (!pq.isEmpty())
            relax(G, pq.delMin());
    }

    private void relax(EdgeWeightedDigraph G, int v) {
        for (DirectedEdge e : G.adj(v)) {
            int w = e.to();
            if (distTo[w] > distTo[v] + e.weight()) {
                distTo[w] = distTo[v] + e.weight();
                eageTo[w] = e;
                if (pq.contains(w)) pq.change(w, distTo[w]);
                else                pq.insert(w, distTo[w]);
            }
        }
    }

    public EdgeWeightedDigraph changeWeight(EdgeWeightedDigraph G) {
        double temp = 0;
        for (int v = 0; v < G.V(); v++)
            for (DirectedEdge e : G.adj(v))
                if (e.weight() < temp)
                    temp = e.weight();
        if (temp == 0)
            return G;
        EdgeWeightedDigraph newG = new EdgeWeightedDigraph(G.V());
        for (int v = 0; v < G.V(); v++)
            for (DirectedEdge e : G.adj(v))
                newG.addEdge(new DirectedEdge(e.from(), e.to(), e.weight() + Math.abs(temp)));
        return newG;
    }

    public double distTO(int v) {
        return distTo[v];
    }

    public boolean hasPathTo(int v) {
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    public Iterable<DirectedEdge> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<DirectedEdge> path = new Stack<DirectedEdge>();
        for (DirectedEdge e = eageTo[v]; e != null; e = eageTo[e.from()])
            path.push(e);
        return path;
    }

    public static void main(String[] args) {
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(new In(args[0]));
        int s = Integer.parseInt(args[1]);

        DijkstraSP sp = new DijkstraSP(G, s);
        for (int t = 0; t < G.V(); t++) {
            StdOut.print(s + " to " + t);
            StdOut.printf(" (%4.2f): " , sp.distTo[t]);
            if (sp.hasPathTo(t))
                for (DirectedEdge e : sp.pathTo(t))
                    StdOut.print(e + "  ");
            StdOut.println();
        }
    }
}