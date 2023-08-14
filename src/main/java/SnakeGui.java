import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

public class SnakeGui extends JFrame implements MouseListener, ActionListener, KeyListener {

    private Game game;
    private JPanel window, gamePanel, scorePanel, buttonPanel, highScorePanel, continuePanel, pausePanel;
    private JButton yesButton, noButton, pauseButton, resetButton, highScoreButton, quitButton;
    private JLabel scoreLabel, highScoreLabel, continueLabel, pauseLabel;
    private static final int FPS = 30;
    private int row;
    private int col;
    private final int cellSize;
    private Timer timer;

    public SnakeGui() {
        Dimension boardSize = new Dimension(500, 500);
        cellSize = 500 / 10;
        int marginSize = 16;
        Border border = BorderFactory.createEmptyBorder(marginSize, marginSize, marginSize, marginSize);

        // create main window and the score, game, and button panels
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

        // add buttons to button panel
        resetButton = new JButton("Reset");
        pauseButton = new JButton("Pause");
        highScoreButton = new JButton("High Scores");
        quitButton = new JButton("Quit");

        Dimension buttonSize = new Dimension(100, 50);
        resetButton.setPreferredSize(buttonSize);
        pauseButton.setPreferredSize(buttonSize);
        highScoreButton.setPreferredSize(buttonSize);
        quitButton.setPreferredSize(buttonSize);

        resetButton.addActionListener(this);
        pauseButton.addActionListener(this);
        highScoreButton.addActionListener(this);
        quitButton.addActionListener(this);

        buttonPanel.add(resetButton);
        buttonPanel.add(pauseButton);
        buttonPanel.add(highScoreButton);
        buttonPanel.add(quitButton);

        // prepare JFrame to take key input
        addKeyListener(this);
        setFocusable(true);

        // set up new game and create game board from it
        resetGame();
        createBoard();

        timer = new Timer(1000/FPS, e -> {
            updateBoard();
            repaint();
        });
        timer.start();
    }

    private void createBoard() {
        for (int i = 0; i < 2500; i++) {
            JPanel cell = new JPanel(new BorderLayout());
            gamePanel.add(cell);

            int row = (i / 50);
            int col = (i % 50);
            CellType currentCellType = game.getBoard().getCell(row, col).getCellType();

            if (currentCellType == CellType.EMPTY) {
                cell.setBackground(Color.BLACK);
            } else if (currentCellType == CellType.SNAKE_SEGMENT) {
                cell.setBackground(Color.GREEN);
            } else if (currentCellType == CellType.FOOD) {
                cell.setBackground(Color.RED);
            }
        }
    }

    private void updateBoard() {
        this.game.update();
        for (int i = 0; i < 2500; i++) {
            JPanel cell = (JPanel)gamePanel.getComponent(i);

            int row = (i / 50);
            int col = (i % 50);
            CellType currentCellType = game.getBoard().getCell(row, col).getCellType();

            if (currentCellType == CellType.EMPTY) {
                cell.setBackground(Color.BLACK);
            } else if (currentCellType == CellType.SNAKE_SEGMENT) {
                cell.setBackground(Color.GREEN);
            } else if (currentCellType == CellType.FOOD) {
                cell.setBackground(Color.RED);
            }
        }
    }

    private void resetGame() {
        // maybe change starting position / cell?    also set direction?
        Board board = new Board(50, 50);
        Cell center = board.getCell(25, 25);
        Snake snake = new Snake(center);
        this.game = new Game(snake, board);
    }

    private void showHighScores() {
    }

    private void showQuitDialogue() {
        JOptionPane.showConfirmDialog(window, "Are you sure you want to quit?", "Quit", JOptionPane.YES_NO_OPTION);
    }

    private void showContinueDialogue() {
        JOptionPane.showConfirmDialog(window, "Continue?", "Pause", JOptionPane.YES_NO_OPTION);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == resetButton) {
            resetGame();
        } else if (e.getSource() == highScoreButton) {
            showHighScores();
        } else if (e.getSource() == pauseButton) {
            showContinueDialogue();
        } else if (e.getSource() == quitButton) {
            showQuitDialogue();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_UP) {
            this.game.setDirection(Game.DIRECTION_UP);
        } else if (keyCode == KeyEvent.VK_DOWN) {
            this.game.setDirection(Game.DIRECTION_DOWN);
        } else if (keyCode == KeyEvent.VK_LEFT) {
            this.game.setDirection(Game.DIRECTION_LEFT);
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            this.game.setDirection(Game.DIRECTION_RIGHT);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

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
