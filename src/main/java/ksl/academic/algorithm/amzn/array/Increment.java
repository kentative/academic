package ksl.academic.algorithm.amzn.array;

import java.util.Arrays;

public class Increment {

    public static void main(String[] args) {

        int[] number = {-1, 0, 0};

        number = new Increment().increment2(number);
        System.out.println(Arrays.toString(number));
    }

    /**
     * Get the last digit, set carry to 1, increment carry
     * Check carry and repeat if not 0,
     * Check overflow and create new array with a 1 if needed
     *
     * @param number
     * @return
     */
    public int[] increment(int[] number) {


        int base = 10;
        int n = number.length;

        int c = 1;
        for (int i = n - 1; i >= 0 && c > 0; i--) {
            int x = number[i] += c;
            number[i] = x % base;
            c = x / base;
        }

        if (c == 1) { // overflow
            number = new int[n + 1];
            number[0] = 1;
        }

        return number;
    }

    /**
     * Handles negative.
     * Check for sign using the first digit
     * Get the last digit, if negative, inverse the carry flag
     * if number is 0, need to carry a -1
     *
     * @param number
     * @return
     */
    public int[] increment2(int[] number) {

        int base = 10;
        boolean isNeg = false;
        if (number[0] < 0) {
            isNeg = true;
            number[0] *= -1;
        }

        int c = (isNeg) ? -1 : 1;
        for (int i = number.length - 1; i >= 0 && c != 0; i--) {
            if (isNeg) {
                if (number[i] == 0) {
                    number[i] = 9;
                    c = -1;
                } else {
                    number[i] += c;
                    c = 0;
                }
            } else {
                int x = number[i] += c;
                number[i] = x % base;
                c = x / base;
            }
        }

        if (isNeg) {
            for (int i = 0; i < number.length; i++) {
                if (number[i] > 0) {
                    number[i] *= -1;
                    break;
                }
            }
        }
        if (c != 0) { // overflow
            number = new int[number.length + 1];
            number[0] = 1;
        }

        return number;
    }

}
