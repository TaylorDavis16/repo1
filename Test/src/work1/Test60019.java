package work1;

import java.util.Scanner;
public class Test60019{
    public static void main(String []args){
        int ri, repeat;
        int i,j,k,row,col,n,a[][];
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
            flag=false;
            row=0;
            col=0;
            /*---------*/

            if(flag)  System.out.println("Question1_b["+row+"]["+col+"]="+a[row][col]);
            else   System.out.println("NO");
        }
    }
}