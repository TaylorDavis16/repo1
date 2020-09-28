package work1;

import java.util.Scanner;
public class Test60014{
    public static void main(String []args){
        int ri, repeat;
        int i,j,m,n,a[][],row[];
        Scanner in=new Scanner(System.in);
        repeat=in.nextInt();
        for(ri=1; ri<=repeat; ri++){
            m=in.nextInt();
            n=in.nextInt();
            a=new int[m][n];
            row=new int[m];
            for (i=0;i<m;i++)
                for (j=0;j<n;j++)
                    a[i][j]=in.nextInt();
            /*---------*/
            for (i=0;i<m;i++)
                for (j=0;j<n;j++)
                    row[i]+=a[i][j];
            /*---------*/
            for(i=0;i<m;i++)
                System.out.println("sum of row "+i+" is "+row[i]);
        }
    }
}