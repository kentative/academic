package ksl.academic.algorithm.sim;

import java.util.Random;

import ksl.academic.algorithm.Utility;

public class Rand7 {
    public static void main(String[] args) {
        int size = 7;
        int[] bucket1 = new int[size];
        int[] bucket2 = new int[size];
        int[] bucket3 = new int[size];


        for (int i = 0; i < 1000000; i++) {
            bucket1[rand7() - 1]++;
            bucket2[r.nextInt(7)]++;
            bucket3[rand7a()]++;

        }

        System.out.println(deviation(bucket1));
        System.out.println(deviation(bucket2));
        System.out.println(deviation(bucket3));

        Utility.print(bucket1);
        Utility.print(bucket2);
        Utility.print(bucket3);
    }

    static int rand7() {
        int i;
        do {
            i = 5 * (rand5() - 1) + rand5();  // i is now uniformly random between 1 and 25
            // 0 - 0   1 2 3 4 5   (.2 * .2) * 5  = 0.2
            // 1 - 5   1 2 3 4 5    6  7  8  9 10 = 0.2
            // 2 - 10  1 2 3 4 5   11 12 13 14 15 = 0.2
            // 3 - 15  1 2 3 4 5   16 17 18 19 20 = 0.2
            // 4 - 20  1 2 3 4 5   21 22 23 24 25 = 0.2

        } while (i > 21);
        // i is now uniformly random between 1 and 21
        return i % 7 + 1;  // result is now uniformly random between 1 and 7
    }

    static int rand7a() {
        int sum = 0;
        for (int i = 0; i < 7; i++) {
            sum += rand5();
        }
        return sum % 7;
    }

    static Random r = new Random();

    static int rand5() {
        return r.nextInt(5) + 1;
    }

    /**
     * M = mean of Xi
     * <p>
     * deviation = sqrt(sum((xi - M)^2)/n)
     *
     * @param data
     * @return
     */
    private static double deviation(int[] data) {

        double mean = 0;
        for (int x : data) mean += x;
        mean = mean / data.length;

        double sqDiffSum = 0;
        for (int i = 0; i < data.length; i++) {
            sqDiffSum += Math.pow(data[i] - mean, 2);
        }
        return Math.sqrt(sqDiffSum / data.length);
    }


}
