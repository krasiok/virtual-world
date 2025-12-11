package com.virtual_world.animal;

import com.virtual_world.Organism;
import com.virtual_world.Position;
import com.virtual_world.RandomUtil;
import com.virtual_world.World;

public class Fox extends Animal{
    RandomUtil randomUtil;
    public Fox(Position position, World world){
        super(AnimalType.FOX,position,world,0);
        this.randomUtil = new RandomUtil();
    }


    @Override
    public boolean canMoveTo(Position newPos) {
        Organism other = world.getOrganismAt(newPos);

        return other == null || other.getStrength() <= this.getStrength();
    }

    @Override
    public Animal createChild(Position pos) {
        return new Fox(pos,world);
    }
}
