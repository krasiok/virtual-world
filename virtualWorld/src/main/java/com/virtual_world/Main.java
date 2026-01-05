package com.virtual_world;



public class Main {
    public static void main(String[] args) {
        World world = new World();
        world.createWorld();

        int numberOfTurns = 25;

        for(int i=0; i<numberOfTurns; i++){
            napTime();
            world.takeTurn();
        }

        System.out.println(world.getAllOrganisms().size());

        for(Organism org:world.getAllOrganisms()){
            System.out.println(org.getClass() + " " +org.getStrength());
        }

    }


    public static void napTime() {
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}