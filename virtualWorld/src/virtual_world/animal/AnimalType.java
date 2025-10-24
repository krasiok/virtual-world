package virtual_world.animal;

public enum AnimalType {

    WOLF(9,5),
    SHEEP(4,4);

    private final int strength;
    private final int initiative;

    AnimalType(int strength, int initiative){
        this.strength = strength;
        this.initiative = initiative;
    }

    public int getStrength() {
        return strength;
    }

    public int getInitiative() {
        return initiative;
    }
}
