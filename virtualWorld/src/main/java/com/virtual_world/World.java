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

        while (!initiativeQueue.isEmpty()) {
            Organism organism = initiativeQueue.poll();
            if (!isOrganismAlive(organism)) continue;

            Position previousPosition = organism.getPosition().clone();
            organism.action();

            Organism other = getOrganismAtExcluding(organism.getPosition(), organism);
            if (other != null) {
                organism.collision(other, false);
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

    private boolean isOrganismAlive(Organism organism) {
        return allOrganisms.contains(organism);
    }

    public void refreshUIAfterMove(Position previousPosition) {
        SwingUtilities.invokeLater(() -> {
            clearPosition(previousPosition);

            // Stwórz kopię listy aby uniknąć ConcurrentModificationException
            List<Organism> organismsCopy = new ArrayList<>(allOrganisms);

            for (Organism organism : organismsCopy) {
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

    public Organism getOrganismAt(Position pos){
        for(Organism organism:allOrganisms){
            if(organism.getPosition().equals(pos)){
                return organism;
            }
        }
        return null;
    }

    public void clearPosition(Position position) {
        SwingUtilities.invokeLater(() -> {
            drawPanel.getCells()[position.getX()][position.getY()].setBackground(Color.BLACK);
        });
    }

    public boolean isOccupied(Position pos) {
        return allOccupiedPositions.contains(pos);
    }

    public void updateOrganismPosition(Organism organism, Position oldPos, Position newPos){
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