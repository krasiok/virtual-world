package com.virtual_world.animal;


import java.awt.*;

public enum AnimalType {

    WOLF(9, 5, Color.GRAY, false,4),
    SHEEP(4, 4, Color.WHITE, false,1),
    FOX(3, 7, Color.ORANGE, false,2),
    TORTOISE(2, 1, Color.MAGENTA, true,5),
    ANTELOPE(4, 4, Color.cyan, true,3),
    CYBER_SHEEP(11,4,Color.DARK_GRAY,true,10),
    HUMAN(5,4,Color.yellow,true,0);


    private final int strength;
    private final int initiative;
    private final Color color;
    private final boolean hasSpecialDefence;
    private final int experienceOnKill;

    AnimalType(int strength, int initiative, Color color, boolean hasSpecialDefence,int experienceOnKill) {
        this.strength = strength;
        this.initiative = initiative;
        this.color = color;
        this.hasSpecialDefence = hasSpecialDefence;
        this.experienceOnKill = experienceOnKill;
    }

    public int getStrength() {
        return strength;
    }

    public int getInitiative() {
        return initiative;
    }

    public Color getColor() { return color; }

    public boolean hasSpecialDefence() {
        return hasSpecialDefence;
    }

    public int getExperienceOnKill() {
        return experienceOnKill;
    }
}
