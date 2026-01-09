package com.virtual_world.ability;

import com.virtual_world.Organism;
import com.virtual_world.World;
import com.virtual_world.animal.AnimalType;
import com.virtual_world.animal.Human;

public class MagicElixir extends Ability {
    private boolean isHumanStrengthSet = false;
    private final int HUMAN_NEW_STRENGTH = 10;
//    private int humanBasicStrength = AnimalType.HUMAN.getStrength();
    private final Human human;

    public MagicElixir(World world, Human human){
        super(AbilityType.MAGIC_ELIXIR,world);
        this.human = human;
    }



    @Override
    public void activate() {
        super.activate();
        if(!isHumanStrengthSet && HUMAN_NEW_STRENGTH>human.getBasicStrength()){
            human.setStrength(HUMAN_NEW_STRENGTH);
            isHumanStrengthSet = true;
        }
    }

    @Override
    public void execute(Human human, Organism organism) {
        if(human.getStrength()!=human.getBasicStrength()){
            human.setStrength(human.getStrength()-1);
        }
    }

    @Override
    void cleanup() {
        this.human.setStrength(human.getBasicStrength());
        this.isHumanStrengthSet = false;
    }


}
