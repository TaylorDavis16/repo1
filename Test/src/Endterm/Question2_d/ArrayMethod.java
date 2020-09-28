package Endterm.Question2_d;

import java.util.Arrays;

public class ArrayMethod {

    public static void main(String[] args) {
        int[] array={1,2,3};
        System.out.println(Arrays.toString(inflateArray(5,array)));
    }
    public static int[] enlargeArray(int[] array, int n){
        if (array==null)
            return null;
        if (n<0){
            n=0;
        }
        if (n==0)
            return new int[]{};
        int[] result=new int[n*array.length];
        int index=0;
        for (int element : array) {
            for (int i=index;i<index+n;i++){
                result[i]=element;
            }
            index+=n;
        }
        return result;
    }

    public static int[] method(int n,int[] array){
        if (array!=null){
            if (n>=0){
                if (n==0)
                    return new int[]{};
                int[] newArray=new int[n*array.length];
                int point=0;
                for (int i=0;i<array.length;i++) {
                    for (int j=point;j<point+n;j++){
                        newArray[j]=array[i];
                    }
                    point+=n;
                }
                return newArray;
            }
        }
        return null;
    }

    public static int[] inflateArray(int n,int[] array){
        if (array!=null){
            if (n==0)
                return new int[]{};
            int[] ints=new int[n*array.length];
            int point=0,i=0;
            while (i<array.length){
                for (int j=point;j<point+n;j++){
                    ints[j]=array[i];
                }
                i++;
                point+=n;
            }
            return ints;
        }
        return null;
    }
}
