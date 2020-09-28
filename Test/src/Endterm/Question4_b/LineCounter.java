package Endterm.Question4_b;

import java.io.*;
public class LineCounter {
    public static void main(String[] args) {
        String fileName=args[0];
        File file=new File(fileName);
        try {
            BufferedReader reader=new BufferedReader(new FileReader(file));
            String line;
            int count=0;
            while ((line=reader.readLine())!=null){
                count++;
            }
            reader.close();
            System.out.println("The number of line in this file is "+count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
