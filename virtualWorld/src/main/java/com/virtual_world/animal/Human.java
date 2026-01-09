package com.virtual_world.animal;

import com.virtual_world.*;
import com.virtual_world.ability.*;

import java.util.ArrayList;
import java.util.List;

public class Human extends Animal {

    private int xp;
    private List<Ability> abilities = new ArrayList<>();
    private Direction nextMoveDirection = null;
    private Ability activeAbility = null;
    private int moveLength = 1;
    private boolean isImmortal = false;
    private int basicStrength = AnimalType.HUMAN.getStrength();

    private final HumanListener humanListener;

    public Human(Position position, World world, int xp) {
        super(AnimalType.HUMAN, position, world, 0);
        this.xp = xp;
        this.humanListener = new HumanListener(world, this);


        abilities.add(new BurrntOffering(world,this));
        abilities.add(new MagicElixir(world, this));
        abilities.add(new AntelopeSpeed(world,this));

        abilities.add(new AlzurShield(world,this));
        abilities.add(new Immortality(world,this));
    }

    public void castAbilityByIndex(int index) {
        if(!world.isOrganismAlive(this)){
            return;
        }
        System.out.println("Próba użycia umiejętności nr: " + (index + 1));

        if (index < 0 || index >= abilities.size()) {
            System.out.println("-> Nie ma takiej umiejętności!");
            return;
        }

        Ability candidate = abilities.get(index);


        if (candidate.getCooldown() == 0 && !candidate.isActive()) { // tutaj warunki z expem dam itd.
            if(this.activeAbility!=null){
                this.activeAbility.deactivate();
            }
            this.activeAbility = candidate;
            this.activeAbility.activate();

            System.out.println("-> SUKCES! Aktywowano: " + activeAbility.getClass().getSimpleName());
        } else {
            System.out.println("-> BŁĄD: Cooldown (" + candidate.getCooldown() + ") lub już aktywna.");
        }
    }

    @Override
    public void action() {


        if (activeAbility != null && activeAbility.getAbilityTrigger() == AbilityTrigger.ACTION && activeAbility.isActive()) {
            activeAbility.execute(this,null);
        }
        if(activeAbility!= null) {
            System.out.println(activeAbility.getClass());
        }
        System.out.println("TURA CZŁOWIEKA");
        while (nextMoveDirection == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < moveLength; i++) {
            Position newPos = position.createShifted(nextMoveDirection);

            if (world.positionValid(newPos)) {
                world.updateOrganismPosition(this, this.position, newPos);
                this.position = newPos;
            }
        }
        nextMoveDirection = null;

        for (Ability ability : abilities) {
            ability.passTurn();
        }
    }

    @Override
    public void collision(Organism other, boolean isCounterAttack) {
        System.out.println("collision");
        if(isCounterAttack && activeAbility != null && activeAbility.getAbilityTrigger() == AbilityTrigger.COLLISION && activeAbility.isActive()){
            activeAbility.execute(this,other);
            return;
        }
        if(isImmortal && activeAbility != null){
            activeAbility.execute(this,other);
            return;
        }
        super.collision(other, isCounterAttack);
        setXp(xp+other.getExperienceOnKill());
        System.out.println(xp);
    }


    public HumanListener getHumanListener() {
        return humanListener;
    }

    public void setNextMoveDirection(Direction nextMoveDirection) {
        this.nextMoveDirection = nextMoveDirection;
    }

    public Direction getNextMoveDirection() {
        return nextMoveDirection;
    }

    public boolean isImmortal() {
        return isImmortal;
    }

    public void setImmortal(boolean immortal) {
        isImmortal = immortal;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getMoveLength() {
        return moveLength;
    }

    public void setMoveLength(int moveLength) {
        this.moveLength = moveLength;
    }

    public int getBasicStrength() {
        return basicStrength;
    }

    public void setBasicStrength(int basicStrength) {
        this.basicStrength = basicStrength;
    }

    @Override
    public Organism createChild(Position pos) {
        return null;
    }

}

