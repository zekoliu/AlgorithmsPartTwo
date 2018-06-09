import edu.princeton.cs.algs4.StdOut;

public class Quick3string {
    private static int chatAt(String s, int d) {
        if (d < s.length())
            return s.charAt(d);
        else return -1;
    }

    public static void sort(String[] a) {
        sort(a, 0, a.length-1, 0);
    }

    private static void sort(String[] a, int lo, int hi, int d) {
        if (hi <= lo) return;
        int lt = lo, gt = hi;
        int v = chatAt(a[lo], d);
        int i = lo + 1;
        while (i <= gt) {
            int t = chatAt(a[i], d);
            if      (t < v) exch(a, lt++, i++);
            else if (t > v) exch(a, i, gt--);
            else            i++;
        }
        sort(a, lo, lt-1, d);
        if (v >= 0) sort(a, lt, gt, d + 1);
        sort(a, gt+1, hi, d);
    }

    private static void exch(String[] a, int i, int j) {
        String tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    public static void main(String[] args) {
        String[] s = {"edu.princeton.cs", "com.apple", "com.cnn", "com.google", "edu.uva.cs", "edu.princeton.cs",
                        "edu.princeton.cs.www", "edu.uva.cs", "edu.uva.cs", "edu.uva.cs", "com.adobe", "edu.princeton.ee"};
        sort(s);
        for (int i = 0; i < s.length; i++)
            StdOut.println(s[i]);
    }
}
