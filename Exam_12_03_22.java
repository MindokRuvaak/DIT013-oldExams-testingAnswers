import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Exam_12_03_22 {
    public static void main(String args[]) {
        // Q3, move
        int[] a1 = { 3, 1, 2 };
        int[] a2 = { 4, 3, 5, 4 };
        int[] a3 = { 7, 5 };
        int[] a4 = { 4, 6 };

        // System.out.println(move(a1, a2) == -3);
        // System.out.println(move(a3, a4) == 0);
        // System.out.println(move(a1, a4) == -4);
        // System.out.println(move(a3, a1) == 5);

        // Q4
        int[][] m1 = {
                { 1, 2 },
                { 3, 4 } };
        int[][] m2 = {
                { 1, 2, 6, -2 },
                { 7, -4, 1, 9 },
                { 9, 1, 3, 4 } };

        // printMatr(removeCols(m1, new int[] { 1 }));
        // printMatr(removeCols(m1, new int[] { 1, 2 }));
        // printMatr(removeCols(m1, new int[] { 5 }));
        // printMatr(removeCols(m2, new int[] { 9 }));
        // printMatr(removeCols(m2, new int[] { 6, 12, 15 }));

        String[][] inpts = { 
                { "", "" },             // true
                { "x", "x" },           // true
                { "xy", "xy" },         // true
                { "xy", "yx" },         // true
                { "xyz", "yxz" },       // false
                { "xyzv", "zvxy" },     // true
                { "xyzv", "vzxy" } };   // false
        
        // for (String[] s : inpts) {
        //     String s1 = s[0];
        //     String s2 = s[1];
        //     System.out.println(isRotation(s1,s2));
        // }

        
    }

    private static boolean isRotation(String s1, String s2) {
        return genRotations(s1).contains(s2);
    }

    private static List<String> genRotations(String str) {
        List<String> rots = new ArrayList<>();
        rots.add(str);
        for (int i = 0; i < str.length()-1; i++) {
            String tmp = rots.get(rots.size()-1);
            rots.add(tmp.substring(1) + tmp.charAt(0));
        }
        return rots;
    }

    private static void printMatr(int[][] matr) {
        List<String> printString = new ArrayList<>();
        for (int[] row : matr) {
            printString.add(Arrays.toString(row));
        }
        System.out.println(printString);
    }

    private static int[][] removeCols(int[][] matr, int[] arr) {
        return transpose(removeRows(transpose(matr), arr));
    }

    private static int[][] removeRows(int[][] matr, int[] arr) {
        int rowsToRemove = 0;
        for (int[] row : matr) {
            if (containsAny(row, arr)) {
                rowsToRemove++;
            }
        }
        if (rowsToRemove == matr.length) {
            return new int[][] { { Integer.MIN_VALUE } };
        }
        int[][] newMatr = new int[matr.length - rowsToRemove][matr[0].length];
        int count = 0;
        for (int[] row : matr) {
            if (!containsAny(row, arr)) {
                newMatr[count] = copy(row);
                count++;
            }
        }
        return newMatr;
    }

    private static int[] copy(int[] arr) {
        return Arrays.copyOf(arr, arr.length);
    }

    private static boolean containsAny(int[] arr, int[] vals) {
        for (int e : vals) {
            if (contains(arr, e)) {
                return true;
            }
        }
        return false;
    }

    private static boolean contains(int[] arr, int val) {
        for (int a : arr) {
            if (a == val) {
                return true;
            }
        }
        return false;
    }

    private static int[][] transpose(int[][] matr) {
        int[][] matrT = new int[matr[0].length][matr.length];
        for (int i = 0; i < matr.length; i++) {
            for (int j = 0; j < matr[0].length; j++) {
                matrT[j][i] = matr[i][j];
            }
        }
        return matrT;
    }

    private static int move(int[] a, int[] b) {
        int[] aMMM = minMidMax(a);
        int[] bMMM = minMidMax(b);
        if (aMMM[0] > bMMM[1]) {
            return aMMM[0];
        }
        if (bMMM[0] > aMMM[1]) {
            return -bMMM[0];
        }
        return 0;
    }

    private static int[] minMidMax(int[] arr) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int sum = 0;
        for (int e : arr) {
            if (e < min) {
                min = e;
            }
            if (e > max) {
                max = e;
            }
            sum += e;
        }
        return new int[] { min, sum / arr.length, max };
    }
}
