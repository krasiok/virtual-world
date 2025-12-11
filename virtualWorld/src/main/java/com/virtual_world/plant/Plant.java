package com.virtual_world.plant;

import com.virtual_world.*;
import com.virtual_world.animal.Wolf;

import java.awt.*;
import java.util.List;

public abstract class Plant extends Organism {
    PlantType plantType;
    private final RandomUtil randomUtil = new RandomUtil();
    private Position previousPosition;

    public Plant(PlantType plantType, Position position, World world, int age){
        super(position, world, age);
        this.plantType = plantType;
//        world.addPlant(this);
//        world.addOrganism(this);
    }

    @Override
    public void action(){
        propagation();
    };

    @Override
    public void collision(Organism attacker, boolean isCounterAttack) {
        world.removeOrganism(this, this.position);
    }

    @Override
    public void propagation() {
        if(!randomUtil.chanceForPropagation(1)) return;

        List<Direction> availableDirections = Direction.getAll();
        boolean propagated = false;

        while (!propagated && !availableDirections.isEmpty()) {
            Direction dir = randomUtil.getRandomDirection(availableDirections);
            availableDirections.remove(dir);

            Position newPos = position.createShifted(dir);

            if (isPositionInWorldBounds(newPos)) {

                Plant baby = createChild(newPos);
                world.addOrganism(baby);

                Organism other = world.getOrganismAtExcluding(newPos, baby);
                if(other != null){
                    baby.collision(other, false);
                }

                propagated = true;
            }
        }
    }

    public abstract Plant createChild(Position pos);
    public PlantType getPlantType() {
        return plantType;
    }

    @Override
    public int getInitiative() {
        return getPlantType().getInitiative();
    }

    @Override
    public Color getColor() {
       return plantType.getColor();
    }

    @Override
    public boolean hasSpecialDefence() {
        return false;
    }

    @Override
    public Position getPreviousPosition() {
        return previousPosition;
    }
}
