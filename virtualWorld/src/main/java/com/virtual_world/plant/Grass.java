package com.virtual_world.plant;

import com.virtual_world.*;
import com.virtual_world.animal.AnimalType;

import java.util.List;
import java.util.Optional;

public class Grass extends Plant {
    private RandomUtil randomUtil;

    public Grass(Position position, World world) {
        super(PlantType.GRASS, position, world,0);
        this.randomUtil = new RandomUtil();
    }

    @Override
    public void action() {

//        if (shouldPropagate()) {
//            propagate();
//        }



        if(randomUtil.plantPropagation()) {
            System.out.println("PROPAGATION!");

            List<Direction> availableDirections = Direction.getAll();
            boolean propagated = false;

            while (!propagated && !availableDirections.isEmpty()) {
                Direction dir = randomUtil.getRandomDirection(availableDirections);
                availableDirections.remove(dir);

                Position newPos = position.createShifted(dir);

                if (positionValid(newPos) && !world.isOccupied(newPos)) {
                    // create a new Grass instance at the new position
                    Organism newOrg = new Grass(newPos, world);
                    world.addOrganism(newOrg);
                    // update the UI immediately if needed


                    propagated = true;
                }
            }
        }
        increaseAge();
    }

//    private void propagate() {
//
//        Optional<Position> position = getAvailablePosition();
//        position.ifPresent(p -> create(p));
//    }
//
//    private Optional<Position> getAvailablePosition() {
//        List<Direction> availableDirections = Direction.getAll();
//        boolean propagated = false;
//
//        while (!propagated && !availableDirections.isEmpty()) {
//            Direction dir = randomUtil.getRandomDirection(availableDirections);
//            availableDirections.remove(dir);
//
//            Position newPos = position.createShifted(dir);
//
//            if (positionValid(newPos) && !world.isOccupied(newPos)) {
//                return Optional.of(newPos);
//            }
//        }
//        return Optional.empty();
//    }


    private Organism create(Position pos) {
        return new Grass(pos, world);
    }

    private boolean positionValid(Position position) {
        return position.getX() >= 0 && position.getX() < world.getRows()
                && position.getY() >= 0 && position.getY() < world.getColumns();
    }
}
