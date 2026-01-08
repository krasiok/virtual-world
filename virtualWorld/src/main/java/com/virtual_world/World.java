package com.virtual_world;

import com.virtual_world.animal.*;
import com.virtual_world.plant.*;

import java.util.*;
import java.util.function.BiFunction;

public class World {

    // --- POLA ---
    private int rows;
    private int columns;
    private List<Organism> allOrganisms = new ArrayList<>();
    private final PanelDrawer panelDrawer = new PanelDrawer(this);
    private final List<Position> allOccupiedPositions = new ArrayList<>();
    private PriorityQueue<Organism> initiativeQueue;

    // --- METODY GŁÓWNE ---

    public void createWorld() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj rozmiar swiata: wiersze -> kolumny");
        rows = scanner.nextInt();
        columns = scanner.nextInt();

        createInitialOrganisms();
        panelDrawer.drawWorld();
    }

    public void takeTurn() {
        sortOrganisms();
        processOrganismsTurns();
    }

    // --- IMPLEMENTACJA LOGIKI ---

    private void createInitialOrganisms() {

//        createOrganisms(Hogweed::new, 2, this);
//        createOrganisms(CyberSheep::new, 1, this);
        createOrganisms(Sheep::new, 2, this);
//        createOrganisms(Wolf::new, 2, this);
//        createOrganisms(Tortoise::new, 3, this);
//        createOrganisms(Antelope::new, 3, this);
        createHuman(); // might be lambda
    }

    private void createHuman() {
        Position humanPos = RandomUtil.getRandomPosition(rows, columns);
        while (isOccupied(humanPos)) {
            humanPos = RandomUtil.getRandomPosition(rows, columns);
        }

        Human human = new Human(humanPos, this, 0);
        addOrganism(human);
        panelDrawer.addKeyListener(human.getHumanListener());
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

            organism.increaseAge();

            Organism other = getOrganismAtExcluding(organism.getPosition(), organism);
            if (other != null) {
                organism.collision(other, false);
            }

            panelDrawer.refreshUIAfterMove(previousPosition);

        }
    }

    private void createOrganisms(BiFunction<Position, World, Organism> creator, int count, World world) {
        for (int i = 0; i < count; i++) {
            Position pos = RandomUtil.getRandomPosition(rows, columns);
            while (world.getAllOccupiedPositions().contains(pos)) {
                pos = RandomUtil.getRandomPosition(rows, columns);
            }
            Organism org = creator.apply(pos, world);
            world.addOrganism(org);
        }
    }

    // --- ZARZĄDZANIE POZYCJAMI ---

    public void updateOrganismPosition(Organism organism, Position oldPos, Position newPos) {
        allOccupiedPositions.remove(oldPos);
        allOccupiedPositions.add(newPos);
        organism.setPosition(newPos);
    }

    public void addOrganism(Organism organism) {
        allOrganisms.add(organism);
        allOccupiedPositions.add(organism.getPosition());
    }

    public void removeOrganism(Organism organism, Position position) {
        allOrganisms.remove(organism);
        allOccupiedPositions.remove(position);
        panelDrawer.clearCell(position);
    }

    // --- GETTERY / POMOCNICZE ---

    public boolean isOccupied(Position pos) {
        return allOccupiedPositions.contains(pos);
    }

    public Organism getOrganismAt(Position pos) {
        for (Organism organism : allOrganisms) {
            if (organism.getPosition().equals(pos)) {
                return organism;
            }
        }
        return null;
    }


    public Organism getOrganismAtExcluding(Position pos, Organism exclude) {
        for (Organism organism : allOrganisms) {
            if (organism != exclude && organism.getPosition().equals(pos)) {
                return organism;
            }
        }
        return null;
    }

    private boolean isOrganismAlive(Organism organism) {
        return allOrganisms.contains(organism);
    }



    public boolean positionValid(Position position) {
        return position.getX() >= 0 && position.getX() < rows
                && position.getY() >= 0 && position.getY() < columns;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public List<Organism> getAllOrganisms() {
        return allOrganisms;
    }

    public List<Position> getAllOccupiedPositions() {
        return allOccupiedPositions;
    }

    public PanelDrawer getPanelDrawer() {
        return panelDrawer;
    }
}
