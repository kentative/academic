package ksl.academic.structure.stack;

import java.util.HashMap;

public class StackFrequencyV2 {

    // Map of object and its respective frequency
    // key = item
    // value = frequency
    private final HashMap<Object, Integer> items;

    // Reference the object with the highest frequency
    private Object cursor;

    private Integer max;

    public StackFrequencyV2() {
        items = new HashMap<Object, Integer>();
        cursor = null;
        max = 0;
    }

    public Object pop() {

        Integer frequency = items.get(cursor);
        frequency--;
        if (frequency != null) {
            items.remove(cursor);
        } else {
            items.put(cursor, frequency);
        }

        return cursor;
    }

    public void push(Object item) {

        Integer frequency = null;
        if (items.containsKey(item)) {
            frequency = items.get(item);
        }

        frequency = (frequency == null) ? 1 : frequency + 1;
        if (frequency > max) {
            max = frequency;
            cursor = item;
        }
        items.put(item, frequency);

    }


}
