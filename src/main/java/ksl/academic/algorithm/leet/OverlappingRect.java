package ksl.academic.algorithm.leet;

public class OverlappingRect {

    public static void main(String[] args) {

        // Rectangle A
        Point p1A = new Point(0, 0);
        Point p2A = new Point(10, 10);

        // Rectangle B
        Point p1B = new Point(11, 11);
        Point p2B = new Point(12, 12);

        System.out.println(findArea(p1A, p2A, p1B, p2B));
    }


    private static int findArea(Point minA, Point maxA, Point minB, Point maxB) {

        int width = (Math.min(maxA.x, maxB.x) - Math.max(minA.x, minB.x));
        int height = (Math.min(maxA.y, maxB.y) - Math.max(minA.y, minB.y));
        if (width < 0 || height < 0) return 0;
        return width * height;
    }


    static class Point {

        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;

        }

    }
}
