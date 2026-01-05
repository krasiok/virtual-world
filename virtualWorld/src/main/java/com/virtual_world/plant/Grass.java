package com.virtual_world.plant;

import com.virtual_world.*;


public class Grass extends Plant {

    public Grass(Position position, World world) {
        super(PlantType.GRASS, position, world, 0);
    }

    @Override
    public Organism createChild(Position pos) {
        return new Grass(pos, world);
    }
}
