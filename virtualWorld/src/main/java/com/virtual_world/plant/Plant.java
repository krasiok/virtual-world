package com.virtual_world.plant;

import com.virtual_world.Organism;
import com.virtual_world.Position;
import com.virtual_world.RandomUtil;
import com.virtual_world.World;

import java.awt.*;

public abstract class Plant extends Organism {
    PlantType plantType;

    public Plant(PlantType plantType, Position position, World world, int age){
        super(position,world,age);
        this.plantType = plantType;
//        world.addPlant(this);
        world.addOrganism(this);
    }
    @Override
    public abstract void action();



    @Override
    public void collision(Organism attacker) {

    }

    public PlantType getPlantType() {
        return plantType;
    }

    @Override
    public int getInitiative() {
        return getPlantType().getInitiative();
    }

    @Override
    public Color getColor() {
       return plantType.getColor();
    }
}
