import edu.princeton.cs.algs4.StdOut;

public class Quick3integer {

    public static void sort(int[] a) {
        sort(a, 0, a.length - 1, 0);
    }

    private static void sort(int[] a, int lo, int hi, int d) {
        if (hi <= lo) return;;
        int lt = lo, gt = hi;
        int v = a[lo];
        int i = lo + 1;
        while (i <= gt) {
            int t = a[i];
            if      (t < v) exch(a, lt++, i++);
            else if (t > v) exch(a, i, gt--);
            else            i++;
        }
        sort(a, lo, lt-1, d);
        sort(a, gt + 1, hi, d);
    }

    private static void exch(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    public static void main(String[] args) {
        int[] a = {1, 10, 9, 8, 8, 3, 12, 10, 23, 1, 0};
        sort(a);
        for (int i = 0; i < a.length; i++)
            StdOut.print(a[i] + "  ");
    }
}