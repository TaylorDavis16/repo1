package test;

public class testFinal {
    private final int l;
    private static final int ll;
   static {
       ll=1;
   }
    public testFinal(int l) {
        this.l = l;
    }

    public static void main(String[] args) {
        final int i;
        i=5;
        System.out.println(i);
    }
}
