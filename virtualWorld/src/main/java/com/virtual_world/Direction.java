package com.virtual_world;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Direction {

    LEFT(-1, 0),
    RIGHT(1, 0),
    UP(0, -1),
    DOWN(0, 1);

    private final int dx;
    private final int dy;

    Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public static List<Direction> getAll() {
        return new ArrayList<>(Arrays.asList(Direction.values()));
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }
}
