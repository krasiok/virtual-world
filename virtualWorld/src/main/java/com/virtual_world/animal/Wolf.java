package com.virtual_world.animal;

import com.virtual_world.*;
import com.virtual_world.plant.Grass;

import java.awt.*;
import java.util.List;


public class Wolf extends Animal {

    public Wolf(Position position, World world) {
        super(AnimalType.WOLF, position, world, 0);
    }

    @Override
    public Organism createChild(Position pos) {
        return new Wolf(pos,world);
    }
}
