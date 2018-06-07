import edu.princeton.cs.algs4.*;
import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;

public class EdgeWeightedCycleFinder {
    private boolean[] marked;
    private int[] edgeTo;
    private Stack<Integer> cycle;
    private boolean[] onStack;

    public EdgeWeightedCycleFinder(EdgeWeightedDigraph G) {
        onStack = new boolean[G.V()];
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];

        for (int v = 0; v < G.V(); v++)
            if (!marked[v])
                dfs(G, v);
    }

    public void dfs(EdgeWeightedDigraph G, int v) {
        onStack[v] = true;
        marked[v] = true;
        for (DirectedEdge w : G.adj(v)) {
            int wTo = w.to();
            if (hasCycle()) return;
            else if (!marked[wTo]) {
                edgeTo[wTo] = v;
                dfs(G, wTo);
            }
            else if (onStack[w.to()]) {
                cycle = new Stack<Integer>();
                for (int x = v; x != wTo; x = edgeTo[x])
                    cycle.push(x);
                cycle.push(w.to());
                cycle.push(v);
            }
        }
        onStack[v] = false;
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<Integer> cycle() {
        return cycle;
    }

    public static void main(String[] args) {
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(new In(args[0]));
        EdgeWeightedCycleFinder EWcycle = new EdgeWeightedCycleFinder(G);
        if (EWcycle.hasCycle())
            for (int v : EWcycle.cycle())
                StdOut.print(v + "  ");
    }
}
