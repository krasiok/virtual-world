package com.virtual_world.ability;

import com.virtual_world.Direction;
import com.virtual_world.Organism;
import com.virtual_world.Position;
import com.virtual_world.World;
import com.virtual_world.animal.Animal;
import com.virtual_world.animal.Human;

import java.util.Collections;
import java.util.List;

public class Immortality extends Ability{
    private final Human human;
    public Immortality(World world, Human human){
        super(AbilityType.IMMORTALITY,world);
        this.human = human;
    }

    @Override
    public void activate() {
        super.activate();
        human.setImmortal(true);
    }

    @Override
    public void execute(Human human, Organism other) {

        Direction dir = findFreeDirection(human);
        if (dir == null) {
            human.setPosition(human.getPreviousPosition());
            return;
        }

        if(human.getStrength()<other.getStrength()) {
            Position oldPos = human.getPosition();
            Position newPos = oldPos.createShifted(dir);

            world.updateOrganismPosition(human, oldPos, newPos);
            human.setPosition(newPos);
        }
        else if(human.getStrength()>=other.getStrength()){
            world.removeOrganism(other,other.getPosition());
            human.setXp(human.getXp() + other.getExperienceOnKill());
            System.out.println(human.getXp());
        }
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
        human.setImmortal(false);
    }
}
