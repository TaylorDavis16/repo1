package Endterm.Question2_c;

public class IdentifyArray {
    public static boolean isEqual(int[] ar1, int[] ar2){
        if (ar1==null){
            return ar2==null;
        }
        if (ar2==null){
            return false;
        }
        if (ar1.length!=ar2.length)
            return false;
        for (int i=0;i<ar1.length;i++){
            if (ar1[i]!=ar2[i]){
                return false;
            }
        }
        return true;
    }
    
    public static boolean isEqual2(int[] ar1, int[] ar2) {
        if (ar1 == ar2)
            return true;
        if (ar1 == null || ar2 == null)
            return false;
        if (ar1.length != ar2.length)
            return false;
        for(int i=0; i<ar1.length; i++) { 
            if(ar1[i] != ar2[i])
            return false;
        }
        return true; 
    }
}
