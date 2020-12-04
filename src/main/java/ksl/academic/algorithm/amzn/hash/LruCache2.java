package ksl.academic.algorithm.amzn.hash;

import java.util.HashMap;
import java.util.Map;

public class LruCache2<K, V> {


    public static void main(String[] args) {
        LruCache2<Integer, String> cache = new LruCache2<>();

        cache.put(1, "Kent");
        cache.put(2, "Nadia");
        cache.put(3, "Kydan");
        cache.put(4, "Kaelyn");

        System.out.println(cache.get(1));
        System.out.println(cache.get(4));
        System.out.println(cache.get(3));
        System.out.println(cache.get(2));
        System.out.println(cache.getMostRecent());  // Nadia
        System.out.println(cache.getLeastRecent()); // Kent
        cache.remove(2);
        System.out.println(cache.getMostRecent());  // Kydan

    }


    private final Map<K, Node<V>> map;
    private Node<V> head;
    private Node<V> tail;

    public LruCache2() {

        map = new HashMap<>();
        head = null;
        tail = null;
    }

    public V get(K key) {

        if (map.containsKey(key)) {
            Node<V> node = map.get(key);
            moveToFront(node);
            return node.data;
        }

        return null;
    }

    public void put(K key, V value) {

        Node<V> node = new Node<>(value);
        map.put(key, node);
        moveToFront(node);

    }

    public V remove(K key) {
        if (map.containsKey(key)) {
            Node<V> node = map.remove(key);
            removeFromList(node);
            return node.data;
        }
        return null;

    }

    public V getMostRecent() {
        return head.data;
    }

    public V getLeastRecent() {
        return tail.data;
    }

    private void removeFromList(Node<V> node) {

        if (node == null) return;
        Node<V> left = node.prev, right = node.next;

        if (left != null) left.next = right;
        if (right != null) right.prev = left;
        if (node == tail) tail = node.prev;
        if (node == head) head = node.next;
    }

    private void insertToFront(Node<V> node) {
        if (node == null) return;

        if (head == null) {
            head = node;
            tail = node;
            return;
        }

        node.next = head;
        head.prev = node;
        head = node;

    }

    private void moveToFront(Node<V> node) {
        removeFromList(node);
        insertToFront(node);
    }

    static class Node<V> {
        V data;
        Node<V> next;
        Node<V> prev;

        public Node() {
            this(null);
        }

        public Node(V value) {
            this.data = value;
            this.next = null;
            this.prev = null;
        }
    }

}