package endTermTest.A1819;

public class ArtTester {
    public static void main(String[] args) {
        ArtWork theQueen = new ArtWork("The Queen",1817);
        System.out.println(theQueen.age());
        ArtWork[] s=new ArtWork[3];
        s[0]=new ArtWork("Question1_b",1);
        for (ArtWork s1 : s) {
            System.out.println(s1);
        }
    }
}
