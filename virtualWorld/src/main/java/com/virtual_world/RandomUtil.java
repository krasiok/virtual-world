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

//    int propagationPercentage = 5;

    public boolean chanceForPropagation(int chance) {
        return random.nextInt(100) <= chance; // in %
    }

    public boolean chance(int chanceInPercent){
        return random.nextInt(100) <= chanceInPercent;
    }
}
