package com.virtual_world.animal;

import com.virtual_world.*;
import com.virtual_world.plant.Grass;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

//import static com.virtual_world.World.allAnimals;

public abstract class Animal extends Organism {
    protected AnimalType animalType;
    private RandomUtil randomUtil = new RandomUtil();
    private int age;
    Position previousPosition;


    public Animal(AnimalType animalType, Position position, World world, int age) {
        super(position, world, age);
        this.animalType = animalType;
        this.age = age;
    }

    public AnimalType getAnimalType() {
        return animalType;
    }

    public boolean canMoveTo(Position newPos){
        return true;
    }

    public void action() {
        boolean moved = false;
        previousPosition = position.clone();
        List<Direction> availableDirections = Direction.getAll();

        while (!moved && !availableDirections.isEmpty()) {
            Direction randomDirection = randomUtil.getRandomDirection(availableDirections);
            availableDirections.remove(randomDirection);
            Position newPosition = position.createShifted(randomDirection);

            if (isPositionInWorldBounds(newPosition) && canMoveTo(newPosition)) {

                world.getAllOccupiedPositions().remove(position);

                setPosition(newPosition);


                world.getAllOccupiedPositions().add(newPosition);

                moved = true;
            }
        }
        increaseAge();
    }

    public boolean defend(){
        return false;
    }

    public void collision(Organism other) {
        collision(other, false);  // Domyślnie nie jest counter-attack
    }


    public void collision(Organism other, boolean isCounterAttack) {

        if (this.getClass() == other.getClass()) {
            world.updateOrganismPosition(this, this.position, previousPosition);
            propagation();
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

    @Override
    public void propagation() {

        List<Direction> availableDirections = Direction.getAll();
        boolean propagated = false;

        while (!propagated && !availableDirections.isEmpty()) {
            Direction dir = randomUtil.getRandomDirection(availableDirections);
            availableDirections.remove(dir);

            Position newPos = position.createShifted(dir);

            if (isPositionInWorldBounds(newPos)) {

                Animal baby = createChild(newPos);
                world.addOrganism(baby);

                Organism other = world.getOrganismAtExcluding(newPos, baby);
                if(other != null){
                    baby.collision(other, false);
                }

                propagated = true;
            }
        }
    }

    public abstract Animal createChild(Position pos);


    void setRandomUtil(RandomUtil randomUtil) {
        this.randomUtil = randomUtil;
    }

    public int getAge() {
        return age;
    }

    public void increaseAge() {
        age++;
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
        return getAnimalType().getStrength();
    }

    @Override
    public boolean hasSpecialDefence() {
        return getAnimalType().hasSpecialDefence();
    }

    public Position getPreviousPosition() {
        return previousPosition;
    }
}
