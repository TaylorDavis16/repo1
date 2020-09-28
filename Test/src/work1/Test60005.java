package work1;
import java.util.Scanner;

public class Test60005 {
    public static void main(String []args){
        int ri, repeat;
        int i, index, k, n, temp,a[];
        Scanner in=new Scanner(System.in);
        repeat=in.nextInt();
        for(ri=1; ri<=repeat; ri++){//输入n次
            n=in.nextInt();
            a=new int[n];
            for(i=0; i<n; i++)
                a[i]=in.nextInt();
            /*---------*/
            for (k=a.length-1;k>0;k--){
                for (index=0;index<k;index++){
                    if (a[index]<a[index+1]){
                        temp=a[index];
                        a[index]=a[index+1];
                        a[index+1]=temp;
                    }
                }
            }
            /*---------*/
            for(i=0; i<n; i++)
                System.out.print(a[i]+" ");
            System.out.println();
        }
    }
}
