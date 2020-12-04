package ksl.academic.algorithm.epi.array;

public class MaxDifference {

    public static void main(String[] args) {
        Coord[] coords = {
                new Coord(0, 0, 0),
                new Coord(0, 5, 0),
                new Coord(0, 2, 0),
                new Coord(0, 7, 0),
        };

        System.out.println(capacity(coords));
        System.out.println(findBattery(coords));
    }

    static int capacity(Coord[] coords) {

        // need at least 2 coordinates
        if (coords.length < 2)
            return -1;

        int n = coords.length;
        int capacity = -getHeight(coords[0], coords[1]);
        int battery = capacity;

        // current and next
        for (int i = 0; i < n - 1; i++) {
            int h = getHeight(coords[i], coords[i + 1]);
            battery += h;
            if (battery < 0) {
                capacity += (-1 * battery);
                battery = 0;
            }
        }

        return capacity;
    }

    static int getHeight(Coord c1, Coord c2) {
        return (c1.y - c2.y);
    } // 8/16/2018 12:23 PM


    static int findBattery(Coord[] coords) {

        int min = 0;
        int capacity = 0;

        for (int i = 0; i < coords.length - 1; i++) {
            int h = Math.abs(coords[i].y - coords[i + 1].y);
            capacity = Math.max(capacity, h - min);
            min = Math.min(min, h);
        }

        return capacity;

    }
}

class Coord {
    int x;
    int y;
    int z;

    public Coord(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}


