package ksl.academic.algorithm.sim;


// https://www.interviewcake.com/question/java/which-appears-twice
public class AppearsTwice {


    public static void main(String[] args) {

        int[] numbers = {1, 2, 3, 4, 5, 6, 2};
        System.out.println(findDuplicate(numbers));
    }

    static int findDuplicate(int[] numbers) {

        int n = numbers.length;
        int actual = 0;
        for (int i = 0; i < n; i++) actual += numbers[i];

        // Expected value
        int last = n - 1;
        int expected = ((last + 1) * last) / 2;

        return actual - expected;

    }

}
