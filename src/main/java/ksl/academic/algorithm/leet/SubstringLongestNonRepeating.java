package ksl.academic.algorithm.leet;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;


/**
 * LeetCode #3 Longest Substring Without Repeating Characters
 *
 * @author Kent
 */
public class SubstringLongestNonRepeating {

    public static int lengthOfLongestSubstring(String s) {

        char[] buffer = s.toCharArray();
        char last = buffer[0];

        int max = 0, len = 0;
        for (int i = 1; i < buffer.length; i++) {
            len++;
            if (last == buffer[i]) {
                if (len > max) max = len;
                len = 0;
            }
            last = buffer[i];
        }


        return max;
    }


    public static int naive(String s) {
        int n = s.length();
        int max = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i; j <= n; j++) {
                if (isUnique(s.substring(i, j))) max = Math.max(max, j - i);
            }
        }
        return max;
    }

    /**
     * This approach optimizes the naive implement by skipping some substring
     * Start by identifying all possible substrings:
     * 1. let S = all substrings in s[i, j)
     * 2. for each substring in S, it has unique char, track length
     * 3. track max length
     * <p>
     * if s[i, 2) has a duplicate char,
     * - skip all subset with [i, 2+)
     * - remaining subset will also have duplicate...
     * - skip this sequence by increment i
     * example:
     * "abcabcbb"
     * S(i=0): {a, ab, abc, abca}, max = 3
     * S(i=1): {b, bc, bca, bcab}, max = 3
     * S(i=2): {c, ca, cab, cabc}, max = 3
     * S(i=3): {a, ab, abc, abcb}, max = 3
     * ...
     *
     * @param s - the string
     * @return the max length of a substring without repeating characters
     */
    public static int slidingWindow(String s) {

        char[] buffer = s.toCharArray();
        Set<Character> charSet = new HashSet<>();

        int max = 0;
        for (int i = 0; i < buffer.length; i++) {
            for (int j = i + 1; j <= buffer.length; j++) {

                String substring = s.substring(i, j);
                if (isUnique(substring)) {
                    max = Math.max(max, substring.length());
                } else {
                    break;
                }
            }
        }
        return max;
    }

    /**
     * This approach uses a sliding window.
     * Start by identifying all possible substrings:
     * 1. let S = all substrings in s[i, j)
     * 2. for each substring in S, it has unique char, track length
     * 3. track max length
     * <p>
     * if s[i, 2) has a duplicate char,
     * - skip all subset with [i, 2+)
     * - remaining subset will also have duplicate...
     * - skip this sequence by increment i
     * example:
     * "abcabcbb"
     * S(i=0): {a, ab, abc, abca}, max = 3
     * S(i=1): {bca, bcab}, max = 3
     * S(i=2): {cab, cabc}, max = 3
     * S(i=3): {abc, abcb}, max = 3
     * ...
     * "aab"
     * {a, }, {a, ab}
     *
     * @param s - the string
     * @return the max length of a substring without repeating characters
     */
    public static int slidingWindow1(String s) {

        if (s == null || s.length() == 0) return 0;

        char[] buffer = s.toCharArray();
        Set<Character> charSet = new HashSet<>();

        int n = buffer.length;
        int max = 1, i = 0, j = 0;
        while (i < n && j < n) {
            if (!charSet.contains(buffer[j])) {
                charSet.add(buffer[j++]);
                max = Math.max(max, charSet.size());
            } else {
                charSet.remove(buffer[i++]);
            }
        }

        return Math.max(max, charSet.size());
    }

    public static int slidingWindow2(String s) {

        if (s == null || s.length() == 0) return 0;

        char[] buffer = s.toCharArray();
        Set<Character> charSet = new HashSet<>();

        int n = buffer.length;
        int max = 1, i = 0, j = 0;
        while (i < n && j < n) {

            if (charSet.contains(buffer[j])) {
                charSet.remove(buffer[i++]);
            } else {
                charSet.add(buffer[j++]);
                max = Math.max(max, charSet.size());
            }
        }

        return max;
    }


    private static boolean isUnique(String substring) {
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < substring.length(); i++) {
            Character c = substring.charAt(i);
            if (set.contains(c)) return false;
            set.add(c);

        }
        return true;
    }

    @Test
    public void test1() {
        Assert.assertEquals(3, slidingWindow1("abcabcbb"));
        Assert.assertEquals(1, slidingWindow1("bbbbb"));
        Assert.assertEquals(3, slidingWindow1("pwwkew"));
        Assert.assertEquals(1, slidingWindow1(" "));
        Assert.assertEquals(2, slidingWindow1("au"));
        Assert.assertEquals(2, slidingWindow1("aab"));
        Assert.assertEquals(5, slidingWindow1("qrsvbspk"));
    }

    @Test
    public void test2() {
        Assert.assertEquals(3, slidingWindow2("abcabcbb"));
        Assert.assertEquals(1, slidingWindow2("bbbbb"));
        Assert.assertEquals(3, slidingWindow2("pwwkew"));
        Assert.assertEquals(1, slidingWindow2(" "));
        Assert.assertEquals(2, slidingWindow2("au"));
        Assert.assertEquals(2, slidingWindow2("aab"));
        Assert.assertEquals(5, slidingWindow2("qrsvbspk"));
    }


}
