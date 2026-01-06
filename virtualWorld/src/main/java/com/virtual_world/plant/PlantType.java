package com.virtual_world.plant;


import java.awt.*;

public enum PlantType {

    GRASS(0, 0, Color.GREEN, false),
    MILKWEED(0, 0, Color.YELLOW, false),
    GUARANA(0, 0, Color.RED, true),
    DEADLY_NIGHTSHADE(99, 0, Color.BLUE, true),
    HOGWEED(10,0,Color.pink,true);

    private final int strength;
    private final int initiative;
    private final Color color;
    private final boolean hasSpecialDefence;

    PlantType(int strength, int initiative, Color color, boolean hasSpecialDefence) {
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

    public Color getColor() {
        return color;
    }

    public boolean hasSpecialDefence() {
        return hasSpecialDefence;
    }
}

