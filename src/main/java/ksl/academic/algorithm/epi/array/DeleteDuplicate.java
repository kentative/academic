package ksl.academic.algorithm.epi.array;

import ksl.academic.algorithm.Utility;

// TODO: Auto-generated Javadoc

/**
 * The Class DeleteDuplicate.
 */
public class DeleteDuplicate {

    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {

        int[] data = {2, 3, 3, 4, 4, 5, 5, 7, 7};

        System.out.println(removeDuplicate(data));
        Utility.print(data);

        int a = -9;
        System.out.println(-a);
    }

    /**
     * Removes the duplicate.
     *
     * @param data the data
     * @return the int
     */
    static int removeDuplicate(int[] data) {

        int write = 0;
        for (int i = 1; i < data.length - 1; i++) {

            // next data is not the same
            if (data[write] != data[i]) {
                data[++write] = data[i];
            }
        }
        return write;
    }
}
