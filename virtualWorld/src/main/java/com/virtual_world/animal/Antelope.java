package com.virtual_world.animal;

import com.virtual_world.*;

import java.util.List;

public class Antelope extends Animal {
    private final int MOVE_RANGE = 2;

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
        if(isCounterAttack && RandomUtil.succeeds(50)){
            if(isAnyNeighborPositionFree()){
                super.action();
            }
        }
        super.collision(attacker, isCounterAttack);
    }

    private boolean isAnyNeighborPositionFree(){
        List<Direction> availableDirections = Direction.getAll();

        while (!availableDirections.isEmpty()) {
            Direction randomDirection = RandomUtil.getRandomDirection(availableDirections);
            availableDirections.remove(randomDirection);
            Position newPosition = position.createShifted(randomDirection);
            if(!world.isOccupied(newPosition)){
                return true;
            }
        }
        return false;
    }

    @Override
    public Organism createChild(Position pos) {
        return new Antelope(pos,world);
    }

}
