package Endterm.Question4_b;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class LineCounter1 {
    public static void main(String[] args) {
        try {
            BufferedReader reader=new BufferedReader(new FileReader(args[0]));
            int count=0;
            while (reader.readLine() !=null){
                count++;
            }
            reader.close();
            System.out.println(count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
