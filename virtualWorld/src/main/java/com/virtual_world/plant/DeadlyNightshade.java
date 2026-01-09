package com.virtual_world.plant;

import com.virtual_world.Organism;
import com.virtual_world.Position;
import com.virtual_world.World;
import com.virtual_world.animal.Human;

public class DeadlyNightshade extends Plant{

    public DeadlyNightshade(Position position, World world){
        super(PlantType.DEADLY_NIGHTSHADE,position,world,0);
    }

    @Override
    public void collision(Organism attacker, boolean isCounterAttack) {
        if(attacker instanceof Human){
            if(((Human) attacker).isImmortal()){
                return;
            }
        }
        world.removeOrganism(attacker,attacker.getPosition());
    }

    @Override
    public Organism createChild(Position pos) {
        return new DeadlyNightshade(pos,world);
    }
}
