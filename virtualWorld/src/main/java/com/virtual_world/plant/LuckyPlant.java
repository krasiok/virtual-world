package com.virtual_world.plant;

import com.virtual_world.Organism;
import com.virtual_world.Position;
import com.virtual_world.World;

public class LuckyPlant extends Plant{
    public LuckyPlant(Position position, World world){
        super(PlantType.LUCKY_PLANT,position,world,0);
    }

    @Override
    public Organism createChild(Position pos) {
        return new LuckyPlant(pos, world);
    }
}
