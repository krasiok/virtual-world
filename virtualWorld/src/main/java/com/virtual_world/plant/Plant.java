package com.virtual_world.plant;

import com.virtual_world.*;

import java.awt.*;
import java.util.List;
import java.util.Random;

public abstract class Plant extends Organism {
    PlantType plantType;
    private int strength;

    public Plant(PlantType plantType, Position position, World world, int age){
        super(position,world,age);
        this.plantType = plantType;
        this.strength = plantType.getStrength();
    }
    @Override
    public void action(){
        propagation(1);
    };

    @Override
    public void collision(Organism attacker, boolean isCounterAttack) {
        world.removeOrganism(this, this.position);
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
    public int getExperienceOnKill() {
        return getPlantType().getExperienceOnKill();
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
        return null;
    }
}
