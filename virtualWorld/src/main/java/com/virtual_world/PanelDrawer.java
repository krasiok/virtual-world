package com.virtual_world;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class PanelDrawer {

    private final World world;
    private JPanel[][] cells;
    private final JFrame frame;

    public PanelDrawer(World world) {
        this.world = world;
        this.frame = new JFrame("Wirtualny Świat");
        this.frame.setSize(1024, 768);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public void addKeyListener(KeyListener listener) {
        frame.addKeyListener(listener);
        frame.setFocusable(true);
        frame.requestFocus();
    }

    public void drawWorld() {
        drawPanel();
        drawOrganisms(world.getAllOrganisms());
        frame.setVisible(true);
    }

    private void drawPanel() {

        JPanel panel = new JPanel(new GridLayout(world.getRows(), world.getColumns()));
        cells = new JPanel[world.getRows()][world.getColumns()];

        for (int i = 0; i < world.getRows(); i++) {
            for (int j = 0; j < world.getColumns(); j++) {
                cells[i][j] = new JPanel();
                cells[i][j].setBackground(Color.BLACK);

                cells[i][j].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
                panel.add(cells[i][j]);
            }
        }

        frame.add(panel);

    }

    public void drawOrganisms(List<Organism> organisms) {
        for (Organism organism : organisms) {
            paintCell(organism);
        }
    }

    public void paintCell(Organism organism) {

        if (world.positionValid(organism.getPosition())) {
            int posX = organism.getPosition().getX();
            int posY = organism.getPosition().getY();
            cells[posX][posY].setBackground(organism.getColor());
        }
    }

    public void clearCell(Position position) {
        if (world.positionValid(position)) {
            cells[position.getX()][position.getY()].setBackground(Color.BLACK);
        }
    }

    public void refreshUIAfterMove(Position previousPosition) {
        clearCell(previousPosition);

        List<Organism> organismsCopy = new ArrayList<>(world.getAllOrganisms());

        drawOrganisms(organismsCopy);

        frame.revalidate();
        frame.repaint();

        Main.napTime();
    }

    public JPanel[][] getCells() {
        return cells;
    }
}

