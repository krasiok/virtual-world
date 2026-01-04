package com.virtual_world.animal;


import java.awt.*;

public enum AnimalType {

    WOLF(9, 5, Color.GRAY, false),
    SHEEP(4, 4, Color.WHITE, false),
    FOX(3, 7, Color.ORANGE, false),
    TORTOISE(2, 1, Color.MAGENTA, true),
    ANTELOPE(4, 4, Color.cyan, true);


    private final int strength;
    private final int initiative;
    private final Color color;
    private final boolean hasSpecialDefence;

    AnimalType(int strength, int initiative, Color color, boolean hasSpecialDefence) {
        this.strength = strength;
        this.initiative = initiative;
        this.color = color;
        this.hasSpecialDefence = hasSpecialDefence;
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
}
