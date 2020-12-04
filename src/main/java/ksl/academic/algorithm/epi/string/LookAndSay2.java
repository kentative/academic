package ksl.academic.algorithm.epi.string;

public class LookAndSay2 {

    public static void main(String[] args) {

        System.out.println(say(6));
    }

    private static char[] say(int n) {

        // n = 3 {11, 21, 1211}
        if (n < 1) throw new IllegalArgumentException("k minimum value is 1");
        if (n == 1) return new char[]{'1'};
        else return describe(say(n - 1));

    }

    // describe sequence 11 = 21 = <counter><char[i]>
    private static char[] describe(char[] sequence) {

        // empty char --can't happen

        StringBuffer sb = new StringBuffer();
        char c = sequence[0];
        int counter = 1;
        for (int i = 1; i < sequence.length; i++) {
            if (sequence[i] != c) {
                sb.append(String.valueOf(counter) + c);
                counter = 0;
                c = sequence[i];
            }
            counter++;
        }

        // the last part
        sb.append(String.valueOf(counter) + c);
        return sb.toString().toCharArray();
    }
}
