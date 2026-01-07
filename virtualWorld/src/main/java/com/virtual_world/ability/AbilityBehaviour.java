package com.virtual_world.ability;

import com.virtual_world.Position;
import com.virtual_world.animal.Human;

public interface AbilityBehaviour {

    int getDuration();
    int getCooldown();
    int getCost();
    AbilityType getAbilityType();
    AbilityTrigger getAbilityTrigger();
    void execute(Human human);
}
