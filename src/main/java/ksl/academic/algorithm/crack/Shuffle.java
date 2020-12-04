package ksl.academic.algorithm.crack;

import java.util.Random;

import ksl.academic.algorithm.Utility;

public class Shuffle {

    public static void main(String[] args) {
        Random r = new Random();

        int size = 10;
        int[] sum1 = new int[size];
        int[] sum2 = new int[size];
        for (int i = 0; i < 100000; i++) {
            sum1[r.nextInt(size)]++;
            sum2[(int) (Math.random() * size)]++;
        }

        int[] sum3 = {10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000};

        double deviation1 = deviation(sum1);
        double deviation2 = deviation(sum2);
        double deviation3 = deviation(sum3);

        Utility.print(sum1);
        Utility.print(sum2);

        System.out.println("mean 1 " + deviation1);
        System.out.println("mean 2 " + deviation2);
        System.out.println("mean 3 " + deviation3);

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
