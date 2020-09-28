import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class TestCharacter {
    public static void main(String[] args) throws UnsupportedEncodingException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        byte[] bytes = "你好啊哈哈哈哈哈拉拉happy".getBytes("GBK");
        System.out.println(Arrays.toString(bytes));
        String a="asd";
        System.out.println(a.getClass().toString());
        Object o = Class.forName("java.util.Date").getDeclaredConstructor().newInstance();
        System.out.println(o);
        for (int i = 0; i < 100; i++) {
            System.out.println((char) i);
        }
        StringBuilder builder=new StringBuilder();
        System.out.println(builder.toString().length());

    }
}
