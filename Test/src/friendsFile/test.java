package friendsFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class test {
    public static void main(String[] args) {
        String executeString="cmd /c ping -n "+5+" "+"www.baidu.com";
        BufferedReader reader;
        try {
            Process p = Runtime.getRuntime().exec(executeString);
            p.waitFor();
            reader = new BufferedReader(new InputStreamReader(p.getInputStream(),"GBK"));
            String value;
            while ((value=reader.readLine())!=null){
                System.out.println(value);
            }
            reader.close();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }
}
