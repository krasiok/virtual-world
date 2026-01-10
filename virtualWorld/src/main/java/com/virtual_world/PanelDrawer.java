package com.virtual_world;

import com.virtual_world.animal.*;
import com.virtual_world.plant.*;
import com.virtual_world.ability.Ability;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PanelDrawer {

    private final World world;
    private JPanel[][] cells;
    private final JFrame frame;

    private JLabel xpLabel;
    private JLabel strengthLabel;
    private JPanel abilitiesPanel;
    private final List<JPanel> abilitySlots = new ArrayList<>();

    private final Map<Class<?>, BufferedImage> imageCache = new HashMap<>();

    public PanelDrawer(World world) {
        this.world = world;
        this.frame = new JFrame("Virtual World - RPG");
        this.frame.setSize(1280, 800);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        loadImages();
        frame.setLayout(new BorderLayout());
        initSidePanel();
    }

    private void initSidePanel() {
        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
        sidePanel.setPreferredSize(new Dimension(250, 800));
        sidePanel.setBackground(new Color(50, 50, 50));
        sidePanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        xpLabel = new JLabel("XP: 0");
        xpLabel.setForeground(new Color(255, 215, 0));
        xpLabel.setFont(new Font("Arial", Font.BOLD, 24));
        xpLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        sidePanel.add(xpLabel);
        sidePanel.add(Box.createRigidArea(new Dimension(0, 20)));

        JLabel skillsHeader = new JLabel("Umiejętności:");
        skillsHeader.setForeground(Color.WHITE);
        skillsHeader.setFont(new Font("Arial", Font.BOLD, 18));
        skillsHeader.setAlignmentX(Component.CENTER_ALIGNMENT);
        sidePanel.add(skillsHeader);
        sidePanel.add(Box.createRigidArea(new Dimension(0, 10)));

        abilitiesPanel = new JPanel();
        abilitiesPanel.setLayout(new BoxLayout(abilitiesPanel, BoxLayout.Y_AXIS));
        abilitiesPanel.setBackground(new Color(50, 50, 50));
        sidePanel.add(abilitiesPanel);

        sidePanel.add(Box.createRigidArea(new Dimension(0, 20)));

        strengthLabel = new JLabel("Strength: 0");
        strengthLabel.setForeground(new Color(255, 100, 100));
        strengthLabel.setFont(new Font("Arial", Font.BOLD, 20));
        strengthLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        sidePanel.add(strengthLabel);

        frame.add(sidePanel, BorderLayout.EAST);
    }

    private void loadImages() {
        registerImage(Human.class, "resources/human.png");
        registerImage(Wolf.class, "resources/wolf.png");
        registerImage(Sheep.class, "resources/sheep.png");
        registerImage(Fox.class, "resources/fox.png");
        registerImage(Tortoise.class, "resources/turtle.png");
        registerImage(Antelope.class, "resources/antelope.png");
        registerImage(CyberSheep.class, "resources/cybersheep.png");

        registerImage(Grass.class, "resources/grass.png");
        registerImage(DeadlyNightshade.class, "resources/nightshade.png");
        registerImage(Guarana.class, "resources/guarana.png");
        registerImage(Milkweed.class, "resources/milkweed.png");
        registerImage(Hogweed.class, "resources/hogweed.png");
        registerImage(LuckyPlant.class, "resources/luckyplant.png");
    }

    private void registerImage(Class<?> clazz, String path) {
        try {
            BufferedImage img = ImageIO.read(new File(path));
            imageCache.put(clazz, img);
        } catch (IOException e) {
            System.out.println("Couldn't find an image");
        }
    }

    public void addKeyListener(KeyListener listener) {
        frame.addKeyListener(listener);
        frame.setFocusable(true);
        frame.requestFocus();
    }

    public void drawWorld() {
        drawBoardPanel();
        drawOrganisms(world.getAllOrganisms());
        frame.setVisible(true);
    }

    private void drawBoardPanel() {
        JPanel boardPanel = new JPanel(new GridLayout(world.getRows(), world.getColumns()));
        cells = new JPanel[world.getRows()][world.getColumns()];

        for (int i = 0; i < world.getRows(); i++) {
            for (int j = 0; j < world.getColumns(); j++) {
                cells[i][j] = new JPanel();
                cells[i][j].setFocusable(false);
                cells[i][j].setLayout(new BorderLayout());
                cells[i][j].setBackground(new Color(193, 154, 107));
                cells[i][j].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));

                JLabel label = new JLabel();
                label.setHorizontalAlignment(SwingConstants.CENTER);
                label.setFocusable(false);
                cells[i][j].add(label, BorderLayout.CENTER);

                boardPanel.add(cells[i][j]);
            }
        }
        frame.add(boardPanel, BorderLayout.CENTER);
    }

    public void drawOrganisms(List<Organism> organisms) {
        for (Organism organism : organisms) {
            paintCell(organism);
        }
    }

    public void paintCell(Organism organism) {
        if (world.positionValid(organism.getPosition())) {
            int x = organism.getPosition().getX();
            int y = organism.getPosition().getY();

            BufferedImage img = imageCache.get(organism.getClass());

            if (img != null) {
                if (cells[x][y].getComponentCount() > 0) {
                    JLabel label = (JLabel) cells[x][y].getComponent(0);
                    int width = cells[x][y].getWidth() > 0 ? cells[x][y].getWidth() : 40;
                    int height = cells[x][y].getHeight() > 0 ? cells[x][y].getHeight() : 40;
                    Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
                    label.setIcon(new ImageIcon(scaledImg));
                }
            } else {
                cells[x][y].setBackground(organism.getColor());
            }
        }
    }

    public void clearCell(Position position) {
        if (world.positionValid(position)) {
            int x = position.getX();
            int y = position.getY();
            if (cells[x][y].getComponentCount() > 0) {
                JLabel label = (JLabel) cells[x][y].getComponent(0);
                label.setIcon(null);
            }
            cells[x][y].setBackground(new Color(193, 154, 107));
        }
    }

    public void refreshUIAfterMove(Position previousPosition) {
        clearCell(previousPosition);

        List<Organism> organismsCopy = new ArrayList<>(world.getAllOrganisms());
        drawOrganisms(organismsCopy);

        updateStatsPanel(organismsCopy);

        frame.repaint();
        Main.napTime();
    }

    private void updateStatsPanel(List<Organism> organisms) {
        Human human = null;

        for (Organism o : organisms) {
            if (o instanceof Human) {
                human = (Human) o;
                break;
            }
        }

        if (human != null) {
            xpLabel.setText("XP: " + human.getXp());
            strengthLabel.setText("Strength: " + human.getStrength());

            List<Ability> abilities = human.getAbilities();

            if (abilitySlots.isEmpty()) {
                abilitiesPanel.removeAll();
                for (int i = 0; i < abilities.size(); i++) {
                    JPanel slot = createAbilitySlot(i + 1);
                    abilitySlots.add(slot);
                    abilitiesPanel.add(slot);
                    abilitiesPanel.add(Box.createRigidArea(new Dimension(0, 10)));
                }
            }

            for (int i = 0; i < abilities.size(); i++) {
                if (i < abilitySlots.size()) {
                    updateAbilitySlot(abilitySlots.get(i), abilities.get(i));
                }
            }
        }
    }

    private JPanel createAbilitySlot(int index) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(220, 60));
        panel.setMaximumSize(new Dimension(220, 60));
        panel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));

        JLabel keyLabel = new JLabel("[" + index + "] ");
        keyLabel.setFont(new Font("Monospaced", Font.BOLD, 16));
        keyLabel.setForeground(Color.BLACK);
        panel.add(keyLabel, BorderLayout.WEST);

        JTextArea infoArea = new JTextArea();
        infoArea.setEditable(false);
        infoArea.setOpaque(false);
        infoArea.setFont(new Font("Arial", Font.BOLD, 12));
        infoArea.setForeground(Color.BLACK);
        panel.add(infoArea, BorderLayout.CENTER);

        return panel;
    }

    private void updateAbilitySlot(JPanel slot, Ability ability) {
        JTextArea text = (JTextArea) ((BorderLayout) slot.getLayout()).getLayoutComponent(BorderLayout.CENTER);

        String name = ability.getClass().getSimpleName();

        if (ability.isActive()) {
            slot.setBackground(new Color(144, 238, 144));
            text.setText(name + "\nACTIVE\nTurns left: " + ability.getDuration());
        } else if (ability.getCooldown() > 0) {
            slot.setBackground(new Color(211, 211, 211));
            text.setText(name + "\nCOOLDOWN\n: " + ability.getCooldown());
        } else {
            slot.setBackground(Color.WHITE);
            text.setText(name + "\nREADY\nCOST: " + ability.getAbilityType().getCost() + " XP");
        }
    }
}