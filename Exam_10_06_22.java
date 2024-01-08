import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Exam_10_06_22 {
    public static void main(String args[]) {
        // Q2
        MyImmutableClass o = new MyImmutableClass();
        System.out.println(o.getArr());
        System.out.println(Arrays.toString(o.getArr()));
        o.getArr()[0] = 0;
        
        System.out.println(o.getArr());
        System.out.println(Arrays.toString(o.getArr()));

    }

    static class MyImmutableClass {
        private final int[] arr;
        private final String str;

        public MyImmutableClass() {
            arr = new int[] { 1, 2, 3 };
            str = "abc";
        }

        public int[] getArr() {
            return arr;
        }

        public String getStr() {
            return str;
        }
    }

}
