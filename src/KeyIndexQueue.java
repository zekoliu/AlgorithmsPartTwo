import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

public class KeyIndexQueue {

    public static void sort(Team[] a, int R) {
        int N = a.length, index = 0;
        Queue<Team>[] temp = new Queue[R];

        for (int i = 0; i < R; i++)
            temp[i] = new Queue<Team>();

        for (int i = 0; i < N; i++)
            temp[a[i].number()].enqueue(a[i]);

        for (int i = 0; i < R; i++)
            for (Team t : temp[i])
                a[index++] = t;
    }

    public static void main(String[] args) {
        Team lakers = new Team("Las", 1);
        Team rocket = new Team("Roc", 1);
        Team stongWind = new Team("StW", 4);
        Team china = new Team("Cha", 2);
        Team usa = new Team("USA", 3);
        Team bas = new Team("Bas", 2);
        Team[] ts = {lakers, rocket, stongWind, china, usa, bas};
        sort(ts, 5);
        for (int i = 0; i < ts.length; i++)
            StdOut.println(ts[i]);
    }
}
