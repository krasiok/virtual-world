package com.virtual_world;

import java.awt.*;

public abstract class Organism {
    protected Position position;
    protected World world;
    private int age;


    public Organism(Position position, World world, int age){
        this.position = position;
        this.world = world;
        this.age = age;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public abstract void action();

    public abstract void collision(Organism attacker, boolean isCounterAttack);

    public abstract void propagation();





    public Position getPosition() {
        return position;
    }

    public int getAge() {
        return age;
    }
    public abstract Color getColor();
    public abstract int getStrength();
    public abstract void setStrength(int strength);
    public abstract int getInitiative();
    public void increaseAge(){
        age++;
    }
    public abstract boolean hasSpecialDefence();
    public abstract Position getPreviousPosition();
}
