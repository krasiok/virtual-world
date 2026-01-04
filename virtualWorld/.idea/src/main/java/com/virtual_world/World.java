package com.virtual_world;

import com.virtual_world.animal.Sheep;
import com.virtual_world.animal.Wolf;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.function.BiFunction;

public class World {
    private int rows;
    private int columns;

    private List<Organism> allOrganisms = new ArrayList<>();
    private final PanelDrawer panelDrawer = new PanelDrawer(this);
    private final List<Position> allOccupiedPositions = new ArrayList<>();
    private PriorityQueue<Organism> initiativeQueue;

    public void createWorld() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select world size: rows -> columns");
        rows = scanner.nextInt();
        columns = scanner.nextInt();

        createInitialOrganisms();
        panelDrawer.drawWorld();
    }

    public void takeTurn() {
        sortOrganisms();
        processOrganismsTurns();
    }

    private void createOrganisms(BiFunction<Position, World, Organism> creator, int count, World world) {
        for (int i = 0; i < count; i++) {
            Position pos = randomPosition();
            while (world.getAllOccupiedPositions().contains(pos)) {
                pos = randomPosition();
            }

            Organism org = creator.apply(pos, world);
            world.addOrganism(org);

        }
    }

    private void createInitialOrganisms() {
        createOrganisms(Wolf::new, 1, this);
//        createOrganisms(Antelope::new, 5, this);
        createOrganisms(Sheep::new,3,this);
    }

    private void sortOrganisms() {
        initiativeQueue = new PriorityQueue<>(
                Comparator.comparingInt(Organism::getInitiative).reversed()
                        .thenComparing(Comparator.comparingInt(Organism::getAge).reversed())
        );
        initiativeQueue.addAll(allOrganisms);
    }

    private void processOrganismsTurns() {

        while (!initiativeQueue.isEmpty()) {
            Organism organism = initiativeQueue.poll();
            if (!isOrganismAlive(organism)) continue;

            Position previousPosition = new Position(organism.getPosition().getX(), organism.getPosition().getY());
            organism.action();

            Organism other = getOrganismAtExcluding(organism.getPosition(), organism);
            if (other != null) {
                organism.collision(other, false);
            }
            panelDrawer.refreshUIAfterMove(previousPosition);
        }
    }



    public void clearPosition(Position position) {
        panelDrawer.getCells()[position.getX()][position.getY()].setBackground(Color.BLACK);
    }

    private boolean isOrganismAlive(Organism organism) {
        return allOrganisms.contains(organism);
    }



    public int getColumns() {
        return columns;
    }

    public int getRows() {
        return rows;
    }

    public void addOrganism(Organism organism) {
        allOrganisms.add(organism);
        allOccupiedPositions.add(organism.getPosition());
//        panelDrawer.paintCell(organism);
    }

    public void removeOrganism(Organism organism, Position position) {
        allOrganisms.remove(organism);
        allOccupiedPositions.remove(organism.getPosition());
        clearPosition(organism.getPosition()); // redundant?
    }

    public Organism getOrganismAtExcluding(Position pos, Organism exclude) {
        for (Organism organism : allOrganisms) {
            if (organism != exclude && organism.getPosition().equals(pos)) {
                return organism;
            }
        }
        return null;
    }

    public Organism getOrganismAt(Position pos) {
        for (Organism organism : allOrganisms) {
            if (organism.getPosition().equals(pos)) {
                return organism;
            }
        }
        return null;
    }



    public boolean isOccupied(Position pos) {
        return allOccupiedPositions.contains(pos);
    }

    public void updateOrganismPosition(Organism organism, Position oldPos, Position newPos) {
        allOccupiedPositions.remove(oldPos);
        allOccupiedPositions.add(newPos);
        organism.setPosition(newPos);
    }

    public List<Position> getAllOccupiedPositions() {
        return allOccupiedPositions;
    }

    public List<Organism> getAllOrganisms() {
        return allOrganisms;
    }

    public void setOrganisms(List<Organism> organisms) {
        this.allOrganisms = organisms;
    }
    private Position randomPosition() {
        int randomX = (int) (Math.random() * rows);
        int randomY = (int) (Math.random() * columns);

        return new Position(randomX, randomY);
    }
}