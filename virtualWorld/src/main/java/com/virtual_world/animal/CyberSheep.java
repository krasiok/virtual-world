package com.virtual_world.animal;

import com.virtual_world.Direction;
import com.virtual_world.Organism;
import com.virtual_world.Position;
import com.virtual_world.World;
import com.virtual_world.plant.Hogweed;

import java.util.ArrayList;
import java.util.List;

public class CyberSheep extends Animal {
    boolean isTurnedIntoSheep = false;

    public CyberSheep(Position position, World world) {
        super(AnimalType.CYBER_SHEEP, position, world, 0);
    }

    @Override
    public void action() {

        List<Position> hogweeds = findAllHogweeds();


        if (!hogweeds.isEmpty()) {
            Position nearestTarget = findNearestTarget(hogweeds);
            Position nextMove = findBestMove(nearestTarget);

            world.updateOrganismPosition(this, this.position, nextMove);
        } else {
            if (!isTurnedIntoSheep) {
                turnIntoSheep();
                isTurnedIntoSheep = true;
            }
            super.action();
        }
    }

    @Override
    public void collision(Organism other, boolean isCounterAttack) {

        if (other instanceof Hogweed) {
            world.removeOrganism(other, other.getPosition());
            return;
        }
            super.collision(other, isCounterAttack);
    }


    private List<Position> findAllHogweeds() {
        List<Position> positions = new ArrayList<>();
        for (Organism org : world.getAllOrganisms()) {
            if (org instanceof Hogweed) {
                positions.add(org.getPosition());
            }
        }
        return positions;
    }

    private Position findNearestTarget(List<Position> hogweeds) {

        Position nearest = null;
        int minDistance = Integer.MAX_VALUE;

        for (Position pos : hogweeds) {

            int distance = Math.abs(this.position.getX() - pos.getX())
                    + Math.abs(this.position.getY() - pos.getY());

            if (distance < minDistance) {
                minDistance = distance;
                nearest = pos;
            }
        }
        return nearest;
    }

    private Position findBestMove(Position target) {
        int currentX = this.position.getX();
        int currentY = this.position.getY();
        int targetX = target.getX();
        int targetY = target.getY();


        int diffX = Math.abs(currentX - targetX);
        int diffY = Math.abs(currentY - targetY);

        if (diffX >= diffY) {
            if (currentX > targetX) return position.createShifted(Direction.LEFT);
            else return position.createShifted(Direction.RIGHT);
        } else {
            if (currentY > targetY) return position.createShifted(Direction.UP);
            else return position.createShifted(Direction.DOWN);
        }
    }

    public void turnIntoSheep(){
        this.setStrength(AnimalType.SHEEP.getStrength());
        this.animalType = AnimalType.SHEEP;
    }

    @Override
    public Organism createChild(Position pos) {
        return new CyberSheep(pos, world);
    }
}