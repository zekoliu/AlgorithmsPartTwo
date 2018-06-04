import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class DijkstraAllPairsSP {
    private DijkstraSP[] all;

    public DijkstraAllPairsSP(EdgeWeightedDigraph G) {
        all = new DijkstraSP[G.V()];
        for (int v = 0; v < G.V(); v++)
            all[v] = new DijkstraSP(G, v);
    }

    public Iterable<DirectedEdge> path(int s, int t) {
        return all[s].pathTo(t);
    }

    public double dist(int s, int t) {
        return all[s].distTO(t);
    }

    public static void main(String[] args) {
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(new In(args[0]));
        DijkstraAllPairsSP sp = new DijkstraAllPairsSP(G);
        int t = Integer.parseInt(args[1]);

        for (int i = 0; i < G.V(); i++) {
            StdOut.print(i + " to " + t);
            StdOut.printf(" (%4.2f): ", sp.dist(i, t));
            for (DirectedEdge e : sp.path(i, t))
                StdOut.print(e + "  ");
            StdOut.println();
        }
    }
}
