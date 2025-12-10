package com.virtual_world.plant;

import com.virtual_world.Organism;
import com.virtual_world.Position;
import com.virtual_world.World;

import java.awt.*;

public enum PlantType  {

    GRASS(0,0,Color.GREEN);

    private final int strength;
    private final int initiative;
    private final Color color;

    PlantType(int strength, int initiative, Color color){
        this.strength = strength;
        this.initiative = initiative;
        this.color = color;
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
}

