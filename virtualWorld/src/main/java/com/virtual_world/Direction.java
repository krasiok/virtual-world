package com.virtual_world;

import java.util.ArrayList;
import java.util.List;

public enum Direction {

    LEFT(-1, 0),
    RIGHT(1, 0),
    UP(0, -1),
    DOWN(0, 1);

    private int dx;
    private int dy;

    Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public static Direction of(int number) {
        number = number % 4;

        return Direction.values()[number];
    }

    public static List<Direction> getAll() {
        List<Direction> directions = new ArrayList<>();
        for (Direction direction : Direction.values()) {
            directions.add(direction);
        }
        return directions;
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }
}
