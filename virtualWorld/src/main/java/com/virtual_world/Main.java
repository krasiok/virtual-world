package com.virtual_world;


public class Main {
    public static void main(String[] args) {
        World world = new World();
        world.createWorld();

        new Thread(() -> {
            while(true) {
                world.takeTurn();
            }
        }).start();

    }

    public static void napTime() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}