package com.virtual_world;

import com.virtual_world.exception.NoDirectionsAvailableException;

import java.util.List;
import java.util.Random;

public final class RandomUtil {
    private static final Random random = new Random();

    private RandomUtil() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static Direction getRandomDirection(List<Direction> availableDirections) {

        if (availableDirections.isEmpty()) {
            throw new NoDirectionsAvailableException("Cannot pick direction: the list of available directions is empty.");
        }
        int index = random.nextInt(availableDirections.size());
        return availableDirections.get(index);
    }


    public static boolean succeeds(int chanceInPercent) {
        return random.nextInt(100) < chanceInPercent;
    }

    public static Position getRandomPosition(int rows, int columns) {
        int randomX = random.nextInt(rows);
        int randomY = random.nextInt(columns);

        return new Position(randomX, randomY);
    }
}

//    public Direction getRandomDirection() {
//        return Direction.of(random.nextInt(4));
//    }
