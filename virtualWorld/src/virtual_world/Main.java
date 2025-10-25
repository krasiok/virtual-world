package virtual_world;

public class Main {
    public static void main(String[] args) {
        World world = new World();
        world.createWorld();
        napTime();
        world.takeTurn();
        napTime();
        world.takeTurn();
        napTime();
        world.takeTurn();
        napTime();
        world.takeTurn();
        napTime();
        world.takeTurn();
        napTime();
    }

    private static void napTime() {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}