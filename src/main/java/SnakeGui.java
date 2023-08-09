import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SnakeGui extends JFrame implements MouseListener, ActionListener {

    private Game game;
    private JPanel window, gamePanel, scorePanel, buttonPanel, highScorePanel, choicePanel;
    private JButton yesButton, noButton, pauseButton, resetButton, highScoreButton;
    private JLabel scoreLabel, highScoreLabel, choiceLabel;
    private int row;
    private int col;
    private final int cellSize;

    public SnakeGui() {
        Dimension boardSize = new Dimension(500, 500);
        cellSize = 500 / 10;
        int marginSize = 16;
        Border border = BorderFactory.createEmptyBorder(marginSize, marginSize, marginSize, marginSize);

        window = new JPanel(new BorderLayout());
        getContentPane().add(window);

        gamePanel = new JPanel(new GridLayout(50, 50));
        gamePanel.setPreferredSize(boardSize);
        gamePanel.setBounds(0, 0, boardSize.width, boardSize.height);
        window.add(gamePanel, BorderLayout.CENTER);

        scorePanel = new JPanel();
        window.add(scorePanel, BorderLayout.NORTH);

        buttonPanel = new JPanel();
        window.add(buttonPanel, BorderLayout.SOUTH);

        resetGame();
        createBoard();
    }

    private void createBoard() {
        for (int i = 0; i < 2500; i++) {
            JPanel cell = new JPanel();
            gamePanel.add(cell);

            row = (i / 50);
            col = (i % 50);
            CellType currentCellType = game.getBoard().getCell(row, col).getCellType();

            if (currentCellType == CellType.EMPTY) {
                cell.setBackground(Color.WHITE);
            } else if (currentCellType == CellType.SNAKE_SEGMENT) {
                cell.setBackground(Color.BLACK);
            } else if (currentCellType == CellType.FOOD) {
                cell.setBackground(Color.GREEN);
            }
        }
    }

    private void updateBoard() {

    }

    private void resetGame() {
        // maybe change starting position / cell?    also set direction?
        Snake snake = new Snake(new Cell(25, 25));
        Board board = new Board(50, 50);
        this.game = new Game(snake, board);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == resetButton) {
            resetGame();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
