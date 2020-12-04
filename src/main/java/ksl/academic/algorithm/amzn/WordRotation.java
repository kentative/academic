package ksl.academic.algorithm.amzn;

/**
 * Given two words, find if second word is the round rotation of first word.
 * For example: abc, cab
 * return 1
 * Example2: ab, aa
 * return -1
 */
public class WordRotation {

    public static void main(String... args) {

        System.out.println(isRotated("abc", "cab"));
        System.out.println(isRotated("aabca", "caaab"));

        System.out.println();

        System.out.println(isRotated2("abc", "cab"));
        System.out.println(isRotated2("aabca", "caaab"));
        System.out.println(isRotated2("HelloWorld!", "WorldHe!llo"));

        System.out.println();
        System.out.println(isRotated3("abc", "cab"));
        System.out.println(isRotated3("abc", "acb"));
        System.out.println(isRotated3("aabca", "caaab"));
        System.out.println(isRotated3("HelloWorld!", "WorldHe!llo"));

    }

    /**
     * Brute force
     */
    private static boolean isRotated(String s1, String s2) {

        char[] buf1 = s1.toCharArray();
        char[] buf2 = s2.toCharArray();

        int length = buf1.length;
        if (buf2.length != length) return false;

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (buf1[i] == buf2[j]) {
                    int k = 0;
                    while (k < length && buf1[k] == buf2[(j + k) % length]) {
                        k++;
                    }
                    if (k == length) return true;
                }
            }
        }
        return false;
    }

    private static boolean isRotated3(String s1, String s2) {

        if (s1 == null || s2 == null) return false;

        int n = s1.length();
        if (s2.length() != n) return false;

        char[] buf1 = s1.toCharArray();
        char[] buf2 = s2.toCharArray();

        int k = 0;
        for (int i = 0; i < n; i++) {
            if (buf1[i] == buf2[k]) {
                while (k < n && buf1[i % n] == buf2[k]) {
                    i++;
                    k++;
                }
                return (k == n);
            }
        }

        return false;
    }

    /*
     * clever
     */
    private static boolean isRotated2(String s1, String s2) {
        if (s1.length() != s2.length()) return false;

        String s3 = s1.concat(s1);
        return s3.contains(s2);
    }

}
