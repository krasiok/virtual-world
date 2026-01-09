package com.virtual_world.ability;

public enum AbilityType {

    IMMORTALITY(5,10,9,AbilityTrigger.COLLISION),
    MAGIC_ELIXIR(5,10,7,AbilityTrigger.ACTION),
    ANTELOPE_SPEED(5,10,5,AbilityTrigger.ACTION),
    ALZUR_SHIELD(5,10,6,AbilityTrigger.COLLISION),
    BURRNT_OFFERING(5,15,15,AbilityTrigger.ACTION);

    private final int duration;
    private final int cooldown;
    private final int cost;
    private final AbilityTrigger abilityTrigger;

    AbilityType(int duration, int cooldown, int cost,AbilityTrigger abilityTrigger){
        this.duration = duration;
        this.cooldown = cooldown;
        this.cost = cost;
        this.abilityTrigger = abilityTrigger;
    }

    public int getDuration() {
        return duration;
    }

    public int getCooldown() {
        return cooldown;
    }

    public int getCost() {
        return cost;
    }

    public AbilityTrigger getAbilityTrigger() {
        return abilityTrigger;
    }
}
