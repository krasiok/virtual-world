package com.virtual_world;


import com.virtual_world.animal.Sheep;
import com.virtual_world.animal.Wolf;


import javax.swing.*;
import java.awt.*;
import java.util.function.BiFunction;

public class DrawPanel {

    World world;
    private JPanel[][] cells;


    public DrawPanel(World world) {
        this.world = world;
    }

    public void drawWorld() {
        drawBoard();
        drawOrganisms();
    }

    private Position randomPosition() {
        int randomX = (int) (Math.random() * world.getRows());
        int randomY = (int) (Math.random() * world.getColumns());

        return new Position(randomX, randomY);
    }

    private void createOrganism(BiFunction<Position, World, Organism> creator, int count, World world) {
        for (int i = 0; i < count; i++) {
            Position pos = randomPosition();
            while (world.getAllOccupiedPositions().contains(pos)) {
                pos = randomPosition();
            }
            world.getAllOccupiedPositions().add(pos);
            Organism org = creator.apply(pos, world);
            cells[pos.getX()][pos.getY()].setBackground(org.getColor());
        }

    }

    private void drawBoard() {
        JFrame frame = new JFrame();
        frame.setSize(1920, 1080);


        JPanel panel = new JPanel(new GridLayout(world.getRows(), world.getColumns()));
        cells = new JPanel[world.getRows()][world.getColumns()];
        for (int i = 0; i < world.getRows(); i++) {
            for (int j = 0; j < world.getColumns(); j++) {

                cells[i][j] = new JPanel();
                cells[i][j].setBackground(Color.BLACK);

                panel.add(cells[i][j]);
            }
        }
        frame.add(panel);
        frame.setVisible(true);
    }

    private void drawOrganisms() {
        createOrganism(Wolf::new, 4, world);
        createOrganism(Sheep::new, 5, world);
    }

    public JPanel[][] getCells() {
        return cells;
    }


}
