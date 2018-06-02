import edu.princeton.cs.algs4.*;

public class PrimMSB {
    private Edge[] edgeTo;
    private double[] distTo;
    private boolean[] marked;
    private IndexMinPQ<Double> pq;

    public PrimMSB(EdgeWeightedGraph G) {
        edgeTo = new Edge[G.V()];
        distTo = new double[G.V()];
        marked = new boolean[G.V()];
        CCMST cc = new CCMST(G);
        for (int v = 0; v < G.V(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;
        pq = new IndexMinPQ<Double>(G.V());

        for (int i = 1; i < cc.count() + 1; i++) {
            distTo[firstNum(i, G)] = 0;
            pq.insert(firstNum(i, G), 0.0);
            while (!pq.isEmpty())
                visit(G, pq.delMin());
        }
    }

    private void visit(EdgeWeightedGraph G, int v) {
        marked[v] = true;
        for (Edge e : G.adj(v)) {
            int w = e.other(v);
            if (marked[w]) continue;;
            if (e.weight() < distTo[w]) {
                edgeTo[w] = e;
                distTo[w] = e.weight();
                if (pq.contains(w)) pq.change(w, distTo[w]);
                else                pq.insert(w, distTo[w]);
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

    public int firstNum(int id, EdgeWeightedGraph G) {
        CC cc = new CC(G);

        int M = cc.count();
        Queue<Integer>[] queue = (Queue<Integer>[]) new Queue[M];
        for (int i = 0; i < M; i++)
            queue[i] = new Queue<Integer>();
        for (int i = 0; i < G.V(); i++)
            queue[cc.id(i)].enqueue(i);

        int temp = 0;
        for (int i = 0; i < id; i++)
            temp = queue[i].dequeue();
        return temp;
    }

    public static void main(String[] args) {
        EdgeWeightedGraph G = new EdgeWeightedGraph(new In(args[0]));;

        PrimMSB msb = new PrimMSB(G);
        for (Edge e : msb.edges())
            StdOut.println(e);
        StdOut.println(msb.weight());
    }

}
