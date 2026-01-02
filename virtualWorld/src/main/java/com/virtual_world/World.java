package com.virtual_world;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class World {
    private int rows;
    private int columns;

    private List<Organism> allOrganisms = new ArrayList<>();
    private final DrawPanel drawPanel = new DrawPanel(this);
    private final List<Position> allOccupiedPositions = new ArrayList<>();
    private PriorityQueue<Organism> initiativeQueue;

    public void createWorld() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select world size: rows -> columns");
        rows = scanner.nextInt();
        columns = scanner.nextInt();

        drawPanel.drawWorld();
    }

    public void takeTurnPseudo() {
        sortOrganisms();
        processOrganismsTurns();

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

            refreshUIAfterMove(previousPosition);
        }
    }

    public void refreshUIAfterMove(Position previousPosition) {

        clearPosition(previousPosition);

        List<Organism> organismsCopy = new ArrayList<>(allOrganisms);

        for (Organism organism : organismsCopy) {
            Position pos = organism.getPosition();
            drawPanel.getCells()[pos.getX()][pos.getY()].setBackground(organism.getColor());
        }
        Main.napTime();

    }

    public void clearPosition(Position position) {
        drawPanel.getCells()[position.getX()][position.getY()].setBackground(Color.BLACK);
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

//        SwingUtilities.invokeLater(() -> {
//            drawPanel.getCells()[organism.getPosition().getX()][organism.getPosition().getY()]
//                    .setBackground(organism.getColor());
//        });
    }

    public void removeOrganism(Organism organism, Position position) {
        allOrganisms.remove(organism);
        allOccupiedPositions.remove(organism.getPosition());
//        clearPosition(organism.getPosition());
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
}