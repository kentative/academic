package ksl.academic.structure.stack;

import java.util.ArrayList;
import java.util.List;

// Add last, remove last
public class Stack<T> {

    List<T> dataList;

    public Stack() {
        dataList = new ArrayList<>();
    }

    public void push(T data) {
        if (data != null) {
            dataList.add(data);
        }
    }

    public T pop() {

        if (isEmpty()) return null;
        int n = dataList.size() - 1;
        T data = dataList.get(n);
        dataList.remove(n);
        return data;
    }

    public boolean isEmpty() {
        return dataList.size() == 0;
    }

    public T peek() {
        if (isEmpty()) return null;
        return dataList.get(dataList.size() - 1);
    }


    public static void main(String[] args) {
        Stack<String> s = new Stack<>();
        for (int i = 0; i < 10; i++) {
            s.push("Item" + i);
        }

        for (int i = 0; i < 10; i++) {
            System.out.println(s.pop());
        }
    }
}
