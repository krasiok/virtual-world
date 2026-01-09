package com.virtual_world.plant;

import com.virtual_world.Organism;
import com.virtual_world.Position;
import com.virtual_world.World;
import com.virtual_world.ability.MagicElixir;
import com.virtual_world.animal.Human;

public class Guarana extends Plant{
    final int VALUE_AFTER_EATEN = 3;

    public Guarana(Position position, World world){
        super(PlantType.GUARANA,position,world,0);
    }

    @Override
    public void collision(Organism attacker, boolean isCounterAttack) {
        onEaten(attacker);
    }

    private void onEaten(Organism eater){
        if(eater instanceof Human){
            ((Human) eater).setBasicStrength(((Human) eater).getBasicStrength() + VALUE_AFTER_EATEN);
        }
        eater.setStrength(eater.getStrength() + VALUE_AFTER_EATEN);
        world.removeOrganism(this,this.getPosition());
    }

    @Override
    public Organism createChild(Position pos) {
        return new Guarana(pos,world);
    }
}
