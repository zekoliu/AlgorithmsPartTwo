import edu.princeton.cs.algs4.StdOut;

public class KeyIndexCounting {

    public static void sort(Team[] a, int R) {
        int N = a.length;
        Team[] aux = new Team[N];
        int[] count = new int[R+1];

        for (int i = 0;  i < N; i++)
            count[a[i].number() + 1]++;

        for (int r = 0; r < R; r++)
            count[r+1] += count[r];

        for (int i = 0; i < N; i++)
            aux[count[a[i].number()]++] = a[i];

        for (int i = 0; i < N; i++)
            a[i] = aux[i];
    }

    public static void main(String[] args) {
        Team lakers = new Team("Las", 1);
        Team rocket = new Team("Roc", 1);
        Team stongWind = new Team("StW", 4);
        Team china = new Team("Cha", 2);
        Team usa = new Team("USA", 3);
        Team bas = new Team("Bas", 2);
        Team[] teams = {lakers, rocket, stongWind, china, usa, bas};
        sort(teams, 5);
        for (int i = 0; i < teams.length; i++)
            StdOut.println(teams[i]);
    }
}
