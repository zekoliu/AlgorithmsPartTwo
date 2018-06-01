import edu.princeton.cs.algs4.*;

public class KruskalMST {
    private Queue<Edge> mst;
    public KruskalMST(EdgeWeightedGraph G) {
        mst = new Queue<Edge>();
        MinPQ<Edge> pq = new MinPQ<Edge>();
        for (Edge e : G.edges()) pq.insert(e);
        UF uf = new UF(G.V());

        while (!pq.isEmpty() && mst.size() < G.V() - 1) {
            Edge e = pq.delMin();
            int v = e.either(), w = e.other(v);
            if (uf.connected(v, w)) continue;
            uf.union(v, w);
            mst.enqueue(e);
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

        KruskalMST mst = new KruskalMST(G);
        for (Edge e : mst.edges())
            StdOut.println(e);
        StdOut.println(mst.weight());
    }
}
