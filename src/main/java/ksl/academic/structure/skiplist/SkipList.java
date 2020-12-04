package ksl.academic.structure.skiplist;

import java.util.Random;

/**
 * based loosly on http://www.mathcs.emory.edu/~cheung/Courses/323/Syllabus/Map/skip-list-impl.html
 *
 * @author Kent
 * @See ConcurrentSkipListMap
 */
public class SkipList<K extends Comparable<K>, V> {

    private static final Random R = new Random();

    private final EntrySet<K, V> root;
    private final int size;
    private final int height;


    public SkipList() {

        this.root = null;
        this.size = 0;
        this.height = 0;

    }


    public void insert(K key, V value) {

        EntrySet<K, V> entry = elementAt(key);

        if (key.equals(entry.key)) {
            entry.value = value;
        } else {
            EntrySet<K, V> newEntry = new EntrySet<K, V>(key, value);
            int level = 0;
            while (R.nextDouble() < 0.5d) {
                if (level >= height) {
                    // create a new top layer

                }
            }

            // TODO
        }


    }


    public void clear() {

    }


    /**
     * https://en.wikipedia.org/wiki/Skip_list
     * <p>
     * Returns null if element is not found
     *
     * @param key
     * @return
     */
    public EntrySet<K, V> elementAt(K key) {

        EntrySet<K, V> entry = root;
        while (true) {
            // Search right
            while (entry.right != null && key.compareTo(entry.right.key) > 0) {
                entry = entry.right;
            }

            // Search down
            if (entry.down != null) entry = entry.down;
            else break;

        }
        return entry;
    }

    private static class EntrySet<K extends Comparable<K>, V> {

        K key;
        V value;
        EntrySet<K, V> right;
        EntrySet<K, V> down;

        public EntrySet(K key, V value) {
            this.key = key;
            this.value = value;
            this.right = null;
            this.down = null;
        }
    }

}
