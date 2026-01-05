package com.virtual_world.animal;

import com.virtual_world.*;


public class Tortoise extends Animal {

    public Tortoise(Position position, World world) {
        super(AnimalType.TORTOISE, position, world, 0);
    }

    @Override
    public void action() {
        if(RandomUtil.succeeds(25)) {
            super.action();
        }
    }

    @Override
    public void collision(Organism attacker, boolean isCounterAttack) {
        if (isCounterAttack && attacker.getStrength() < 5) {
            world.updateOrganismPosition(attacker, attacker.getPosition(), attacker.getPreviousPosition());
            return;
        }
        super.collision(attacker, isCounterAttack);
    }

    @Override
    public Organism createChild(Position pos) {
        return new Tortoise(pos,world);
    }

}
