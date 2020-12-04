package ksl.academic.algorithm.epi.binary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RectangleIntersect {

    private static final Logger logger = LoggerFactory.getLogger(RectangleIntersect.class);

    public static void main(String[] args) {

        Rectangle r = new Rectangle(5, 5, 4, 4);
        Rectangle s = new Rectangle(0, 0, 10, 10);

        logger.info(String.valueOf(intersect(r, s)));

    }

    static Rectangle intersect(Rectangle r, Rectangle s) {
        if (hasIntersect(r, s)) {
            int x = max(r.x, s.x);
            int y = max(r.y, s.y);
            int w = min(r.x + r.w, s.x + s.w) - max(r.x, s.x);
            int h = min(r.y + r.h, s.y + s.h) - max(r.y, s.y);

            return new Rectangle(x, y, w, h);

        } else {
            return new Rectangle(0, 0, -1, -1);
        }
    }

    static boolean hasIntersect(Rectangle r, Rectangle s) {

        logger.info("p1: " + (r.x <= s.x + s.w));
        logger.info("p2: " + (r.x + r.w >= s.x));
        logger.info("p3: " + (r.y <= s.y + s.h));
        logger.info("p4: " + (r.y + r.h >= s.y));

        return
                r.x <= s.x + s.w && r.x + r.w >= s.x &&
                        r.y <= s.y + s.h && r.y + r.h >= s.y;
    }

    static int min(int x, int y) {
        return (x < y) ? x : y;
    }

    static int max(int x, int y) {
        return (x > y) ? x : y;
    }
}

class Rectangle {
    int x;
    int y;
    int w;
    int h;

    Rectangle(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public String toString() {
        return "(" + x + ", " + y + ", " + w + ", " + h + ")";
    }
}

