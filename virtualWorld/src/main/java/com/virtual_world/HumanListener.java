package com.virtual_world;

import com.virtual_world.animal.Human;
import com.virtual_world.Organism;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static java.awt.event.KeyEvent.*;

public class HumanListener extends KeyAdapter {
    private final World world;
    private final Human human;

    public HumanListener(World world, Human human){
        this.world = world;
        this.human = human;
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();
        boolean turnFinished = false;

        if(key == VK_KP_LEFT || key == VK_A){
            human.setNextMoveDirection(Direction.UP);
        }else if(key == VK_KP_RIGHT || key == VK_D){
            human.setNextMoveDirection(Direction.DOWN);
        }else if(key == VK_KP_DOWN || key == VK_S){
            human.setNextMoveDirection(Direction.RIGHT);
        }else if(key == VK_KP_UP || key == VK_W){
            human.setNextMoveDirection(Direction.LEFT);
        }

        if (key == VK_1) {
            human.castAbilityByIndex(0); // Wybiera BurntOffering
        }
        else if (key == VK_2) {
            human.castAbilityByIndex(1); // Wybiera MagicElixir
        }
        else if (key == VK_3) {
            human.castAbilityByIndex(2); // Wybiera AntelopeSpeed
        }


        }
    }
