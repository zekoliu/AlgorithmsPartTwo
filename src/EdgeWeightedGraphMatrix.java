import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.StdOut;

public class EdgeWeightedGraphMatrix {
    private final int V;
    private int E;
    private double[][] weight;

    public EdgeWeightedGraphMatrix(int V) {
        this.V = V;
        this.E = 0;
        weight = new double[V][V];
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public void addEdge(Edge e) {
        int v = e.either(), w = e.other(v);
        if (weight[v][w] == 0 && weight[w][v] == 0) {
            weight[v][w] = e.weight();
            E++;
        }
    }

    public Iterable<Edge> adj(int v) {
        Edge current;
        Bag<Edge> adj = new Bag<Edge>();
        for (int i = 0; i < V; i++)
            if (weight[v][i] != 0) {
                current = new Edge(v, i, weight[v][i]);
                adj.add(current);
            }
        return adj;
    }

    public Iterable<Edge> edges() {
        Bag<Edge> temp = new Bag<Edge>();
        for (int i = 0; i < V; i++)
            for (Edge e : adj(i))
                temp.add(e);
        return temp;
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
        for (Edge e : G.edges())
            StdOut.println(e);
    }
}
