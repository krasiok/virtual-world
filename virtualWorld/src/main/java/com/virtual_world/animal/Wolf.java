package com.virtual_world.animal;

import com.virtual_world.*;
import com.virtual_world.plant.Grass;

import java.awt.*;
import java.util.List;


public class Wolf extends Animal {
    RandomUtil randomUtil;

    public Wolf(Position position, World world) {
        super(AnimalType.WOLF, position, world, 0);
        this.randomUtil = new RandomUtil();
    }

//    @Override
//    public void collision(Organism other) {
//        super.collision(other);
//    }


    @Override
    public void propagation() {
        List<Direction> availableDirections = Direction.getAll();
        boolean propagated = false;

        while (!propagated && !availableDirections.isEmpty()) {
            Direction dir = randomUtil.getRandomDirection(availableDirections);
            availableDirections.remove(dir);

            Position newPos = position.createShifted(dir);

            if (positionValid(newPos) && !world.isOccupied(newPos)) {

                Wolf newWolf = new Wolf(newPos, world);
                world.addOrganism(newWolf);



                propagated = true;
            }
        }
    }

    @Override
    public void collision(Organism attacker) {
        super.collision(attacker);
    }

    @Override
    public void action() {
        super.action();

        Position position = getNextPosition();
    }


    @Override
    protected Position getNextPosition() {
        // wlasna logika
        return null;
    }

    private boolean positionValid(Position position) {
        return position.getX() >= 0 && position.getX() < world.getRows()
                && position.getY() >= 0 && position.getY() < world.getColumns();
    }
}
