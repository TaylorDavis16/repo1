package work1;
import java.util.Scanner;
public class Test60030 {
    public static void main(String []args){
        int ri, repeat;
        int i, n, a[];
        Scanner in=new Scanner(System.in);
        repeat=in.nextInt();
        for(ri=1; ri<=repeat; ri++){
            n=in.nextInt();
            a=new int[n];
            for(i=0; i<n; i++)
                a[i]=in.nextInt();
            sort(a);
            for(i=0; i<n; i++)
                System.out.print(a[i]+" ");
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
