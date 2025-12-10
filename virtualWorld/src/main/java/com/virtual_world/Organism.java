package com.virtual_world;

import java.awt.*;

public abstract class Organism {
    protected Position position;
    protected World world;
    protected int strength;
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

    public abstract void collision(Organism attacker);





    public Position getPosition() {
        return position;
    }

    public int getAge() {
        return age;
    }
    public abstract Color getColor();

    public abstract int getInitiative();
    public void increaseAge(){
        age++;
    }
}
