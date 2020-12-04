package ksl.academic.algorithm.leet;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import org.junit.Assert;
import org.junit.Test;


/**
 * Leetcode #5 Longest Palindromic Substring
 * <p>
 * Approach 1.
 * 1. Find all substrings
 * 2. Filter for palindrome
 * 3. Find max length of palindrome substrings
 * <p>
 * Approach 2. - reduce # of substrings using LCS
 *
 * @author Kent
 */
public class SubstringLongestPalindrome {

    public static String longestPalindrome2(String s) {

        // input validation
        Objects.requireNonNull(s);
        if (s.length() == 0) return "";

        // initialization
        char[] buffer = s.toCharArray();
        int n = buffer.length;

        // length, start, end
        int[] max = {0, 0, 0};

        // find all substrings using [i, j)
        for (int i = 0; i < n; i++) {
            for (int j = i; j <= n; j++) {
                if (isPalindrome(buffer, i, j)) {
                    int length = j - i;
                    if (length > max[0]) {
                        max[0] = length;
                        max[1] = i;
                        max[2] = j;
                    }
                }
            }
        }
        return s.substring(max[1], max[2]);
    }


    /**
     * Indicates if the buffer contains a palindrome
     *
     * @param buffer - the character buffer
     * @param start  - the start index
     * @param end    - the end index, must be less than or equals to the length
     * @return true if the buffer contains a palindrome, false otherwise
     */
    private static boolean isPalindrome(char[] buffer, int start, int end) {

        int m = (start + end) / 2;
        for (int i = start, j = end - 1; i < m; i++, j--) {
            if (buffer[i] != buffer[j]) return false;
        }

        return true;
    }

    public String longestPalindrome(String s) {

        // initialization
        char[] buffer = s.toCharArray();
        int n = buffer.length;
        if (n == 0) return "";

        // length, start, end
        int[] max = {0, 0, 0};

        // find all substrings using [i, j)
        for (int i = 0; i < n; i++) {
            int[] maxOdd = findPalindrome(buffer, i, i);
            int[] maxEven = findPalindrome(buffer, i, i + 1);
            int[] maxima = (maxOdd[0] > maxEven[0]) ? maxOdd : maxEven;
            if (maxima[0] > max[0]) {
                if (maxima[0] == n)
                    return s.substring(maxima[1], maxima[2]);
                max = maxima;
            }
        }
        return s.substring(max[1], max[2]);
    }

    /**
     * Returns the length, start (inclusive) and end (exclude) indices of a palindrome with
     * the specified buffer and center index.
     *
     * @param buffer - the character buffer
     * @param center - the center index
     * @return a tuple consisting of 3 values, {length, start (inclusive), end (exclusive) }
     * representing the palindrome substring
     */
    private static int[] findPalindrome(char[] buffer, int left, int right) {

        int length = buffer.length, i = left, j = right;
        while (i >= 0 && j < length && (buffer[i] == buffer[j])) {
            i--;
            j++;
        }
        return new int[]{j - i - 1, i + 1, j};
    }

    @Test
    public void testSolution() {
        Assert.assertEquals("", longestPalindrome(""));
        Assert.assertEquals("a", longestPalindrome("a"));
        Assert.assertEquals("bab", longestPalindrome("babad"));
        Assert.assertEquals("bb", longestPalindrome("cbbd"));
    }


    @Test
    public void testPalindrome() {
        Assert.assertTrue(isPalindrome("".toCharArray(), 0, 0));
        Assert.assertTrue(isPalindrome(" ".toCharArray(), 0, 1));
        Assert.assertTrue(isPalindrome("aba".toCharArray(), 0, 3));
        Assert.assertTrue(isPalindrome("abba".toCharArray(), 0, 4));
        Assert.assertFalse(isPalindrome("abad".toCharArray(), 0, 4));
        Assert.assertTrue(isPalindrome("abcba".toCharArray(), 0, 5));
        Assert.assertFalse(isPalindrome("abcba".toCharArray(), 1, 5));
    }

    @Test
    public void testFindPalindrome() {

        Assert.assertEquals(
                Arrays.toString(new int[]{3, 1, 4}),
                Arrays.toString(findPalindrome("babad".toCharArray(), 2, 2)));

        Assert.assertEquals(
                Arrays.toString(new int[]{4, 0, 4}),
                Arrays.toString(findPalindrome("abba".toCharArray(), 1, 2)));

        Assert.assertEquals(
                Arrays.toString(new int[]{5, 0, 5}),
                Arrays.toString(findPalindrome("abcba".toCharArray(), 2, 2)));
    }

}
