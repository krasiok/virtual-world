package com.virtual_world;


import com.virtual_world.animal.*;
import com.virtual_world.plant.DeadlyNightshade;
import com.virtual_world.plant.Grass;
import com.virtual_world.plant.Guarana;
import com.virtual_world.plant.Milkweed;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
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


    private void drawBoard() {
        JFrame frame = new JFrame();
        frame.setSize(1024, 768);


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

    private void createOrganism(BiFunction<Position, World, Organism> creator, int count, World world) {
        for (int i = 0; i < count; i++) {
            Position pos = randomPosition();
            while (world.getAllOccupiedPositions().contains(pos)) {
                pos = randomPosition();
            }

            Organism org = creator.apply(pos, world);
            world.addOrganism(org);
        }

    }


    private void drawOrganisms() {
        createOrganism(Wolf::new, 5, world);
//        createOrganism(Sheep::new, 4, world);
//        createOrganism(Fox::new, 4, world);
//        createOrganism(Tortoise::new, 4, world);
//        createOrganism(Grass::new, 4, world);
//        createOrganism(Milkweed::new, 4, world);
//        createOrganism(Guarana::new,8,world);
//        createOrganism(DeadlyNightshade::new,5,world);
          createOrganism(Antelope::new, 5, world);

    }


    private Position randomPosition() {
        int randomX = (int) (Math.random() * world.getRows());
        int randomY = (int) (Math.random() * world.getColumns());

        return new Position(randomX, randomY);
    }

    public JPanel[][] getCells() {
        return cells;
    }


}
