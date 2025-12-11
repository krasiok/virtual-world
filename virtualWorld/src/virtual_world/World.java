package virtual_world;

import virtual_world.animal.Animal;
import virtual_world.animal.AnimalType;
import virtual_world.animal.Sheep;
import virtual_world.animal.Wolf;

import javax.swing.*;
import java.awt.*;
import java.util.PriorityQueue;
import java.util.Scanner;

public class World {
    public int rows;
    public int columns;
    private JPanel[][] cells;

    public void createWorld() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Select world size: rows -> columns");
        rows = scanner.nextInt();
        columns = scanner.nextInt();


        JFrame frame = new JFrame();
        frame.setSize(1920, 1080);


        JPanel panel = new JPanel(new GridLayout(rows, columns));
        cells = new JPanel[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {

                cells[i][j] = new JPanel();
                cells[i][j].setBackground(Color.BLACK);

                panel.add(cells[i][j]);
            }
        }
        frame.add(panel);
        frame.setVisible(true);

        Wolf wolf1 = new Wolf(randomPosition(), this);
        Sheep sheep1 = new Sheep(randomPosition(), this);

        cells[wolf1.position.getX()][wolf1.position.getY()].setBackground(AnimalType.WOLF.getColor());
        cells[sheep1.position.getX()][sheep1.position.getY()].setBackground(AnimalType.SHEEP.getColor());


    }

    public void takeTurn() {

        PriorityQueue<Animal> initiativeQueue = new PriorityQueue<>(
                (a, b) -> Integer.compare(b.getAnimalType().getInitiative(), a.getAnimalType().getInitiative())
        );
        initiativeQueue.addAll(Animal.allAnimals);

        for(Animal animal: initiativeQueue){
            Position previousPosition = animal.position;
            animal.action();
            cells[previousPosition.getX()][previousPosition.getY()].setBackground(Color.BLACK);
            cells[animal.position.getX()][animal.position.getY()].setBackground(animal.getAnimalType().getColor());
            System.out.println(animal.getClass());
        }
    }

    private Position randomPosition() {
        int randomX = (int) (Math.random() * rows);
        int randomY = (int) (Math.random() * columns);

        return new Position(randomX, randomY);
    }

}
