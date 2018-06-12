import edu.princeton.cs.algs4.StdOut;

public class TriesSTAA<Value> {
    private static int R = 256;
    private Node root;

    private static class Node {
        private Object val;
        private Node[] next = new Node[R];
    }

    public Value get(String key) {
        Node x = get(root, key, 0);
        if (x == null) return null;
        return (Value)x.val;
    }

    private Node get(Node x, String key, int d) {
        if (x == null) return null;

       for (d = 0; d < key.length(); d++) {
           char c = key.charAt(d);
           if (x.next[c] == null)
               return null;
           x = x.next[c];
       }
       return x;
    }

    public void put(String key, Value val) {
        root = put(root, key, val, 0);
    }

    private Node put(Node x, String key, Value val, int d) {
        if (x == null)
            x = new Node();

        for (d = 0; d < key.length(); d++) {
            char c = key.charAt(d);
            x = x.next[c];
            if (x == null)
                x = new Node();
        }
        x.val = val;
        return x;
    }

    public static void main(String[] args) {
        TriesSTAA<Integer> staa = new TriesSTAA<Integer>();
        staa.put("kobe", 24);
        staa.put("curry", 30);
        StdOut.println(staa.get("kobe") + "   " + staa.get("curry"));
    }
}
