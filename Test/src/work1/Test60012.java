package work1;

import java.util.Scanner;
public class Test60012{
    public static void main(String []args){
        int ri, repeat;
        int i,j,n,a[][]=new int[10][10];
        Scanner in=new Scanner(System.in);
        repeat=in.nextInt();
        for(ri=1; ri<=repeat; ri++){
            n=in.nextInt();
            /*---------*/
            for (i=0;i<n;i++){
                a[0][i+1]=i+1;
            }
            for (i=0;i<n;i++){
                a[i+1][0]=i+1;
            }
            for (i=1;i<=n;i++){
                for (j=1;j<=i;j++){
                    a[i][j]=i+j;
                }

            }
            /*---------*/
            for( i=0; i<=n; i++ ){
                for( j=0; j<=n; j++ )
                    if(i==0&&j==0) System.out.print( "+   ");
                    else if(i==0||j<=i) System.out.print( a[i][j]+"   ");
                System.out.println();
            }
        }
    }
}