package model;

import Enums.Direction;

public class Ship {
    private int i;
    private int j;
    private Direction direction;
    private int length;
    private int health;

    public Ship(int i, int j, Direction direction, int length) {
        this.i = i;
        this.j = j;
        this.direction = direction;
        this.length = length;
        this.health = length;
    }

    public boolean contains(int i, int j) {
        if (direction == Direction.VERTICAL) {
            for (int k = 0; k < length; k++) {
                if (this.i == i && (this.j + k) == j)
                    return true;
            }
        }
        if (direction == Direction.HORIZONTAL) {
            for (int k = 0; k < length; k++) {
                if (this.j == j && (this.i + k) == i)
                    return true;
            }
        }
        return false;
    }

    public boolean destroy(int i, int j) {
        if (contains(i, j)) health--;
        return health <= 0;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public Direction getDirection() {
        return direction;
    }

    public int getLength() {
        return length;
    }

    public int getHealth() {
        return health;
    }
}
