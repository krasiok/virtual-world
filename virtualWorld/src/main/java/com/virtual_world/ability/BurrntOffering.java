package com.virtual_world.ability;

import com.virtual_world.Direction;
import com.virtual_world.Organism;
import com.virtual_world.Position;
import com.virtual_world.World;
import com.virtual_world.animal.Human;

public class BurrntOffering extends Ability{
    private final Human human;
    public BurrntOffering(World world, Human human){
        super(AbilityType.BURRNT_OFFERING,world);
        this.human = human;
    }

    @Override
    public void activate() {
        super.activate();
        burnNeighbours();
    }

    @Override
    public void execute(Human human, Organism organism) {
        burnNeighbours();
    }

    private void burnNeighbours(){
        for (Direction dir : Direction.getAll()) {
            Position nearbyPosition = human.getPosition().createShifted(dir);
            Organism nearbyOrganism = world.getOrganismAt(nearbyPosition);
            if(nearbyOrganism!=null) {
                world.removeOrganism(nearbyOrganism, nearbyPosition);
                human.setXp(human.getXp()+nearbyOrganism.getExperienceOnKill());
                System.out.println(human.getXp());
            }
        }
    }


    @Override
    void cleanup() {

    }
}
