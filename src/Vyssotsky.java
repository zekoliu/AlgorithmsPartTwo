
import edu.princeton.cs.algs4.*;

public class Vyssotsky {
    private MaxPQ<Edge> mst;

    public Vyssotsky(EdgeWeightedGraph G) {
        mst = new MaxPQ<Edge>();
        MinPQ<Edge> pq = new MinPQ<Edge>();
        for (Edge e : G.edges()) pq.insert(e);
        UF uf = new UF(G.V());

        while (!pq.isEmpty()&& mst.size() < G.V() - 1) {
            Edge e = pq.delMin();
            int v = e.either(), w = e.other(v);
            if (uf.connected(v, w)) {
                uf.union(v, w);
                mst.insert(e);
                mst.delMax();
            }
            uf.union(v, w);
            mst.insert(e);
        }
    }

    public Iterable<Edge> edges() {
        return mst;
    }

    public double weight() {
        double temp = 0;
        for (Edge e : edges())
            temp = temp + e.weight();
        return temp;
    }

    public static void main(String[] args) {
        EdgeWeightedGraph G = new EdgeWeightedGraph(new In(args[0]));

        Vyssotsky mst = new Vyssotsky(G);
        for (Edge e : mst.edges())
            StdOut.println(e);
        StdOut.println(mst.weight());
    }
}
