public class Animal extends Organism{

    String name;
    int strength;
    int initiative;
    public Animal(String name, int strength, int initiative){
        this.name = name;
        this.strength = strength;
        this.initiative = initiative;
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
