package Endterm.Question4_a;

public class Test {
    public void aVoid() throws UserNotFoundException{
        throw new UserNotFoundException();
    }
    
    public static void testDominator(int a, int b){
        try {
            int c=a/b;
        }
        catch (ArithmeticException e){
            e.printStackTrace();
        }
    }
}
