package com.virtual_world;


import com.virtual_world.animal.CyberSheep;

public class Main {
    public static void main(String[] args) {
        World world = new World();
        world.createWorld();

//        int numberOfTurns = 100;
//        for(Organism org:world.getAllOrganisms()){
//            System.out.println(org.getClass() + " " +org.getPosition());
//        }
//        for(int i=0; i<numberOfTurns; i++){
//            napTime();
//            world.takeTurn();
//            for(Organism org:world.getAllOrganisms()){
//                if(org instanceof CyberSheep) {
//                    System.out.println(org.getClass() + " " + org.getPosition());
//                }
//            }
//
//
//        }

        // W klasie Main lub po kliknięciu przycisku "Start"
        new Thread(() -> {
            while(true) { // Nieskończona pętla tur
                world.takeTurn();
                // Opcjonalnie sleep po całej turze
            }
        }).start();

//        System.out.println(world.getAllOrganisms().size());
//
//        for(Organism org:world.getAllOrganisms()){
//            System.out.println(org.getClass() + " " +org.getStrength());
//        }

    }


    public static void napTime() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}