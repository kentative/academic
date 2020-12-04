package ksl.academic.algorithm.leet;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class TimeParsing {

    public static void main(String[] args) {

        int[] digits1 = {1, 2, 3, 4};
        int[] digits2 = {5, 5, 5, 5};
        int[] digits3 = {0, 4, 0, 0};
        int[] digits4 = {2, 0, 6, 6};

        long time = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            System.out.println(largestTimeFromDigits1(digits1));
            System.out.println(largestTimeFromDigits1(digits2));
            System.out.println(largestTimeFromDigits1(digits3));
            System.out.println(largestTimeFromDigits1(digits4));
        }
        System.out.println(System.currentTimeMillis() - time);

    }

    public static String largestTimeFromDigits(int[] A) {

        //      hh:mm
        // max: 23:59
        // 1. Search for largest digit left to right
        // 2. Find 2, find 1, find 0 - 
        //    2a. findH1, findH2
        // 3. Find minute <= 5
        // 4. Find minute <= 9
        // --too many cases, simpler to enumerate all possibilities

        int n = A.length;
        List<Integer> digits = new LinkedList<>();
        for (int i = 0; i < n; i++) digits.add(A[i]);

        String time = "";
        for (int h1 = 2; h1 >= 0; h1--) {
            for (int h2 = 9; h2 >= 0; h2--) {
                if (h1 == 2 && h2 >= 4) continue;
                for (int m1 = 5; m1 >= 0; m1--) {
                    for (int m2 = 9; m2 >= 0; m2--) {
                        time = parseTime(digits, new int[]{h1, h2, m1, m2});
                        if (!time.equals("")) {
                            return time;
                        }
                    }
                }
            }
        }
        return time;

    }

    private static String parseTime(List<Integer> digits, int[] maxTime) {

        List<Integer> tempDigits = new LinkedList<>(digits);
        char[] time = new char[5];
        try {
            time[0] = getTimeDigit(tempDigits, maxTime[0]);
            time[1] = getTimeDigit(tempDigits, maxTime[1]);
            time[2] = ':';
            time[3] = getTimeDigit(tempDigits, maxTime[2]);
            time[4] = getTimeDigit(tempDigits, maxTime[3]);
        } catch (Exception e) {
            return "";
        }
        return String.valueOf(time);
    }

    private static char getTimeDigit(List<Integer> digits, int max) {
        int i = max;
        if (digits.contains(i)) {
            digits.remove((Object) i);
            return (char) (i + '0');
        }
        throw new IllegalArgumentException("Not a valid time");
    }


    public static String largestTimeFromDigits1(int[] A) {

        int digitValue = 0;
        Arrays.sort(A);
        for (int a : A) digitValue = digitValue * 10 + a;

        int[] temp = new int[4];
        for (int h = 23; h >= 0; h--)
            for (int m = 59; m >= 0; m--) {
                temp[0] = h / 10;
                temp[1] = h % 10;
                temp[2] = m / 10;
                temp[3] = m % 10;

                Arrays.sort(temp);
                int maxDigitValue = 0;
                for (int b : temp) maxDigitValue = maxDigitValue * 10 + b;

                if (digitValue == maxDigitValue)
                    return (h / 10) + (h % 10) + ":" + (m / 10) + (m % 10);
            }
        return "";
    }
}
