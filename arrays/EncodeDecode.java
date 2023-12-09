package arrays;

import java.util.Arrays;
import java.util.List;

public class EncodeDecode {

    // Most maintainable code
    public String encode(List<String> strs) {
        return String.join(":;", strs);
    }

    public List<String> decode(String str) {
        return Arrays.asList(str.split(":;"));
    }

    public static void test() {
        EncodeDecode e = new EncodeDecode();
        System.out.println(Arrays.asList("apple", "banana", "orange", "grape"));
    }
}
