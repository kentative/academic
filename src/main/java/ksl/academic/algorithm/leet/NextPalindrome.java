package ksl.academic.algorithm.leet;

// So many cases
public class NextPalindrome {

    public static void main(String[] args) {
        long value = 123421;//Long.MAX_VALUE/2;

        long start = System.currentTimeMillis();
        System.out.println(findNext(value));
        System.out.println("Smart: " + (System.currentTimeMillis() - start));


        start = System.currentTimeMillis();
        value += 1;
        while (!isPali(value)) {
            value++;
        }
        System.out.println(value);
        System.out.println("Brute: " + (System.currentTimeMillis() - start));
    }


    /**
     * 4611686019106861164
     * Smart: 1
     * Brute: 22638
     *
     * @param number
     * @return
     */
    static boolean isPali(long number) {

        char[] digits = String.valueOf(number).toCharArray();
        int left, right;
        for (left = 0, right = digits.length - 1; left <= right; left++, right--) {
            if (digits[left] == digits[right]) {
                if (left == right || left == right - 1) { // middle
                    return true;
                }
            } else {
                return false;
            }
        }
        return false;
    }

    static long findNext(long number) {

        char[] digits = String.valueOf(number).toCharArray();

        // Special case
        boolean allNines = true;
        for (int i = 0; i < digits.length; i++) {
            if (digits[i] != '9') {
                allNines = false;
                break;
            }
        }
        if (allNines) {
            // 9, 99, 999, ..
            return number + 2;
        }

        boolean isModified = false;
        int left, right;
        for (left = 0, right = digits.length - 1; left <= right; left++, right--) {
            if (digits[left] == digits[right]) {
                if (left == right || left == right - 1) { // middle
                    if (!isModified) {                   // from test, only find next if we didn't already increase
                        // already a palindrome, need to find next one
                        addOutward(digits, left, right);
                    }
                }

                // Not a palindrome to begin with, make it one and return
            } else if (digits[left] < digits[right]) {
                // make right digit = left, then increase by 10
                digits[right] = digits[left];
                increment(digits, right - 1, 1);
                if (left == right - 1) {
                    // To handle: 123 421
                    digits[right] = digits[left];
                }
                isModified = true;
            } else {
                digits[right] = digits[left];
                isModified = true;
            }
        }
        return Long.parseLong(String.valueOf(digits));

    }

    // dir = direction to move position when incrementing
    // allowing us to add digit outwards
    private static void increment(char[] digits, int pos, int dir) {
        int n = (dir < 0) ? 0 : digits.length;
        boolean carry = true;
        while (pos != n && carry) {
            int x = (digits[pos] - '0') + 1;
            carry = x == 10;
            digits[pos] = (char) ((x % 10) + '0');
            pos += dir;
        }
    }

    private static void addOutward(char[] digits, int left, int right) {
        if (left == right) {
            if (digits[left] == '9') {        // from test
                increment(digits, left, -1);
                increment(digits, right + 1, 1); // from test
            } else {
                increment(digits, left, -1);
            }

        } else {
            increment(digits, left, -1);
            increment(digits, right, 1);

        }
    }
}
