import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class EdgeWeightedDigraph {
    private final int V;
    private int E;
    private Bag<DirectedEdge>[] adj;

    public EdgeWeightedDigraph(int V) {
        this.V = V;
        this.E = 0;
        adj = (Bag<DirectedEdge>[]) new Bag[V];
        for (int v = 0; v < V; v++)
            adj[v] = new Bag<DirectedEdge>();
    }

    public EdgeWeightedDigraph(In in) {
        this(in.readInt());
        this.E = in.readInt();

        for (int i = 0; i < E; i++) {
            int v = in.readInt();
            int w = in.readInt();
            double weight = in.readDouble();
            DirectedEdge e = new DirectedEdge(v, w, weight);
            addEageIn(e);
//            StdOut.println(e.toString() + " " + E());
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public void addEage(DirectedEdge e) {
        adj[e.from()].add(e);
        E++;
    }

    public void addEageIn(DirectedEdge e) {
        adj[e.from()].add(e);
    }

    public Iterable<DirectedEdge> adj(int v) {
        return adj[v];
    }

    public Iterable<DirectedEdge> edges() {
        Bag<DirectedEdge> bag = new Bag<DirectedEdge>();
        for (int v = 0; v < V; v++)
            for (DirectedEdge e : adj[v])
                bag.add(e);
        return bag;
    }

    public String toString() {
        String s = "";
        for (int v = 0; v < V; v++)
            for (DirectedEdge e : adj[v])
                s = s + e.toString() + "\n";
        return s;
    }

    public static void main(String[] args) {
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(new In(args[0]));
//        for (DirectedEdge e : G.edges()) {
//            StdOut.print(e.toString() + " ");
//            StdOut.println();
//        }
        StdOut.println(G.toString());
    }
}