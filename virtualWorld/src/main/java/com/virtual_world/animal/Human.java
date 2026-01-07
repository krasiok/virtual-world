package com.virtual_world.animal;

import com.virtual_world.Organism;
import com.virtual_world.Position;
import com.virtual_world.World;
import com.virtual_world.ability.Ability;
import com.virtual_world.ability.AbilityTrigger;
import com.virtual_world.ability.AbilityType;
import com.virtual_world.ability.BurrntOffering;

public class Human extends Animal{


    int xp;
    Ability activeAbility = new BurrntOffering(world);

    public Human(Position position, World world, int xp){
        super(AnimalType.HUMAN,position,world,0);
        this.xp = xp;
    }

    //po nascisnieciu danego guzika jak mamy wystarczająco expa to zmienia sie na true

    @Override
    public void action() {
        if(activeAbility!=null && activeAbility.getAbilityTrigger() == AbilityTrigger.ACTION){
            activeAbility.execute(this);
        }
//        if(activeAbility && activeAbility.)
        //if (ability) && AbilityType = action
        //metoda abilitki na humanie
        super.action();
    }

    @Override
    public void collision(Organism other, boolean isCounterAttack) {
        super.collision(other, isCounterAttack);
    }

    @Override
    public Organism createChild(Position pos) {
        return null;
    }
}

//Human boolean ability (musi wiedziec czy włączona jest czy nie) jak jest włączona to typ czy Action czy Kolizja