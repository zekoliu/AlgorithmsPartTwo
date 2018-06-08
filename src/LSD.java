import edu.princeton.cs.algs4.StdOut;

public class LSD {

    public static void sort(String[] a, int w) {
        int N = a.length;
        int R = 256;
        String[] aux = new String[N];

        for (int d = w-1; d >= 0; d--) {
            int[] count = new int[R+1];     //计算出现频率
            for (int i = 0; i < N; i++)
                count[a[i].charAt(d) + 1]++;

            for (int r = 0; r < R; r++)     //将频率转换为索引
                count[r+1] += count[r];

            for (int i = 0; i < N; i++)     //将元素分类
                aux[count[a[i].charAt(d)]++] = a[i];

            for (int i = 0; i < N; i++)     //回写
                a[i] = aux[i];
        }
    }

    public static void main(String[] args) {
        String[] a = {"4PGC938", "2IYE230", "3CI0720", "1ICK750", "10HV845", "4JZY524", "1ICK750",
                        "3CIO720", "1OHV845", "1OHV845", "2RLA629", "2RLA629", "3ATW723"};
        int w = a[0].length();
        sort(a, 7);
        for (int i = 0; i < a.length; i++)
            StdOut.println(a[i]);
    }
}
