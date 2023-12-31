import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

public class SnakeGui extends JFrame implements MouseListener, ActionListener, KeyListener {

    private Game game;
    private final JPanel window, gamePanel, scorePanel, buttonPanel;
    private final JButton pauseButton, resetButton, highScoreButton, quitButton, modeButton;
    private JLabel scoreLabel, highScoreLabel, continueLabel, pauseLabel;
    private static final int FPS = 20;
    private Timer timer;
    private final int cellSize;

    public SnakeGui() {
        int width = 650;
        int height = 650;
        Dimension boardSize = new Dimension(width, height);
        cellSize = width / 10;
        int marginSize = 16;
        Border border = BorderFactory.createEmptyBorder(marginSize, marginSize, marginSize, marginSize);

        // create main window and the score, game, and button panels
        window = new JPanel(new BorderLayout());
        getContentPane().add(window);

        gamePanel = new JPanel(new GridLayout(cellSize, cellSize));
        gamePanel.setPreferredSize(boardSize);
        gamePanel.setBounds(0, 0, boardSize.width, boardSize.height);
        window.add(gamePanel, BorderLayout.CENTER);

        scorePanel = new JPanel();
        window.add(scorePanel, BorderLayout.NORTH);

        // add label to panel
        scoreLabel = new JLabel("");
        scorePanel.add(scoreLabel);

        buttonPanel = new JPanel();
        window.add(buttonPanel, BorderLayout.SOUTH);

        // add buttons to button panel
        resetButton = new JButton("Reset");
        pauseButton = new JButton("Pause");
        highScoreButton = new JButton("High Scores");
        quitButton = new JButton("Quit");
        modeButton = new JButton("Mode");

        Dimension buttonSize = new Dimension(100, 50);
        resetButton.setPreferredSize(buttonSize);
        pauseButton.setPreferredSize(buttonSize);
        highScoreButton.setPreferredSize(buttonSize);
        quitButton.setPreferredSize(buttonSize);
        modeButton.setPreferredSize(buttonSize);

        resetButton.addActionListener(this);
        pauseButton.addActionListener(this);
        highScoreButton.addActionListener(this);
        quitButton.addActionListener(this);
        modeButton.addActionListener(this);

        buttonPanel.add(resetButton);
        buttonPanel.add(pauseButton);
        buttonPanel.add(highScoreButton);
        buttonPanel.add(quitButton);
        buttonPanel.add(modeButton);

        // prepare JFrame to take key input
        addKeyListener(this);
        setFocusable(true);

        // set up new game and create game board from it
        resetGame();
        createBoard();
        runGame();
    }

    private void runGame() {
        if (timer != null) {
            timer.stop();
        }
        timer = new Timer(1000 / FPS, e -> {
            updateBoard();
            repaint();
        });
        timer.start();
    }

    private void createBoard() {
        for (int i = 0; i < (cellSize*cellSize); i++) {
            JPanel cell = new JPanel(new BorderLayout());
            gamePanel.add(cell);

            int row = (i / cellSize);
            int col = (i % cellSize);
            CellType currentCellType = game.getBoard().getCell(row, col).getCellType();

            if (currentCellType == CellType.EMPTY) {
                cell.setBackground(Color.BLACK);
            } else if (currentCellType == CellType.SNAKE_SEGMENT) {
                cell.setBackground(Color.GREEN);
            } else if (currentCellType == CellType.FOOD) {
                cell.setBackground(Color.RED);
            } else if (currentCellType == CellType.WALL) {
                cell.setBackground(Color.LIGHT_GRAY);
            }
        }

        scoreLabel.setText("Score: " + game.getScore());
    }

    private void updateBoard() {
        this.game.update();
        for (int i = 0; i < (cellSize*cellSize); i++) {
            JPanel cell = (JPanel)gamePanel.getComponent(i);

            int row = (i / cellSize);
            int col = (i % cellSize);
            CellType currentCellType = game.getBoard().getCell(row, col).getCellType();

            if (currentCellType == CellType.EMPTY) {
                cell.setBackground(Color.BLACK);
            } else if (currentCellType == CellType.SNAKE_SEGMENT) {
                cell.setBackground(Color.GREEN);
            } else if (currentCellType == CellType.FOOD) {
                cell.setBackground(Color.RED);
            }
        }
        scoreLabel.setText("Score: " + game.getScore());
    }

    private void resetGame() {
        // maybe change starting position / cell?    also set direction?
        Board board = new Board(cellSize, cellSize);
        Cell center = board.getCell(cellSize/2, cellSize/2);
        Snake snake = new Snake(center);
        board.generateFood();
        this.game = new Game(snake, board);
    }

    private void showHighScores() {
    }

    private void showQuitDialogue() {
        int choice = JOptionPane.showConfirmDialog(window, "Are you sure you want to quit?", "Quit", JOptionPane.YES_NO_OPTION);

        if (choice == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    private void showContinueDialogue() {
        String[] options = {"Continue"};
        JOptionPane.showOptionDialog(window, "Continue?", "Pause", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == resetButton) {
            resetGame();
            this.requestFocusInWindow();
            runGame();
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
