package com.virtual_world.animal;

import com.virtual_world.*;
import com.virtual_world.plant.Grass;

import java.util.ArrayList;
import java.util.List;

public class Sheep extends Animal {
    RandomUtil randomUtil;

    public Sheep(Position position, World world) {
        super(AnimalType.SHEEP, position, world, 0);
        this.randomUtil = new RandomUtil();
    }


    @Override
    public void propagation() {
        List<Direction> availableDirections = Direction.getAll();
        boolean propagated = false;

        while (!propagated || availableDirections.isEmpty()) {
            Direction dir = randomUtil.getRandomDirection(availableDirections);
            availableDirections.remove(dir);

            Position newPos = position.createShifted(dir);

            if (positionValid(newPos) && !world.isOccupied(newPos)) {

                Sheep newSheep = new Sheep(newPos, world);
                world.addOrganism(newSheep);
                System.out.println("PROPAGATED");

                propagated = true;
            }
        }

    }

    @Override
    public void collision(Organism attacker) {
        super.collision(attacker);
    }

    private boolean positionValid(Position position) {
        return position.getX() >= 0 && position.getX() < world.getRows()
                && position.getY() >= 0 && position.getY() < world.getColumns();
    }


}

