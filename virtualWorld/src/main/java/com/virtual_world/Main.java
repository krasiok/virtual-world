package com.virtual_world;


import com.virtual_world.animal.CyberSheep;

public class Main {
    public static void main(String[] args) {
        World world = new World();
        world.createWorld();

        int numberOfTurns = 100;
        for(Organism org:world.getAllOrganisms()){
            System.out.println(org.getClass() + " " +org.getPosition());
        }
        for(int i=0; i<numberOfTurns; i++){
            napTime();
            world.takeTurn();
            for(Organism org:world.getAllOrganisms()){
                if(org instanceof CyberSheep) {
                    System.out.println(org.getClass() + " " + org.getPosition());
                }
            }


        }

        System.out.println(world.getAllOrganisms().size());

        for(Organism org:world.getAllOrganisms()){
            System.out.println(org.getClass() + " " +org.getStrength());
        }

    }


    public static void napTime() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}