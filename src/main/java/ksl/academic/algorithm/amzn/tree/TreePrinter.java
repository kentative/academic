package ksl.academic.algorithm.amzn.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Tree printer class
 * 1. find max level
 * 2.
 *
 * @author Kent
 */
public class TreePrinter {


    /**
     * @param args
     */
    public static void main(String[] args) {
        //		│           ┌── 22
        //		│       ┌── 21
        //		│       │   └── 20
        //		│   ┌── 18
        //		│   │   │   ┌── 16
        //		│   │   └── 15
        //		│   │       └── 14
        //		└── 12
        //		    │       ┌── 10
        //		    │   ┌── 9
        //		    │   │   └── 8
        //		    └── 6
        //		        │   ┌── 4
        //		        └── 3
        //		            └── 2

        // Sorted balanced tree
        Node root = new Node(12);
        Node n6 = root.addLeft(6);
        Node n18 = root.addRight(18);

        Node n3 = n6.addLeft(3);
        n3.addLeft(2);
        n3.addRight(4);

        Node n9 = n6.addRight(9);
        n9.addLeft(8);
        n9.addRight(10);

        Node n15 = n18.addLeft(15);
        n15.addLeft(14);
        n15.addRight(16);

        Node n21 = n18.addRight(21);
        n21.addLeft(20);
        n21.addRight(22);

        System.out.println(HorizontalTreePrinter.print(root));
        VerticalTreePrinter.print(root);

    }

}
