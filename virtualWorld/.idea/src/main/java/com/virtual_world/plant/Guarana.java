package com.virtual_world.plant;

import com.virtual_world.Organism;
import com.virtual_world.Position;
import com.virtual_world.World;

public class Guarana extends Plant{
    final int VALUE_AFTER_EATEN = 3;
//    int strength = PlantType.GUARANA.getStrength();
    public Guarana(Position position, World world){
        super(PlantType.GUARANA,position,world,0);
    }

    @Override
    public Plant createChild(Position pos) {
        return new Guarana(pos,world);
    }




    @Override
    public void collision(Organism attacker, boolean isCounterAttack) {
        onEaten(attacker);
        System.out.println("GUARANA ZOSTALA ZJEDZONA");
        System.out.println("SILA " + attacker.getClass() +" Wynosi " + attacker.getStrength());

    }

    private void onEaten(Organism eater){
        eater.setStrength(eater.getStrength() + VALUE_AFTER_EATEN);
        world.removeOrganism(this,this.getPosition());
    }
}
