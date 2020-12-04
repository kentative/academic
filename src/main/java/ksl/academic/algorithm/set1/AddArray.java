package ksl.academic.algorithm.set1;

public class AddArray {


    public static void main(String[] args) {

        int[] data = {9, 8, 9, 9};

        int[] added = add(data);

        for (int i = 0; i < added.length; i++) {
            System.out.println(added[i]);
        }

    }

    private static int[] add(int[] data) {
        int x = data.length - 1;
        boolean overflow = false;
        do {

            if (x >= 0) {
                overflow = increment(data, x);
                x--;
            } else {
                data = new int[data.length + 1];
                data[0] = 1;
                overflow = false;
            }

        } while (overflow);

        return data;
    }

    private static boolean increment(int[] data, int i) {
        data[i] = (data[i] + 1) % 10;
        return (data[i] == 0);
    }

}
