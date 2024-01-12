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

        // Q3
        int[] i1 = { 1, 2, 3, 4, 5 }; // Ingen hop
        int[] i2 = { 1, 1, 3, 4, 5, 6, 8, 9 }; // En hop 1,1
        int[] i3 = { 1, 1, 3, 4, 5, 8, 8, 8 }; // Två hopar 1,1 och 8,8,8
        int[] i4 = { 5, 5, 4, 4, 3, 8, 8, 8 }; // Tre hopar 5,5 och 4,4 och 8,8,8
        int[] i5 = { 1, 1 }; // En hop
        int[][] is = { i1, i2, i3, i4, i5 };
        for (int[] arr : is) {
            System.out.println(countHop(arr));
        }

        // Q4
        int[][] m1 = { { 0, 1, 2 }, // true (t.ex. 1,2,3 se understrukna
                { 1, 2, 3 }, // finns flera andra t.ex. 2, 3, 5)
                { 3, 5, 2 } };
        int[][] m2 = { { 2, 4, 2 }, // false
                { 1, 3, 3 },
                { 3, 1, 2 } };
        int[][] m3 = { { 6, 8, 3, 9 }, // true (unik lösning)
                { 1, 3, 6, 7 },
                { 3, 7, 2, 6 },
                { 8, 1, 4, 2 } };

        int[][][] mats = { m1, m2, m3 };
        for (int[][] mat : mats) {
            System.out.println(hasInceasingSequance(mat));
        }
        String world = "01101001";
        System.out.println("0: "+world);
        for (int i = 1; i <= 4; i++) {
            world = updateWorld(world);
            System.out.println(""+i+": "+world);
        }
        // 0: 01101001
        // 1: 01110000
        // 2: 01010000
        // 3: 00100000
        // 4: 00000000
        
    }



    private static String updateWorld(String world) {
        int[] nsVals = getNeghbourSums(world.toCharArray());
        StringBuilder nextWorld = new StringBuilder("");
        for (int i = 0; i < nsVals.length; i++) {
            nextWorld.append(newCellstate(nsVals[i], world.charAt(i)));
        }
        return nextWorld.toString();
    }



    private static char newCellstate(int ns, char c) {
        switch (ns) {
            case 0:
                return '0';
            case 1:
                return c;
            case 2:
                if(c=='0') {return '1';}
                if(c=='1') {return '0';}
            default:
                return '-';
        }
    }


    private static int[] getNeghbourSums(char[] cs) {
        int[] ns = new int[cs.length];
        ns[0] = charToBinInt(cs[1]);
        for (int i = 1; i < ns.length-1; i++) {
            ns[i] = charToBinInt(cs[i-1]) + charToBinInt(cs[i+1]);
        }
        ns[cs.length-1] = charToBinInt(cs[cs.length-2]);
        return ns;
    }


    private static int charToBinInt(char c) {
        if (c=='0') {return 0;}
        if (c=='1'){return 1;}
        return -1;
    }



    private static boolean hasInceasingSequance(int[][] mat) {
        int prevInPosSeq = minValue(mat[0]); // if there is a sequance then the
                                             // minimum value of first row
                                             // will always be a valid first
                                             // number in the sequance
        boolean strictIncSeq = prevInPosSeq >= 0;
        for (int i = 1; i < mat.length; i++) {
            prevInPosSeq = smallestOfThatIsLargerThan(mat[i], prevInPosSeq);
            strictIncSeq = prevInPosSeq > 0; // if not possible to create
                                             // sequence method returns -1
        }
        return strictIncSeq;
    }

    private static int smallestOfThatIsLargerThan(int[] arr, int val) {
        int candidate = maxValue(arr) + 1; // would cause a problem if array
                                           // contains Integer.MAX_VALUE
        for (int i : arr) {
            if (i > val && i < candidate) {
                candidate = i;
            }
        }
        if (candidate == maxValue(arr) + 1) {
            return -1;
        }
        return candidate;
    }

    static int maxValue(int[] arr) {
        int max = arr[0];
        for (int i : arr) {
            max = Math.max(i, max);
        }
        return max;
    }

    static int minValue(int[] arr) {
        int min = arr[0];
        for (int i : arr) {
            min = Math.min(i, min);
        }
        return min;
    }

    private static int countHop(int[] arr) {
        int counter = 0;
        if (arr.length >= 2) {
            for (int i = 0; i < arr.length - 1; i++) {
                if (arr[i] == arr[i + 1]) {
                    counter++;
                    while (i < arr.length - 1 && arr[i] == arr[i + 1]) {
                        i++;
                    }
                }
            }
        }
        return counter;
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
