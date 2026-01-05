package com.virtual_world.animal;

import com.virtual_world.*;
import com.virtual_world.plant.Grass;

import java.util.ArrayList;
import java.util.List;

public class Sheep extends Animal {

    public Sheep(Position position, World world) {
        super(AnimalType.SHEEP, position, world, 0);
    }

    @Override
    public Organism createChild(Position pos) {
        return new Sheep(pos, world);
    }
}

