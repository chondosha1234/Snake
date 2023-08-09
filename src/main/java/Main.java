import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        SnakeGui snakeGui = new SnakeGui();
        snakeGui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        snakeGui.setTitle("Snake Game");
        snakeGui.pack();
        snakeGui.setVisible(true);
    }
}
