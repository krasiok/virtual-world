package com.virtual_world.plant;


import java.awt.*;

public enum PlantType {

    GRASS(0, 0, Color.GREEN, false,2),
    MILKWEED(0, 0, Color.YELLOW, false,1),
    GUARANA(0, 0, Color.RED, true,1),
    DEADLY_NIGHTSHADE(99, 0, Color.BLUE, true,0),
    HOGWEED(10,0,Color.pink,true,0);

    private final int strength;
    private final int initiative;
    private final Color color;
    private final boolean hasSpecialDefence;
    private final int experienceOnKill;

    PlantType(int strength, int initiative, Color color, boolean hasSpecialDefence,int experienceOnKill) {
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

    public Color getColor() {
        return color;
    }

    public boolean hasSpecialDefence() {
        return hasSpecialDefence;
    }

    public int getExperienceOnKill() {
        return experienceOnKill;
    }
}

