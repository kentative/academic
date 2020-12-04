package ksl.academic.structure.queue;

import java.util.LinkedList;
import java.util.List;

// Add to the end of the list and remove from the front
public class Queue<T> {

    public T data;
    public List<T> list;

    public Queue() {
        list = new LinkedList<>();
    }

    public void add(T data) {
        if (data != null) list.add(data);
    }

    public T remove() {
        return (isEmpty()) ? null : list.remove(0);
    }

    public T peek() {
        return (isEmpty()) ? null : list.get(0);
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public static void main(String[] args) {

        Queue<String> q = new Queue<>();
        for (int i = 0; i < 10; i++) {
            q.add("item" + i);
        }

        for (int i = 0; i < 10; i++) {
            System.out.println(q.remove());
        }
    }

}
