package com.virtual_world;

import java.util.List;
import java.util.Random;

public class RandomUtil {
    private static final Random random = new Random();

    public Direction getRandomDirection() {
        return Direction.of(random.nextInt(4));
    }

    public Direction getRandomDirection(List<Direction> availableDirections) {

        if (availableDirections.isEmpty()) {
            throw new IllegalArgumentException("No available directions"); // TODO wlasny wyjatek
        }
        int index = (int) (Math.random() * availableDirections.size());
        return availableDirections.get(index);
    }

    int plantPropagationPercentage = 5;

    public boolean plantPropagation() {

        return random.nextInt(100) <= plantPropagationPercentage;
    }
}
