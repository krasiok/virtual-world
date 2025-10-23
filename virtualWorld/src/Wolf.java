public class Wolf extends Animal {

    private static final String NAME = "Wolf";
    private static final int STRENGTH = 9;
    private static final int INITIATIVE = 5;
    Position position;

    public Wolf(Position position){
        super(NAME, STRENGTH, INITIATIVE);
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
