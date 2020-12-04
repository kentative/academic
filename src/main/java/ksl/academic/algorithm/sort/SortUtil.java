package ksl.academic.algorithm.sort;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SortUtil {

    private static final Logger logger = LoggerFactory.getLogger(SortUtil.class);

    public static void swap(int[] data, int x, int y) {
        int t = data[x];
        data[x] = data[y];
        data[y] = t;
    }

    public static void swap(boolean[] data, int x, int y) {

        if (data[x] == data[y]) return;
        data[x] = data[x] ^ data[y];
        data[y] = data[y] ^ data[x];
        data[x] = data[x] ^ data[y];
    }

    /**
     * @param data
     * @param x
     * @param y
     */
    public static void swapInPlace(int[] data, int x, int y) {

        if (data[x] == data[y]) return;
        data[x] = data[x] ^ data[y];
        data[y] = data[y] ^ data[x];
        data[x] = data[x] ^ data[y];
    }

    public static void swapInPlaceWithDebug(int[] data, int x, int y) {

        logger.info("swapping " + data[x] + " with " + data[y]);

        StringBuilder sb = new StringBuilder();
        for (int element : data) {
            sb.append(element + " ");
        }
        logger.info("    before: " + sb.toString());

        data[x] = data[x] ^ data[y];
        data[y] = data[y] ^ data[x];
        data[x] = data[x] ^ data[y];


        logger.info("after swapping " + data[x] + " / " + data[y]);

        sb = new StringBuilder();
        for (int element : data) {
            sb.append(element + " ");
        }
        logger.info("    after : " + sb.toString());
    }

}
