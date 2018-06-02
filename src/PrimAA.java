import edu.princeton.cs.algs4.*;

public class PrimAA {
    private Edge[] edgeTo;
    private double[] distTo;
    private boolean[] marked;
    private double[] pq;
    private Queue<Integer> alread;

    public PrimAA(EdgeWeightedGraph G) {
        edgeTo = new Edge[G.V()];
        distTo = new double[G.V()];
        marked = new boolean[G.V()];
        alread = new Queue<Integer>();
        for (int v = 0; v < G.V(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;
        pq = new double[G.V()];

        distTo[0] = 0.0;
        alread.enqueue(0);
        while (!alread.isEmpty()) {
            if (alread.size() > 1)
                sortAlread(alread);
            visit(G, alread.dequeue());
        }
    }

    private void visit(EdgeWeightedGraph G, int v) {
        marked[v] = true;
        for (Edge e : G.adj(v)) {
            int w = e.other(v);
            if (marked[w]) continue;
            if (e.weight() < distTo[w]) {
                edgeTo[w] = e;
                distTo[w] = e.weight();
                if (alreadAccess(w)) {
                    pq[w] = distTo[w];
                } else {
                    pq[w] = distTo[w];
                    alread.enqueue(w);
                }
            }
        }
    }

    public Iterable<Edge> edges() {
        Bag<Edge> temp = new Bag<Edge>();
        for (int i = 0; i < edgeTo.length; i++)
            if (edgeTo[i] != null)
                temp.add(edgeTo[i]);
        return temp;
    }

    public double weight() {
        double result = 0;
        for (Edge e : edges())
            result =  result + e.weight();
        return result;
    }
    
    public boolean alreadAccess(int v) {
        for (int i : alread)
            if (v == i)
                return true;
        return false;
    }

    public void sortAlread(Queue<Integer> e) {
        int i = 0, size = e.size();
        Comparable[] temp = new Comparable[size];
        for (int v = 0 ; v < size; v++)
            temp[i++] = e.dequeue();
        Quick.sort(temp);
        for (int v = 0; v < size; v++)
            e.enqueue((int)temp[v]);
//        StdOut.println(e.size());
    }

    public static void main(String[] args) {
        EdgeWeightedGraph G = new EdgeWeightedGraph(new In(args[0]));

        PrimAA mst = new PrimAA(G);
        for (Edge e : mst.edges())
            StdOut.println(e);
        StdOut.println(mst.weight());
    }
}