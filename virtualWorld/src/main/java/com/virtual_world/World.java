package com.virtual_world;

import com.virtual_world.animal.*;
import com.virtual_world.plant.*;

import java.util.*;
import java.util.function.BiFunction;

public class World {

    // --- 1. POLA (Stan obiektu) ---
    private int rows;
    private int columns;
    private List<Organism> allOrganisms = new ArrayList<>();
    private final PanelDrawer panelDrawer = new PanelDrawer(this);
    private final List<Position> allOccupiedPositions = new ArrayList<>();
    private PriorityQueue<Organism> initiativeQueue;

    // --- 2. KONSTRUKTORY (Tu brak jawnego, ale to byłoby ich miejsce) ---

    // --- 3. PUBLICZNE METODY BIZNESOWE (Główne funkcjonalności) ---

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


    // Metody operujące na organizmach (publiczne, bo mogą być wołane z zewnątrz)
    public void addOrganism(Organism organism) {
        allOrganisms.add(organism);
        allOccupiedPositions.add(organism.getPosition());
    }

    public void removeOrganism(Organism organism, Position position) {
        allOrganisms.remove(organism);
        allOccupiedPositions.remove(organism.getPosition());
        panelDrawer.clearCell(organism.getPosition());
    }

    public void updateOrganismPosition(Organism organism, Position oldPos, Position newPos) {
        allOccupiedPositions.remove(oldPos);
        allOccupiedPositions.add(newPos);
        organism.setPosition(newPos);
    }

    // --- 4. METODY PRYWATNE (Szczegóły implementacji logiki) ---

    private void createInitialOrganisms() {
        createOrganisms(Hogweed::new,2,this);
        createOrganisms(CyberSheep::new,1,this);
//        createOrganisms(Antelope::new,10,this);
//        createOrganisms(Sheep::new, 5, this);
//        createOrganisms(Wolf::new, 5, this);

//        createOrganisms(Tortoise::new, 2, this);
//        createOrganisms(Fox::new, 2, this);
//        createOrganisms(Antelope::new, 2, this);
//        createOrganisms(DeadlyNightshade::new, 2, this);
//        createOrganisms(Grass::new, 2, this);
//        createOrganisms(Guarana::new, 2, this);
//        createOrganisms(Milkweed::new, 2, this);
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


    // --- 5. ZAPYTANIA (Queries) ---
    // Metody, które nie są prostymi getterami, ale szukają danych

    public boolean isOccupied(Position pos) {
        return allOccupiedPositions.contains(pos);
    }

    private boolean isOrganismAlive(Organism organism) {
        return allOrganisms.contains(organism);
    }

    public boolean positionValid(Position position) {
        return position.getX() >= 0 && position.getX() < rows
                && position.getY() >= 0 && position.getY() < columns;
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

    // --- 6. GETTERY I SETTERY (Boilerplate na samym dole) ---

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public PanelDrawer getPanelDrawer() {
        return panelDrawer;
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