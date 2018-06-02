import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

public class LazyPrimAA {
    private boolean[] marked;
    private Queue<Edge> mst;
    private MinPQ<Double> pq;

    public LazyPrimAA(EdgeWeightedGraphMatrix G) {
        pq = new MinPQ<Double>();
        marked = new boolean[G.V()];
        mst = new Queue<Edge>();

        visit(G, 0);
        while (!pq.isEmpty()) {
            double weight = pq.delMin();
            int v = 0, w = 0;
            for (int i = 0; i < G.V(); i++)
                for (int j = 0; j < G.V(); j++)
                    if (G.getWeightAA(i, j) == weight) {
                        v = i;
                        w = j;
                    }
            if (marked[v] && marked[w]) continue;;
            mst.enqueue(new Edge(v, w, weight));
            if (!marked[v]) visit(G, v);
            if (!marked[w]) visit(G, w);
        }
    }

    private void visit(EdgeWeightedGraphMatrix G, int v) {
        marked[v] = true;
        for (int j = 0; j < G.adjAA(v).length && G.adjAA(v)[j] != 0; j++)
            if (!marked[G.adjAA(v)[j]]) pq.insert(G.getWeightAA(v, j));
    }

    public Iterable<Edge> edges() {
        return mst;
    }

    public static void main(String[] args) {
        EdgeWeightedGraphMatrix G = new EdgeWeightedGraphMatrix(8);
        G.addEdge(new Edge(4, 5, 0.35));
        G.addEdge(new Edge(4, 7, 0.37));
        G.addEdge(new Edge(5, 7, 0.28));
        G.addEdge(new Edge(0, 7, 0.16));
        G.addEdge(new Edge(1, 5, 0.32));
        G.addEdge(new Edge(0, 4, 0.38));
        G.addEdge(new Edge(2, 3, 0.17));
        G.addEdge(new Edge(1, 7, 0.19));
        G.addEdge(new Edge(0, 2, 0.26));
        G.addEdge(new Edge(1, 2, 0.36));
        G.addEdge(new Edge(1, 3, 0.29));
        G.addEdge(new Edge(2, 7, 0.34));
        G.addEdge(new Edge(6, 2, 0.40));
        G.addEdge(new Edge(3, 6, 0.52));
        G.addEdge(new Edge(6, 0, 0.58));
        G.addEdge(new Edge(6, 4, 0.93));

        LazyPrimAA mst = new LazyPrimAA(G);
        for (Edge e : mst.edges())
            StdOut.println(e);
    }
}