package com.virtual_world;

import com.virtual_world.animal.Animal;

import java.awt.*;
import java.util.List;

public abstract class Organism {
    protected Position position;
    protected World world;
    private int age;

    public Organism(Position position, World world, int age) {
        this.position = position;
        this.world = world;
        this.age = age;
    }

    public abstract void action();

    public abstract void collision(Organism attacker, boolean isCounterAttack);

    public abstract Organism createChild(Position pos);

    public void propagation(int chanceForPropagation) {
        if(RandomUtil.succeeds(chanceForPropagation)) {
            List<Direction> availableDirections = Direction.getAll();
            boolean propagated = false;

            while (!propagated && !availableDirections.isEmpty()) {
                Direction dir = RandomUtil.getRandomDirection(availableDirections);
                availableDirections.remove(dir);

                Position newPos = position.createShifted(dir);

                if (world.positionValid(newPos) && !world.isOccupied(newPos)) {

                    Organism baby = createChild(newPos);
                    world.addOrganism(baby);
                    world.getPanelDrawer().paintCell(baby);

                    propagated = true;
                }
            }
        }
    }



    public void increaseAge() {
        age++;
    }

    public abstract boolean hasSpecialDefence();

    public abstract int getInitiative();

    public abstract int getStrength();

    public abstract void setStrength(int strength);

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public abstract Position getPreviousPosition();

    public int getAge() {
        return age;
    }

    public abstract Color getColor();

    public abstract int getExperienceOnKill();
}