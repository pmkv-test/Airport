package multithearding;

public class RunWay {
    private int countAll = 0;
    private int nameRunWay;

    public RunWay(int name) {
        this.nameRunWay = name;
    }

    public String getName() {
        return String.valueOf(nameRunWay);
    }

    public void counter() {
        countAll++;
    }

    public int getCount() {
        return countAll;
    }

}
