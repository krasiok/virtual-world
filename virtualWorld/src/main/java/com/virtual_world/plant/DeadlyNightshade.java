package com.virtual_world.plant;

import com.virtual_world.Organism;
import com.virtual_world.Position;
import com.virtual_world.World;

public class DeadlyNightshade extends Plant{

    public DeadlyNightshade(Position position, World world){
        super(PlantType.DEADLY_NIGHTSHADE,position,world,0);
    }

    @Override
    public void collision(Organism attacker, boolean isCounterAttack) {
        world.removeOrganism(attacker,attacker.getPosition());
        System.out.println("Wilcza jagoda zabiła " + attacker.getClass());
    }

    @Override
    public Plant createChild(Position pos) {
        return new DeadlyNightshade(pos,world);
    }
}
