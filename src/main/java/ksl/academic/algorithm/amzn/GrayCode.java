package ksl.academic.algorithm.amzn;

/**
 * Given two hexadecimal numbers find if they can be consecutive in gray code
 * <p>
 * For example: 10001000, 10001001
 * return 1 since they are successive in gray code
 * <p>
 * Example2: 10001000, 10011001
 * return -1 since they are not successive in gray code.
 * <p>
 * Gray code is a code assigning to each of a contiguous set of integers,
 * or to each member of a circular list, a word of symbols such that no two code words are identical
 * and each two adjacent code words differ by exactly one symbol. These codes are
 * also known as single-distance codes, reflecting the Hamming distance of 1 between adjacent codes
 *
 * @author Kent
 */
public class GrayCode {

    public static void main(String... args) {
        System.out.println(isGrayCode("10001000", "10001001"));
        System.out.println(isGrayCode("10001000", "10011001"));
    }

    private static int isGrayCode(String hex1, String hex2) {

        int hDist = 0;
        int length = hex1.length();
        if (length != hex2.length()) return -1;

        // Hamming distance of 1
        for (int i = 0; i < length && hDist < 2; i++) {
            if (hex1.charAt(i) != hex2.charAt(i)) hDist++;
        }

        return (hDist == 1) ? 1 : -1;
    }

}
