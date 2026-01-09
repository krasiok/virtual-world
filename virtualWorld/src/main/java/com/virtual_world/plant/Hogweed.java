package com.virtual_world.plant;

import com.virtual_world.*;
import com.virtual_world.animal.Animal;
import com.virtual_world.animal.CyberSheep;
import com.virtual_world.animal.Human;

public class Hogweed extends Plant {
    public Hogweed(Position position, World world) {
        super(PlantType.HOGWEED, position, world, 0);
    }

    @Override
    public void action() {
        checkSurroundings();
        super.action();
    }

    @Override
    public void collision(Organism attacker, boolean isCounterAttack) {
        if(attacker instanceof Human){
            if(((Human) attacker).isImmortal()){
                return;
            }
        }
        if(isCounterAttack && isVulnerableToHogweed(attacker)){
            world.removeOrganism(attacker,attacker.getPosition());
        }
        super.collision(attacker, isCounterAttack);
    }

    private void checkSurroundings() {
        for (Direction dir : Direction.getAll()) {

            Position nearbyPosition = position.createShifted(dir);
            Organism nearbyOrganism = world.getOrganismAt(nearbyPosition);
            if(nearbyOrganism instanceof Human){
                if(((Human) nearbyOrganism).isImmortal()){
                    continue;
                }
            }
            if (isVulnerableToHogweed(nearbyOrganism)) {
                world.removeOrganism(nearbyOrganism, nearbyPosition);
            }

        }
    }

    private boolean isVulnerableToHogweed(Organism organism) {
        if (organism == null) return false;
        if (organism instanceof CyberSheep) return false;
        return organism instanceof Animal;
    }

    @Override
    public Organism createChild(Position pos) {
        return new Hogweed(pos, world);
    }
}
