package com.virtual_world.plant;

import com.virtual_world.*;
import com.virtual_world.animal.AnimalType;

import java.util.List;
import java.util.Optional;

public class Grass extends Plant {
    private RandomUtil randomUtil;
//    int strength = PlantType.GRASS.getStrength();
    public Grass(Position position, World world) {
        super(PlantType.GRASS, position, world, 0);
        this.randomUtil = new RandomUtil();
    }
//    @Override
//    public void setStrength(int strength) {
//        this.strength = strength;
//    }
    @Override
    public Plant createChild(Position pos) {
        return new Grass(pos, world);
    }





    private boolean positionValid(Position position) {
        return position.getX() >= 0 && position.getX() < world.getRows()
                && position.getY() >= 0 && position.getY() < world.getColumns();
    }
}
