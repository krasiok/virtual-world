package com.virtual_world;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class World {
    private int rows;
    private int columns;
//    private List<Animal> allAnimals = new ArrayList<>();
    private List<Organism> allOrganisms = new ArrayList<>();
//    public List<Plant> allPlants = new ArrayList<>();

    private DrawPanel drawPanel = new DrawPanel(this);
//    private List<Position> allPositions = drawPanel.getAllOccupiedPositions();
    private final List<Position> allOccupiedPositions = new ArrayList<>();
    private PriorityQueue<Organism> initiativeQueue;


    public void createWorld() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select world size: rows -> columns");
        rows = scanner.nextInt();
        columns = scanner.nextInt();

        // TODO stworzenie poczatkowych organizmow
        // TODO stworzenie planszy - wymiary juz znamy (pusta / wstepnie wypelniona istniejacymi organizmami)
        drawPanel.drawWorld();
    }


    public void takeTurnPseudo() {

        sortOrganisms();

        while (!initiativeQueue.isEmpty()) {
            Organism organism = initiativeQueue.poll();
            if (!isOrganismAlive(organism)) continue;

            Position previousPosition = new Position(organism.getPosition().getX(), organism.getPosition().getY());
            organism.action();

            Organism other = getOrganismAtExcluding(organism.getPosition(), organism);
            if (other != null) {
                organism.collision(other);
            }


            refreshUIAfterMove(previousPosition);
            Main.napTime();
        }
    }

    private void sortOrganisms() {
        initiativeQueue = new PriorityQueue<>(
                Comparator.comparingInt(Organism::getInitiative).reversed()
                        .thenComparing(Comparator.comparingInt(Organism::getAge).reversed())
        );
        initiativeQueue.addAll(allOrganisms);
    }

    private boolean isOrganismAlive(Organism organism){
        return allOrganisms.contains(organism);
    }



//    public void takeTurn() {
//        PriorityQueue<Animal> initiativeQueue = new PriorityQueue<>(
//                Comparator.comparingInt((Animal a) -> a.getAnimalType().getInitiative()).reversed()
//                        .thenComparing(Comparator.comparingInt(Animal::getAge).reversed())
//        );
//        initiativeQueue.addAll(new ArrayList<>(allAnimals));
//
//        for (Animal animal : new ArrayList<>(initiativeQueue)) {
//            if (!allAnimals.contains(animal)) {
//                continue;
//            }
//
//            Position previousPosition = new Position(animal.position.getX(), animal.position.getY());
//
//            animal.action();
//
//            Animal other = getAnimalAtExcluding(animal.position, animal);
//
//            if (other != null) {
//                animal.collision(other);
//            }
//
//            if (allAnimals.contains(animal)) {
//                animal.increaseAge();
//            }
//
//            refreshUIAfterMove(previousPosition);
//            Main.napTime();
//        }
//
//        for (Plant plant : new ArrayList<>(allPlants)) {
//            plant.action();
//        }
//    }

    private void refreshUIAfterMove(Position previousPosition) {
        SwingUtilities.invokeLater(() -> {
            drawPanel.getCells()[previousPosition.getX()][previousPosition.getY()].setBackground(Color.BLACK);

            for (Organism organism : allOrganisms) {
                Position pos = organism.getPosition();
                drawPanel.getCells()[pos.getX()][pos.getY()].setBackground(organism.getColor());
            }
        });
    }

    public int getColumns() {
        return columns;
    }

    public int getRows() {
        return rows;
    }

//    public void addAnimal(Animal animal) {
//        allAnimals.add(animal);
//    }

//    public void addPlant(Plant plant) {
//        allPlants.add(plant);
//        drawPanel.getCells()[plant.position.getX()][plant.position.getX()]
//                .setBackground(PlantType.GRASS.getColor());
//    }

    public void addOrganism(Organism organism) {
        allOrganisms.add(organism);
        allOccupiedPositions.add(organism.getPosition());
        drawPanel.getCells()[organism.getPosition().getX()][organism.getPosition().getY()].setBackground(organism.getColor());
    }
    public void removeOrganism(Organism organism, Position position){
        allOrganisms.remove(organism);
        allOccupiedPositions.remove(organism.getPosition());
        drawPanel.getCells()[organism.getPosition().getX()][organism.getPosition().getY()].setBackground(Color.BLACK);
    }

//    public void removeAnimal(Animal animal, Position position) {
//        allAnimals.remove(animal);
//        allOccupiedPositions.remove(position);
//    }

//    public Animal getAnimalAt(Position pos) {
//        for (Animal animal : allAnimals) {
//            if (animal.getPosition().equals(pos)) {
//                return animal;
//            }
//        }
//        return null;
//    }

//    public Animal getAnimalAtExcluding(Position pos, Animal exclude) {
//        for (Animal animal : allAnimals) {
//            if (animal != exclude && animal.getPosition().equals(pos)) {
//                return animal;
//            }
//        }
//        return null;
//    }

    public Organism getOrganismAtExcluding(Position pos, Organism exclude) {
        for (Organism organism : allOrganisms) {
            if (organism != exclude && organism.getPosition().equals(pos)) {
                return organism;
            }
        }
        return null;
    }

    public void clearPosition(Position position) {
        drawPanel.getCells()[position.getX()][position.getY()].setBackground(Color.BLACK);
    }

    public boolean isOccupied(Position pos) {
        return allOccupiedPositions.contains(pos);
    }

//    public int getAnimalsNumber() {
//        return allAnimals.size();
//    }

    public List<Position> getAllOccupiedPositions() {
        return allOccupiedPositions;
    }

    public List<Organism> getOrganisms() {
        return allOrganisms;
    }

    public void setOrganisms(List<Organism> organisms) {
        this.allOrganisms = organisms;
    }
}