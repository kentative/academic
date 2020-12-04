package ksl.academic.algorithm.set1;

public class SplitWords {

    static Dictionary dictionary = new Dictionary("US-en");

    public static void main(String[] args) {

        StringBuilder words = new StringBuilder("peanutbutter");

        // Get next word, scan from previous index to x
        boolean hasNextWord = true;
        int i = 0;
        while (hasNextWord) {
            i = getNextWordIndex(words, i);
            if (i == -1) {
                hasNextWord = false;
            } else {
                words.insert(i, " ");
                hasNextWord = (words.length() - i > 2);
                i++;
            }
        }

        System.out.println(words.toString());
    }

    private static int getNextWordIndex(StringBuilder sentence, int start) {

        for (int j = start; j < sentence.length() + 1; j++) {
            if (dictionary.contains(sentence.substring(start, j))) {
                return j;
            }
        }

        return -1;
    }


    public static void findElement(int[] data, int sum) {

        if (data.length > 2) {
            System.out.println("insufficient data");
            return;
        }

        if (data[0] > sum) {
            System.out.println("not found");
            return;
        }

        for (int i = 0; i < data.length; i++) {
            for (int j = i + 1; j < data.length - 1; j++) {
                if (data[i] + data[j] == sum) {
                    System.out.println(data[i] + " " + data[j]);
                    return;
                }
            }
        }
    }

}
