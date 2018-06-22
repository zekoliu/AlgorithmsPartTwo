import edu.princeton.cs.algs4.StdOut;

public class MSD {
    private static int R = 256;
    private static final int M = 15;
    private static String[] aux;
    private static int chatAt(String s, int d) {
        if (d < s.length()) return s.charAt(d);
        else return -1;
    }

    public static void sort(String[] a) {
        int N = a.length;
        aux = new String[N];
        sort(a, 0, N-1, 0);
    }

    private static void sort(String[] a, int lo, int hi, int d) {
        if (hi <= lo + M) {
            insert(a, lo, hi, d);
            return;
        }
        int[]count = new int[R+2];
        for (int i = lo; i <= hi; i++)
            count[chatAt(a[i], d) + 2]++;

        for (int r = 0; r < R+1; r++)
            count[r+1] += count[r];

        for (int i = lo; i <= hi; i++)
            aux[count[chatAt(a[i], d) + 1]++] = a[i];

        for (int i = lo; i <= hi; i++)
            a[i] = aux[i - lo];

        for (int r = 0; r < R; r++)
            sort(a, lo + count[r], lo + count[r+1]- 1, d+1);
    }

    public static void insert(String[] a, int lo, int hi, int d) {
        for (int i = lo; i <= hi; i++)
            for (int j = i; j > lo && less(a[j], a[j-1], d); j--)
                exch(a, j, j - 1);
    }

    private static boolean less(String v, String w, int d) {
        return v.substring(d).compareTo(w.substring(d)) < 0;
    }

    private static void exch(String[] a, int i, int j) {
        String temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void main(String[] args) {
        String[] a = {"she", "sells", "seashells", "by", "the", "seashore", "the", "shells",
                "she", "sells", "are", "sirely", "seashells"};
        sort(a);
        for (int i = 0; i < a.length; i++)
            StdOut.println(a[i]);
    }
}