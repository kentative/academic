package ksl.academic.algorithm.epi.binary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ksl.academic.algorithm.Utility;

/**
 * Some are adopted from:
 * http://graphics.stanford.edu/~seander/bithacks.html
 * <p>
 * Check the bit at a specific position:
 * - create a position mask (1 << position)
 * - apply bitwise AND check if it's == 1
 * or shift # of position and check last bit
 * <p>
 * Toggle a bit at a specific position
 * - create a position mask (1 << position)
 * - apply XOR
 *
 * @author Kent
 */
public class Binary {

    private static final Logger logger = LoggerFactory.getLogger(Parity.class);

    public static void main(String[] args) {
        logger.info(bitSwap(4099l, 13, 0));
        logger.info(bitAt(256, 8));
        logger.info(String.valueOf(toggleBitAt(128, 3)));


        int value = 11230;
        logger.info("Reverse Starts");
        logger.info(Utility.toBinaryString(value));
        logger.info(Utility.toBinaryString(reverse(value)));
        logger.info(Utility.toBinaryString(Integer.reverse(value)));
        logger.info("Reverse Ends");

        System.out.println(isOppositeSign(3, -4));
        System.out.println(isPowerOf2(-128));
        System.out.println(negate(20));

        logger.info(bitAt(128, 7));
        logger.info("Set bit at 3");
        logger.info(Utility.toBinaryString(setBitAt(1, 2, 0)));


        logger.info("Setting  " + Utility.toBinaryString(toggleBitAt(0, 3)));
        logger.info("Clearing " + Utility.toBinaryString(toggleBitAt(1, 2)));

        System.out.println(nextPowerOf2(1));
    }

    /**
     * Returns a power of two size for the given target capacity.
     */
    static final int nextPowerOf2(int value) {

        int number = value - 1; // round down - to simplify case
        // fill with all 1s up to power of 2
        int n = -1 >>> Integer.numberOfLeadingZeros(number);

        // 0 000 0000 0000 0000 0000 0000 0000 1111 + 1 = 16
        return (n < 0) ? 1 : (n >= Integer.MAX_VALUE) ? Integer.MAX_VALUE : n + 1;
    }


    /**
     * Reverse x, using the obvious approach, handling negative value
     *
     * @param x - the value to be reversed
     * @return
     */
    static int reverse(int x) {

        boolean isNegative = false;
        if (x < 0) {
            x = ~x;
            isNegative = true;
        }

        int s = Integer.BYTES * 8;
        int r = 0;

        while (x != 0) {
            r <<= 1;     // shift left
            r |= x & 1;  // takes the last bit of x into r
            x >>= 1;     // shift right
            s--;
        }
        r <<= s;  // shift when x's highest bits are zero

        if (isNegative) {
            return ~r;
        }
        return r;
    }

    static boolean isOppositeSign(int x, int y) {
        return (x ^ y) > 0;
    }

    static boolean isPowerOf2(int x) {
        return (x & (x - 1)) == 0;
    }

    /**
     * Getting the bit at position i
     *
     * @param x - the integer (base 10)
     * @param i - the binary position
     * @return
     */
    static String bitAt(int x, int i) {
        return String.valueOf(x >> i & 1);
    }

    /**
     * Toggle the bit at position i
     *
     * @param x - the integer (base 10)
     * @param i - the binary position
     * @return the integer with its binary value modified
     */
    static int toggleBitAt(int x, int i) {
        return x ^= (1 << i);
    }

    /**
     * Set the bit at position i
     *
     * @param x    - the integer (base 10)
     * @param i    - the binary position
     * @param flag - 0 to clear, 1 to set
     * @return the integer with its binary value modified
     */
    static int setBitAt(int x, int i, int flag) {
        // 1. (-flag ^ x) = create a mask to set the value of all bits to the value of the flag
        // 2. & (1 << i) = create a position mask to select on bit at this position
        // 3. apply bit value at position to with XOR
        return x ^ (-flag ^ x) & (1 << i);
    }

    static int negate(int x) {
        return (x ^ -1) + 1;
    }


    /**
     * Given a 64-bit integer, swap the i and j bit
     *
     * @param x - the 64 bit integer
     * @param i - the bit to be swap with j
     * @param j - the bit to be swap with i
     * @return the new integer with the bit swapped
     */
    static String bitSwap(long x, int i, int j) {

        // if the bits are different at the place to be swapped
        logger.info("bit at i " + ((x >> i) & 1));
        logger.info("bit at j " + ((x >> j) & 1));

        if (((x >> i) & 1) != ((x >> j) & 1)) {
            x ^= (1L << i) | (1L << j);
        }
        return Utility.toBinaryString(x);

    }
}
