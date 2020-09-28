package work1;
/**
 * 输入一个正整数n (n>0)，做 n 次下列运算：
 * 输入一组（5个）有序的整数，再输入一个整数 x，把 x 插入到这组数据中，使该组数据（6个数）仍然有序。
 * 说明：
 * 1）程序中的第一个大括号“{”必须位于类名所在行。
 * 2）类名与变量名由编程者自取。
 * 3）指明为整数或整数的数据，不要用浮点类型。
 *
 * 输入输出示例：括号内为说明
 * 输入：
 * 2               (n=2，后面将输入2组数据和对应的x)
 * 1 2 4 5 7       (第一组有序整数)
 * 3             (待插入整数x=3)
 * 1 2 5 7 9       (第二组有序整数)
 * -10            (待插入整数x=-10)
 * 输出（输出的每个数后有一个空格，每组数占一行）：
 * 1 2 3 4 5 7      (插入后的第一组6个有序数)
 * -10 1 2 5 7 9     (插入后的第二组6个有序数)
 */

import java.util.Scanner;
public class test2 {
    public static void main(String[] args){
        Scanner in=new Scanner(System.in);
        int n = in.nextInt();
        int[][] array=new int[n][6];
        for (int k=0;k<n;k++){

            for (int i=0;i<6;i++){
                array[k][i]=in.nextInt();
            }
            sort(array[k]);
        }

        for(int i=0;i<n;i++){
            for(int j=0;j<6;j++) System.out.print(array[i][j]+" ");
            System.out.println();
        }


    }

    static void sort(int[] a){
        for (int i=1;i<a.length;i++){
//            if (array[i].compareTo(array[i-1])>0)
//                continue;
//            time++;
            for (int j=0;j<i;j++){
                if (a[i]<a[j]){
                    int temp=a[i];
                    System.arraycopy(a,j,a,j+1,i-j);
                    a[j]=temp;
                    break;
                }
            }
        }
    }

}
