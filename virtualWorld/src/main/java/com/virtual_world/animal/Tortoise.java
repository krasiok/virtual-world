package com.virtual_world.animal;

import com.virtual_world.*;

import java.util.List;

public class Tortoise extends Animal {
    RandomUtil randomUtil;

    public Tortoise(Position position, World world) {
        super(AnimalType.TORTOISE, position, world, 0);
        this.randomUtil = new RandomUtil();
    }

    @Override
    public Animal createChild(Position pos) {
        return new Tortoise(pos,world);
    }

    @Override
    public void action() {
        if(randomUtil.chance(25)) {
            super.action();
        }
        increaseAge();
    }

    @Override
    public void collision(Organism attacker, boolean isCounterAttack) {
        if (isCounterAttack && attacker.getStrength() < 5) {
            world.updateOrganismPosition(attacker, attacker.getPosition(),
                    attacker.getPreviousPosition());
            return;
        }

        super.collision(attacker, isCounterAttack);
    }

    private boolean positionValid(Position position) {
        return position.getX() >= 0 && position.getX() < world.getRows()
                && position.getY() >= 0 && position.getY() < world.getColumns();
    }
}
