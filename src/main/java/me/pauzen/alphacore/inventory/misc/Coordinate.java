/*
 *  Created by Filip P. on 3/2/15 1:00 AM.
 */

package me.pauzen.alphacore.inventory.misc;

public class Coordinate {

    private int x, y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Coordinate coordinate(int x, int y) {
        return new Coordinate(x, y);
    }

    public static Coordinate fromSlot(int inventorySlot, int width) {
        int x = Math.max(0, (inventorySlot) % width);
        int y = Math.max(0, inventorySlot / width);
        return new Coordinate(x, y);
    }

    public static int asSlot(int x, int y, int width) {
        return y * width + x;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coordinate that = (Coordinate) o;

        if (x != that.x) return false;
        if (y != that.y) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    public int toSlot(int width) {
        return asSlot(x, y, width);
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public static enum Direction {
        UP(0, 1),
        DOWN(0, -1),
        LEFT(-1, 0),
        RIGHT(1, 0),
        UP_LEFT(-1, 1),
        DOWN_LEFT(-1, -1),
        UP_RIGHT(1, 1),
        DOWN_RIGHT(1, -1);

        private int addendX, addendY;

        Direction(int addendX, int addendY) {
            this.addendX = addendX;
            this.addendY = addendY;
        }

        public Coordinate getRelative(Coordinate coordinate) {
            return new Coordinate(coordinate.getX() + addendX, coordinate.getY() + addendY);
        }
    }
}
