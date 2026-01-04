package com.virtual_world;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PanelDrawer {

    World world;
    private JPanel[][] cells;


    public PanelDrawer(World world) {
        this.world = world;
    }


    public void drawWorld() {
        drawPanel();
        drawOrganisms(world.getAllOrganisms());
    }


    private void drawPanel() {
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


    public void drawOrganisms(List<Organism> organisms) {
        for (Organism organism : organisms) {
            paintCell(organism);
        }
    }

    public void paintCell(Organism organism) {
        int posX = organism.getPosition().getX();
        int posY = organism.getPosition().getY();

        cells[posX][posY].setBackground(organism.getColor());
    }

    public void refreshUIAfterMove(Position previousPosition) {

        world.clearPosition(previousPosition);

        List<Organism> organismsCopy = new ArrayList<>(world.getAllOrganisms());

        drawOrganisms(organismsCopy);
        Main.napTime();

    }

    public JPanel[][] getCells() {
        return cells;
    }

}
