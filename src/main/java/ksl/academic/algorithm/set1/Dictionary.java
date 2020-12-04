package ksl.academic.algorithm.set1;

import java.util.ArrayList;
import java.util.List;

public class Dictionary {

    private final List<String> data;

    public Dictionary(String string) {
        data = new ArrayList<>();
        data.add("pea");
        data.add("nut");
        data.add("butter");
    }

    public boolean contains(String substring) {
        System.out.println("checking: " + substring);
        return data.contains(substring);
    }

}
