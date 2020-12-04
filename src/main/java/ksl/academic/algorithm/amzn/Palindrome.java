package ksl.academic.algorithm.amzn;

public class Palindrome {


    public static void main(String[] args) {
        System.out.println(isPalindrome("aluela"));

        System.out.println(isPalindromeNumber(1005001));
    }

    private static boolean isPalindrome(String text) {

        char[] buffer = text.toCharArray();
        int n = buffer.length;
        for (int i = 0, j = n - 1; i <= j; i++, j--) {
            if (buffer[i] != buffer[j]) return false;
        }

        return true;
    }

    private static boolean isPalindromeNumber(int number) {
        return isPalindrome(String.valueOf(number));
    }
}
