package com.virtual_world.plant;

import com.virtual_world.Organism;
import com.virtual_world.Position;
import com.virtual_world.World;


public class Milkweed extends Plant {

    public Milkweed(Position position, World world) {
        super(PlantType.MILKWEED, position, world, 0);
    }

    @Override
    public void action() {
        int countOfPropagation = 3;
        for (int i = 0; i < countOfPropagation; i++) {
            super.action();
        }
    }

    @Override
    public Organism createChild(Position pos) {
        return new Milkweed(pos, world);
    }

}
