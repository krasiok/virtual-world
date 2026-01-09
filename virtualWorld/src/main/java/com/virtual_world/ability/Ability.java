package com.virtual_world.ability;

import com.virtual_world.Organism;
import com.virtual_world.World;
import com.virtual_world.animal.Human;

public abstract class Ability implements AbilityBehaviour {
    AbilityType abilityType;
    protected World world;
    private boolean isActive = false;
    private int durationTimer = 0;
    private int cooldownTimer = 0;

    Ability(AbilityType abilityType, World world) {
        this.abilityType = abilityType;
        this.world = world;
    }

    public void passTurn() {
        if(isActive) {
            if (durationTimer > 0) {
                durationTimer--;
            }
            if (durationTimer == 0) {
                deactivate();
                return;
            }
        }

        if (cooldownTimer > 0) {
            cooldownTimer--;
        }
    }

    public void activate() {
            durationTimer = abilityType.getDuration();
            isActive = true;
    }

    public void deactivate() {
        isActive = false;
        cooldownTimer = abilityType.getCooldown();
        durationTimer = 0;
        cleanup();
    }

    abstract void cleanup();

    public boolean execute(Organism other) {
        return false;
    }

    public boolean isActive() {
        return isActive;
    }

    @Override
    public int getDuration() {
        return durationTimer;
    }

    @Override
    public int getCooldown() {
        return cooldownTimer;
    }

    @Override
    public int getCost() {
        return abilityType.getCost();
    }

    @Override
    public AbilityType getAbilityType() {
        return abilityType;
    }

    @Override
    public AbilityTrigger getAbilityTrigger() {
        return abilityType.getAbilityTrigger();
    }

}
