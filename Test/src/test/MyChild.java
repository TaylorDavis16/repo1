package test;

public class MyChild extends MyParent{
    int count;
    public MyChild(int cnt, int num) {
        super(num);
        this.count=cnt;
    }

    public MyChild(int cnt) {
        super(cnt);
        this.count = cnt;
    }
}
