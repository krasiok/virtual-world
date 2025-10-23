import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class World {
    public int rows;
    public int columns;

    public void createWorld() {



        Scanner scanner = new Scanner(System.in);
        System.out.println("Select world size: rows -> columns");
        rows = scanner.nextInt();
        columns = scanner.nextInt();

        Wolf wolf1 = new Wolf(randomPosition());
        Sheep sheep1 = new Sheep(randomPosition());


        JFrame frame = new JFrame();
        frame.setSize(1920, 1080);


        JPanel panel = new JPanel(new GridLayout(rows, columns));
        JPanel[][] cells = new JPanel[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {

                cells[i][j] = new JPanel();
                cells[i][j].setBackground(Color.BLACK);

                panel.add(cells[i][j]);
            }
        }
        frame.add(panel);
        frame.setVisible(true);

        cells[wolf1.position.getX()][wolf1.position.getY()].setBackground(Color.GRAY);
        cells[sheep1.position.getX()][sheep1.position.getY()].setBackground(Color.WHITE);

    }

    public void takeTurn(){

    }

    private Position randomPosition() {
        int randomX = (int) (Math.random() * rows+1);
        int randomY = (int) (Math.random() * columns+1); // magic +1

        return new Position(randomX,randomY);
    }
}
