package work1;
/**
 * 程序填空，不要改变与输入输出有关的语句。
 * 输入一个正整数repeat (0<repeat<10)，做repeat次下列运算：
 * 输入1 个正整数 n (1≤n≤6)和n 阶方阵a中的元素，如果a是上三角矩阵, 输出"YES", 否则, 输出"NO"。(上三角矩阵，即主对角线以下的元素都为0, 主对角线为从矩阵的左上角至右下角的连线)
 * 例：括号内是说明
 * 输入：
 * 2       (repeat=2)
 * 3  1  2  3   0  4  5  0  0  6 (n=3)
 * 2  1  0  -8  2   (n=2)
 * 输出：
 * YES
 * NO
 */

import java.util.Scanner;
public class Test60013{
    public static void main(String []args){
        int ri, repeat;
        int a[][],i,j,n;
        boolean flag;
        Scanner in=new Scanner(System.in);
        repeat=in.nextInt();
        for(ri=1; ri<=repeat; ri++){
            n=in.nextInt();
            a=new int[n][n];
            for (i=0;i<n;i++)
                for (j=0;j<n;j++)
                    a[i][j]=in.nextInt();
            /*---------*/
            flag=true;
            for (i=0;i<n;i++)
                for (j=0;j<i;j++){
                    if (a[i][j]!=0){
                        flag=false;
                        break;
                    }
                }
            /*---------*/
            if(flag)  System.out.println("YES");
            else  System.out.println("NO");
        }
    }
}
