package com.virtual_world.animal;

import com.virtual_world.Organism;
import com.virtual_world.Position;
import com.virtual_world.World;

import java.awt.*;

public enum AnimalType {

    WOLF(9,5, Color.GRAY),
    SHEEP(4,4,Color.WHITE);

    private final int strength;
    private final int initiative;
    private final Color color;

    AnimalType(int strength, int initiative, Color color){
        this.strength = strength;
        this.initiative = initiative;
        this.color = color;
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



}
