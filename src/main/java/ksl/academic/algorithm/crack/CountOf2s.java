package ksl.academic.algorithm.crack;

public class CountOf2s {

    public static void main(String[] args) {
        int size = 123;
        System.out.println("count brute: " + size + " = " + countOf2(size));
        System.out.println("count fast:  " + size + " = " + count2sInRange(size));

    }

    static int countOf2(int n) {
        if (n < 2) return 0;

        int count = 0;
        for (int i = 2; i <= n; i++) {
            count += countNumber2(i);
        }
        return count;
    }

    private static int countNumber2(int n) {

        int count = 0;
        while (n > 0) {
            if (n % 10 == 2) count++;
            n /= 10;
        }
        return count;
    }

    private static int count2sInRange(int number) {

        int count = 0;
        int n = (int) Math.log10(number) + 1;
        for (int i = 0; i < n; i++) {
            count += countDigit(number, i);
        }
        return count;
    }

    // Uses this naming pattern: https://en.wikipedia.org/wiki/Positional_notation
    private static int countDigit(int number, int position) {

        int radix = 10;                             // radix or base
        int pVal = (int) Math.pow(radix, position); // the position value
        int digit = (number / pVal) % radix;

        int result = digit * position * (pVal / radix);

        // for each right-side value, there's a 2, +1 for this occurrence
        if (digit == 2) {
            System.out.println("right: " + (number % pVal));
            result += (number % pVal) + 1;
        }

        // Add all 2s for this position value (i.e., 200 to 299 = 100(2s))
        if (digit > 2) result += pVal;

        return result;
    }

}
