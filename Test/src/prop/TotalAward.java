package prop;

import java.util.Arrays;

public class TotalAward {
    public static void main(String[] args) {
        String a="Hello";
        String b="He"+new String("llo");
        System.out.println(a==b);
    }
    public static int max=0;
    public int count(int a, int b, int c){
        int[] array={a,b,c};
        Arrays.sort(array);
        return array[0]+balance(0,array[1]-array[0],array[2]-array[1]);
    }
    public int balance(int a, int b, int c){
        return 0;
    }
}
