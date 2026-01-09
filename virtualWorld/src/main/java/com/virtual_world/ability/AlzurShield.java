package com.virtual_world.ability;

import com.virtual_world.*;
import com.virtual_world.animal.Animal;
import com.virtual_world.animal.Human;

import java.util.Collections;
import java.util.List;

public class AlzurShield extends Ability{
    private final Human human;
    public AlzurShield(World world, Human human){
        super(AbilityType.ALZUR_SHIELD,world);
        this.human = human;
    }

    @Override
    public void activate() {
        super.activate();
    }

    @Override
    public void execute(Human human, Organism other) {
        if (!(other instanceof Animal)) return;

        Direction dir = findFreeDirection(other);
        if (dir == null) {
            other.setPosition(other.getPreviousPosition());
            return;
        }

        Position oldPos = other.getPosition();
        Position newPos = oldPos.createShifted(dir);

        world.updateOrganismPosition(other, oldPos, newPos);
        other.setPosition(newPos);
    }


    private Direction findFreeDirection(Organism other) {
        List<Direction> dirs = Direction.getAll();
        Collections.shuffle(dirs);

        for (Direction dir : dirs) {
            Position pos = other.getPosition().createShifted(dir);
            if (world.positionValid(pos) && !world.isOccupied(pos)) {
                return dir;
            }
        }
        return null;
    }


    @Override
    void cleanup() {

    }
}
