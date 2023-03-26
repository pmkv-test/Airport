package multithearding;

public class RunWay {
    private int countAll = 0;
    private int name;

    public RunWay(int name) {
        this.name = name;
    }

    public String getName() {
        return String.valueOf(name);
    }

    public void counter() {
        countAll++;
    }

    public int getCount() {
        return countAll;
    }

}
