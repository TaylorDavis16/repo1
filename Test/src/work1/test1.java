package work1;

import java.util.Scanner;

/**
 * 编程，输入一个正整数 n (n>0)，做 n 次下列运算：
 * 输入1个3×3的整数矩阵，将它们存入数组 Question1_b 中，交换其第一行和最后一行后，再以矩阵格式输出。
 * 矩阵中每个元素的输出参数为：Question1_b[i][j]+"\t"
 *
 * 说明：
 * 1）程序中的第一个大括号“{”必须位于类名所在行。
 * 2）类名与变量名由编程者自取。
 * 3）指明为整数或整数的数据，不要用浮点类型。
 *
 * 输入输出示例：括号内为说明
 * 输入：
 * 2		(n=2，后面将分别输入2个矩阵)
 * 1 3 5     (第一个矩阵)
 * 7 9 5
 * 8 9 0
 * 7 3 5     (第二个矩阵)
 * 9 71 22
 * 9 7 66
 * 输出：（每个数据后有一个制表符）
 * 8	9	0	      (第一个矩阵输出)
 * 7	9	5
 * 1	3	5
 * 9	7	66	      (第二个矩阵输出)
 * 9	71	22
 * 7	3	5
 */
public class test1 {
    public static void main(String[] args) {
        int a[][] = new int[3][3],i,j,number,n;
        Scanner in=new Scanner(System.in);
        n=in.nextInt();
        for(number=0; number<n; number++){//每次矩阵输入
            for(i=0; i<3; i++)//第X个矩阵赋值
                for(j=0;j<3;j++)
                    a[i][j]=in.nextInt();
            /*---------*/
            int temp;
            for (i=0;i<3;i++){
                temp=a[0][i];
                a[0][i]=a[2][i];
                a[2][i]=temp;
            }

            /*---------*/
            for(i=0; i<3; i++){//第X个矩阵输出
                for(j=0;j<3;j++){
                    System.out.print(a[i][j]+"\t");
                }
                System.out.println();
            }
        }

    }
}
