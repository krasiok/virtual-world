package virtual_world.animal;

import virtual_world.Organism;
import virtual_world.Position;
import virtual_world.World;

public abstract class Animal extends Organism {
    protected AnimalType animalType;

    public Animal(AnimalType animalType,Position position, World world){
        super(position,world);
        this.animalType = animalType;
    }

    public void action(){
        boolean moved = false;
        while (!moved) {
            int randomMove = (int) (Math.random() * 4);
            int x = position.getX();
            int y = position.getY();
            int newX = x;
            int newY = y;

            switch (randomMove) {
                case 0 -> newY = y - 1;
                case 1 -> newY = y + 1;
                case 2 -> newX = x + 1;
                case 3 -> newX = x - 1;
            }

            if (newX >= 0 && newX < world.rows && newY >= 0 && newY < world.columns) {
                setPosition(new Position(newX, newY));
                moved = true;
            }
        }
    }

    @Override
    public void collision() {

    }

//    public abstract AnimalType getAnimalType();
}
