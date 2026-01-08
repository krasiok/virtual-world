package com.virtual_world.ability;

import com.virtual_world.Direction;
import com.virtual_world.Organism;
import com.virtual_world.Position;
import com.virtual_world.World;
import com.virtual_world.animal.Human;

public class BurrntOffering extends Ability{
    public BurrntOffering(World world){
        super(AbilityType.BURRNT_OFFERING,world);
    }

    @Override
    public void execute(Human human) {
        for (Direction dir : Direction.getAll()) {

            Position nearbyPosition = human.getPosition().createShifted(dir);
            Organism nearbyOrganism = world.getOrganismAt(nearbyPosition);
            if(nearbyOrganism!=null) {
                world.removeOrganism(nearbyOrganism, nearbyPosition);
            }
        }
    }


    @Override
    void cleanup() {

    }
}
