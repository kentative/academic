package ksl.academic.algorithm.crack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.google.common.base.Preconditions;

import ksl.academic.algorithm.Utility;

public class FindAlphaNumericSeq {

    public static void main(String[] args) {
//		char[] data = { 'A', '1'};
        char[] data = {'A', '1', 'A', '1', 'A', 'A', 'A', '1', '1', '2'};
//		char[] data = { 'A', '1', 'C', '2', 'D', 'E', 'F', '4', '5', '1' };

        Utility.print(findSequence(data));
    }

    static char[] findSequence(char[] data) {
        Preconditions.checkNotNull(data);

        // Initialization
        Map<Integer, Integer> deltaMap = new HashMap<>(data.length);
        Sequence max = new Sequence();
        int delta = 0;
        deltaMap.put(delta, -1);

        for (int i = 0; i < data.length; i++) {
            if (isDigit(data[i])) delta++;
            else delta--;

            System.out.println("delta " + delta);
            if (deltaMap.containsKey(delta)) {
                Integer first = deltaMap.get(delta);
                int length = i - first;
                if (length > max.getLength()) {
                    max.setFirst(first);
                    max.setLast(i);
                    max.setLength(length);
                }
            } else {
                deltaMap.put(delta, i);
            }
        }
        // Arrays.copyOfRange(inclusive, exclusive)
        return Arrays.copyOfRange(data, max.getFirst() + 1, max.getLast() + 1);
    }

    private static boolean isDigit(char c) {
        return c <= '9' && c >= '0';
    }

    static class Sequence {
        int first;
        int last;
        int length;

        public int getFirst() {
            return first;
        }

        public void setFirst(int first) {
            this.first = first;
        }

        public int getLast() {
            return last;
        }

        public void setLast(int last) {
            this.last = last;
        }

        public int getLength() {
            return length;
        }

        public void setLength(int length) {
            this.length = length;
        }
    }
}
