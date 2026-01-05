package com.virtual_world.animal;

import com.virtual_world.*;

import java.awt.*;
import java.util.List;


public abstract class Animal extends Organism {

    protected AnimalType animalType;
    private int age;
    private int strength;
    Position previousPosition;

    public Animal(AnimalType animalType, Position position, World world, int age) {
        super(position, world, age);
        this.animalType = animalType;
        this.age = age;
        this.strength = animalType.getStrength();
    }

    public void action() {
        boolean moved = false;
        previousPosition = new Position(position.getX(), position.getY());
        List<Direction> availableDirections = Direction.getAll();

        while (!moved && !availableDirections.isEmpty()) {
            Direction randomDirection = RandomUtil.getRandomDirection(availableDirections);
            availableDirections.remove(randomDirection);
            Position newPosition = position.createShifted(randomDirection);

            if (world.positionValid(newPosition) && canMoveTo(newPosition)) {
                world.updateOrganismPosition(this,position,newPosition);
                moved = true;
            }
        }
    }

    public void collision(Organism other) {
        collision(other, false);  // Domyślnie nie jest counter-attack
    }

    public void collision(Organism other, boolean isCounterAttack) {
        if (this.getClass() == other.getClass()) {
            world.updateOrganismPosition(this, this.position, previousPosition);
            propagation(100);
            return;
        }

        if (!isCounterAttack && other.hasSpecialDefence()) {
            other.collision(this, true);
            return;
        }

        int myStrength = this.getStrength();
        int enemyStrength = other.getStrength();

        if (myStrength > enemyStrength) {
            world.removeOrganism(other, other.getPosition());
        } else if (myStrength < enemyStrength) {
            world.removeOrganism(this, this.position);
        }
    }

    public boolean canMoveTo(Position newPos){
        return true;
    }

    @Override
    public boolean hasSpecialDefence() {
        return getAnimalType().hasSpecialDefence();
    }

    public void increaseAge() {
        age++;
    }

    public int getAge() {
        return age;
    }

    public AnimalType getAnimalType() {
        return animalType;
    }

    @Override
    public Color getColor() {
        return getAnimalType().getColor();
    }

    @Override
    public int getInitiative() {
        return getAnimalType().getInitiative();
    }

    @Override
    public int getStrength() {
        return strength;
    }

    @Override
    public void setStrength(int strength) {
        this.strength = strength;
    }

    public Position getPreviousPosition() {
        return previousPosition;
    }


}
