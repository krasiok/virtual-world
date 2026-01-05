package com.virtual_world.plant;

import com.virtual_world.*;

import java.awt.*;
import java.util.List;
import java.util.Random;

public abstract class Plant extends Organism {
    PlantType plantType;
//    private final RandomUtil randomUtil = new RandomUtil();
    private Position previousPosition;
    private int strength;

    public Plant(PlantType plantType, Position position, World world, int age){
        super(position,world,age);
        this.plantType = plantType;
        this.strength = plantType.getStrength();
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
        if(!RandomUtil.succeeds(1)) return;
        List<Direction> availableDirections = Direction.getAll();
        boolean propagated = false;

        while (!propagated && !availableDirections.isEmpty()) {
            Direction dir = RandomUtil.getRandomDirection(availableDirections);
            availableDirections.remove(dir);

            Position newPos = position.createShifted(dir);

            if (positionValid(newPos) && world.isOccupied(newPos)) {

                Plant baby = createChild(newPos);
                world.addOrganism(baby);

//                Organism other = world.getOrganismAtExcluding(newPos,baby);
//                if(other != null){
//                    baby.collision(other,false);
//                }

                propagated = true;
            }
        }
    }

    public abstract Plant createChild(Position pos);

    private boolean positionValid(Position position) {
        return position.getX() >= 0 && position.getX() < world.getRows()
                && position.getY() >= 0 && position.getY() < world.getColumns();
    }
    public PlantType getPlantType() {
        return plantType;
    }

    @Override
    public int getStrength() {
        return strength;
    }

    @Override
    public void setStrength(int strength) {
        this.strength = strength;
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
        return plantType.hasSpecialDefence();
    }

    @Override
    public Position getPreviousPosition() {
        return previousPosition;
    }
}
