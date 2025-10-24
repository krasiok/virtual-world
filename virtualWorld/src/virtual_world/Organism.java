package virtual_world;

public abstract class Organism {
    protected Position position;
    protected World world;

    public Organism(Position position, World world){
        this.position = position;
        this.world = world;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public abstract void action();

    public abstract void collision();

    public void drawBackground(){

    }
}
