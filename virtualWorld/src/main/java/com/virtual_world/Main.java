package com.virtual_world;



public class Main {
    public static void main(String[] args) {
        World world = new World();
        world.createWorld();

        for(int i=0; i<25; i++){ // 70 :D
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
            Thread.sleep(700);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}