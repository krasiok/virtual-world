package com.virtual_world;



public class Main {
    public static void main(String[] args) {
        World world = new World();
        world.createWorld();

        for(int i=0; i<50; i++){ // 70 :D
            napTime();
            world.takeTurnPseudo();
        }
        System.out.println(world.getOrganisms().size());
//        System.out.println(world.getAnimalsNumber());
    }


    public static void napTime() {
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}