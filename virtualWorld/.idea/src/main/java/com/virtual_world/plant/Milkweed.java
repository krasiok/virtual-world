package com.virtual_world.plant;

import com.virtual_world.Position;
import com.virtual_world.RandomUtil;
import com.virtual_world.World;


public class Milkweed extends Plant {
    RandomUtil randomUtil;
//    int strength = PlantType.MILKWEED.getStrength();

    public Milkweed(Position position, World world) {
        super(PlantType.MILKWEED, position, world, 0);
        randomUtil = new RandomUtil();
    }

    @Override
    public void action() {
        int countOfPropagation = 3;
        for (int i = 0; i < countOfPropagation; i++) {
            super.action();
        }
    }

    @Override
    public Plant createChild(Position pos) {
        return new Milkweed(pos, world);
    }

//    @Override
//    public void setStrength(int strength) {
//        this.strength = strength;
//    }

}
