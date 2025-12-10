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


    public Animal(AnimalType animalType,Position position, World world, int age){
        super(position,world,age);
        this.animalType = animalType;
//        world.addAnimal(this);
        world.addOrganism(this);
        this.age = age;
    }

    public AnimalType getAnimalType() {
        return animalType;
    }



    public abstract void propagation();


    public void action(){
        boolean moved = false;
        List<Direction> availableDirections = Direction.getAll();
        while (!moved) {
            Direction randomDirection = randomUtil.getRandomDirection(availableDirections);
            availableDirections.remove(randomDirection);
            Position newPosition = position.createShifted(randomDirection);

            if (positionValid(newPosition)) {
                setPosition(newPosition);
                moved = true;
            }
        }
        increaseAge();
    }



    private boolean positionValid(Position position) {
        return position.getX() >= 0 && position.getX() < world.getRows()
                && position.getY() >= 0 && position.getY() < world.getColumns();
    }



    @Override
    public void collision(Organism attacker) {
        if(!(attacker instanceof Animal enemy)){
            return;
        }
        if (this.getClass() == enemy.getClass()){
            propagation();
        }

        int myStrength = this.animalType.getStrength();
        int enemyStrength = enemy.getAnimalType().getStrength();

//        System.out.println("COLLISION " + this.animalType +" "+ enemy.animalType);

        if(myStrength>enemyStrength){
            world.removeOrganism(enemy,enemy.position);
        }
        if(myStrength<enemyStrength){
            world.removeOrganism(this,this.position);
        }

        // check if ==
        // same type -> add new

    }

    void setRandomUtil(RandomUtil randomUtil) {
        this.randomUtil = randomUtil;
    }

    public int getAge() {
        return age;
    }
    public void increaseAge(){
        age++;
    }


    protected Position getNextPosition() {

        // rand position
        return null;
    }

    @Override
    public Color getColor() {
        return animalType.getColor();
    }

    @Override
    public int getInitiative() {
        return getAnimalType().getInitiative();
    }
}
