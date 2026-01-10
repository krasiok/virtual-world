package com.virtual_world.ability;

import com.virtual_world.Organism;
import com.virtual_world.RandomUtil;
import com.virtual_world.World;
import com.virtual_world.animal.Antelope;
import com.virtual_world.animal.Human;

public class AntelopeSpeed extends Ability {
    private final int BASIC_HUMAN_MOVE_LENGTH = 1;
    private final int MOVE_LENGTH = 2;
    private final int UNSTABLE_PHASE_DURATION = AbilityType.ANTELOPE_SPEED.getDuration()/2;
    private final Human human;
    public AntelopeSpeed(World world, Human human){
        super(AbilityType.ANTELOPE_SPEED,world);
        this.human = human;
    }

    @Override
    public void activate() {
        super.activate();

        human.setMoveLength(MOVE_LENGTH);
    }


    @Override
    public void execute(Human human, Organism organism) {
        if (this.getDuration() <= UNSTABLE_PHASE_DURATION) {
            unstablePhase();
        } else {
            human.setMoveLength(MOVE_LENGTH);
        }
    }

    private void unstablePhase(){
        if(RandomUtil.succeeds(50)){
            human.setMoveLength(MOVE_LENGTH);
        }
        else {
            human.setMoveLength(BASIC_HUMAN_MOVE_LENGTH);
        }
    }

    @Override
    void cleanup() {
        human.setMoveLength(BASIC_HUMAN_MOVE_LENGTH);
    }
}
