package com.virtual_world.ability;

import com.virtual_world.World;
import com.virtual_world.animal.Human;

public abstract class Ability implements AbilityBehaviour {
    AbilityType abilityType;
    protected World world;

    Ability(AbilityType abilityType,World world){
        this.abilityType = abilityType;
        this.world = world;
    }

    @Override
    public int getDuration() {
        return abilityType.getDuration();
    }

    @Override
    public int getCooldown() {
        return abilityType.getCooldown();
    }

    @Override
    public int getCost() {
        return abilityType.getCost();
    }
    @Override
    public AbilityType getAbilityType(){
        return abilityType;
    }
    @Override
    public AbilityTrigger getAbilityTrigger() {
        return abilityType.getAbilityTrigger();
    }

}
