public class Team {
    private String name;
    private int num;

    public Team(String name, int num) {
        this.name = name;
        this.num = num;
    }

    public String name() {
        return name;
    }

    public int number() {
        return num;
    }

    public String toString() {
        return name + "  " + num;
    }
}
