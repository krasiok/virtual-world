package com.virtual_world.animal;

import com.virtual_world.*;

import java.util.List;

public class Antelope extends Animal {
    private final int MOVE_RANGE = 2;
    RandomUtil randomUtil = new RandomUtil();
    public Antelope(Position position, World world){
        super(AnimalType.ANTELOPE,position,world,0);
    }

    @Override
    public void action() {
        for (int i=0; i<MOVE_RANGE; i++){
            super.action();
        }
    }

    @Override
    public void collision(Organism attacker, boolean isCounterAttack) {
        if(isCounterAttack && randomUtil.chance(50)){
            System.out.println("Antylopa proboje uciekac");
            previousPosition = new Position(position.getX(), position.getY());
            List<Direction> availableDirections = Direction.getAll();

            while (!availableDirections.isEmpty()) {
                Direction randomDirection = randomUtil.getRandomDirection(availableDirections);
                availableDirections.remove(randomDirection);
                Position newPosition = position.createShifted(randomDirection);

                if (positionValid(newPosition) && canMoveTo(newPosition) && !world.isOccupied(newPosition)) {

                    world.getAllOccupiedPositions().remove(position);
                    setPosition(newPosition);
                    world.getAllOccupiedPositions().add(newPosition);
                    System.out.println("Udało się uciec");
                    return;
                }
            }
        }
        super.collision(attacker, isCounterAttack);
    }

    @Override
    public Animal createChild(Position pos) {
        return new Antelope(pos,world);
    }

    private boolean positionValid(Position position) {
        return position.getX() >= 0 && position.getX() < world.getRows()
                && position.getY() >= 0 && position.getY() < world.getColumns();
    }
}
