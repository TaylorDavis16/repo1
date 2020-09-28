package test;

import java.util.Arrays;

public class BitOperation {
    public static void main(String[] args) {
        System.out.println("位运算奇技淫巧大全：");
        System.out.println("&: 两个为1才为1 eg: 1 & 2 = "+(1&2));
        System.out.println("|: 两个有1则为1 eg: 1 | 2 = "+(1|2));
        System.out.println("^: 相同为0不同为1  eg: 1 ^ 2 = "+(1^3));
        System.out.println("~: 0->1 1->0  eg: ~0 = "+(~0));
        System.out.println("<<: 左移运算，向左进行移位操作，高位丢弃，低位补 0 eg: "+(8<<3));
        System.out.println(">>: 右移运算，向右进行移位操作，对无符号数，高位补 0，对于有符号数，高位补符号位");
        System.out.println("8 >> 3="+(8>>3)+"   -8 >> 3="+(-8>>3));
        System.out.println("Tips:----------------------------------------------");
        System.out.println("1.交换两数：[1, 2] -> "+ Arrays.toString(swap(new int[]{1, 2})));
        System.out.println("2.根据数的最后一位是 0 还是 1 判断奇偶 2 , 3: "+judge(2)+", "+judge(3));
        System.out.println("3.取反： 16 -> "+reverse(16)+"; -16 -> "+reverse(-16));
        System.out.println("4.取绝对值：16 -> "+absoluteValue(16)+"; -16 -> "+absoluteValue(-16));
        System.out.println("5.交换高低位: "+exchangeBits(1));
    }

    /**
     * Swap numbers
     * @param values number array
     * @return number array
     */
    public static int[] swap(int[] values){
        values[0]^=values[1];
        values[1]^=values[0];
        values[0]^=values[1];
//        values[0]^=values[1]^=values[0]^=values[1];
        return values;
    }

    /**
     * Judge if a number is even or odd
     * @param num the number
     * @return "Even" or "Odd", which depends on the number
     */
    public static String judge(int num){
        return 0 == (num & 1) ? "Even" : "Odd";
    }

    /**
     * Reverse a number to the other side: 1 -> -1
     * @param num the number
     * @return the other side's number
     */
    public static int reverse(int num){
        return ~num + 1 ;
    }

    /**
     * Move 31 bits right, get 0 if it is even, -1 otherwise.
     * Any value ^ 0 will get itself and any value ^ -1 is synonymous with ~
     * @param num the number
     * @return the absolute value
     */
    public static int absoluteValue(int num){
        int i=num>>31;
        return (num^i) - i;
    }

    /**
     * Exchange high and low 4 bits
     * @param num the number
     * @return the exchanged number
     */
    public static int exchangeBits(int num){
        return (num>>4 | num<<4);
    }
    
}
