package ksl.academic.structure.tree;


/**
 * The Class Node.
 */
public class Node<T> {

    /**
     * The red.
     */
    // Red = 1, Black = 0
    public boolean red;

    /**
     * The lower.
     */
    public int lower;

    /**
     * The upper.
     */
    public int upper;

    /**
     * The value.
     */
    public T data;

    /**
     * The parent.
     */
    public Node<T> parent;

    /**
     * The left.
     */
    public Node<T> left;

    /**
     * The right.
     */
    public Node<T> right;

    /**
     * Instantiates a new node.
     *
     * @param value the value
     */
    public Node(T value) {
        this.data = value;
    }

    /**
     * Instantiates a new node.
     *
     * @param value the value
     * @param isRed the is red
     */
    public Node(T value, boolean isRed) {
        this.data = value;
        this.red = isRed;
    }

    /**
     * Instantiates a new node.
     *
     * @param left  the left
     * @param right the right
     */
    public Node(Node<T> left, Node<T> right) {
        this.left = left;
        this.right = right;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString() {
        char color = (red) ? 'R' : 'B';
        return color + ":" + data;
    }

    /**
     * Rotate left.
     *
     * @param x the x
     */
    public void rotateLeft(Node<T> x) {
        Node<T> y = x.right;
        x.right = y.left;

        y.left.parent = x;
        y.parent = x.parent;

        if (x.parent == null) {
            x = y;
        } else if (x.parent.left == x) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }

        y.left = x;
        x.parent = y;

    }

}
