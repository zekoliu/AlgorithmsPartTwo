import edu.princeton.cs.algs4.StdOut;

public class StringST {
    private String[] strings;
    private int N = 10, index = 0, size = 0;

    public StringST() {
        strings = new String[N];
    }

    public void  add(String key) {
        if (key == null)
            return;
        if (size > 3/4 * strings.length)
            reverse(2 * N);
        strings[index++] = key;
        size++;
    }

    public void delete(String key) {
        if (size > 1/4 * strings.length && N > 10)
            reverse(N/2);
        for (int i = 0; i < size; i++) {
            if (strings[i].equals(key)) {
                for (int j = i; j < size; j++)
                    strings[j] = strings[j + 1];
                index--;
                size--;
                return;
            }
        }
    }

    public boolean contains(String key) {
        for (int i = 0; i < size; i++)
            if (strings[i].equals(key))
                return true;
        return false;
    }

    public boolean isEmpty() {
        return size != 0;
    }

    public int size() {
        return size;
    }

    public String toString() {
        String s = "";
        for (int i = 0; i < size; i++)
            s = s + strings[i] + " ";
        return s;
    }

    private void reverse(int N) {
        String[] temp = new String[N];
        for (int i = 0; i < strings.length; i++)
            temp[i] = strings[i];
        strings = temp;
    }

    public static void main(String[] args) {
        StringST st = new StringST();
        st.add("kobe");
        st.add("curry");
        st.add("dunant");
        st.add("jordan");
        st.add("zekoliu");
        st.add("harden");
        st.add("paul");
        st.add("jordan");
        st.add("hash");
        st.add("who");
        st.add("name");
        if (st.contains("kobe")) {
            st.delete("kobe");
            st.delete("curry");
            st.delete("dunant");
            st.delete("jordan");
            st.delete("zeokoliu");
            st.delete("harden");
            st.delete("paul");
            st.delete("name");
            st.delete("who");
        }
        StdOut.print(st.toString() + " " + st.size());
    }
}