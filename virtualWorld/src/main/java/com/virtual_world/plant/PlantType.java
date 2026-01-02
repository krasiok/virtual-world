package com.virtual_world.plant;

import com.virtual_world.Organism;
import com.virtual_world.Position;
import com.virtual_world.World;

import java.awt.*;

public enum PlantType  {

    GRASS(0,0,Color.GREEN,false),
    MILKWEED(0,0,Color.YELLOW,false),
    GUARANA(0,0,Color.RED,true);

    private final int strength;
    private final int initiative;
    private final Color color;
    private final boolean hasSpecialDefence;

    PlantType(int strength, int initiative, Color color, boolean hasSpecialDefence){
        this.strength = strength;
        this.initiative = initiative;
        this.color = color;
        this.hasSpecialDefence = hasSpecialDefence;
    }

    public int getStrength() {
        return strength;
    }

    public Color getColor() {
        return color;
    }

    public int getInitiative() {
        return initiative;
    }


    public boolean hasSpecialDefence() {
        return hasSpecialDefence;
    }
}

