public class Sheep extends Animal {
    private final static String NAME = "Sheep";
    private final static int STRENGTH = 4;
    private final static int INITIATIVE = 4;
    Position position;

    public Sheep(Position position){
        super(NAME,STRENGTH,INITIATIVE);
        this.position = position;
    }

    @Override
    public void action() {
        super.action();
    }

    @Override
    public void collision() {
        super.collision();
    }

    @Override
    public void drawBackground() {
        super.drawBackground();
    }
}
