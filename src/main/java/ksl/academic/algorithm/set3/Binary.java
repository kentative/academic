package ksl.academic.algorithm.set3;

public class Binary {

    public static void main(String[] args) {

        System.out.println(countBit(17));
        System.out.println(getHighOrderBit(33));
    }

    static int getHighOrderBit(int x) {
        for (int i = 31; i >= 0; i--) {
            if ((x >> i & 1) == 1) return i;
        }
        return -1;

    }

    static int countBit(int x) {
        int count = 0;
        while (x > 0) {
            if ((x & 1) == 1) {
                count++;
            }
            x = x >> 1;
        }
        return count;
    }
}
