
public class Game {

    public static final int DIRECTION_NONE = 0;
    public static final int DIRECTION_RIGHT = 1;
    public static final int DIRECTION_LEFT = -1;
    public static final int DIRECTION_UP = 2;
    public static final int DIRECTION_DOWN = -2;
    private Snake snake;
    private Board board;
    private int direction;
    private int score;
    private boolean gameOver;

    public Game(Snake snake, Board board) {
        this.snake = snake;
        this.board = board;
        this.direction = DIRECTION_NONE;
        this.score = 0;
    }

    public Snake getSnake() {
        return this.snake;
    }

    public void setSnake(Snake snake) {
        this.snake = snake;
    }

    public Board getBoard() {
        return this.board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isGameOver() {
        return this.gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public int getDirection() {
        return this.direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public void update() {
        if (!gameOver) {
            if (direction != DIRECTION_NONE) {
                Cell nextCell = getNextCell(snake.getHead());

                //check if crash and end game
                if (snake.checkCrash(nextCell)) {
                    setDirection(DIRECTION_NONE);
                    setGameOver(true);
                } else {
                    // get cell type before move, because the move always sets it to Snake Body type
                    CellType nextCellType = nextCell.getCellType();
                    snake.move(nextCell);
                    // if player gets food, snake grows and make new food
                    if (nextCellType == CellType.FOOD) {
                        snake.grow();
                        setScore(this.score + 1);
                        board.generateFood();
                    }
                }
            }
        }
    }

    private Cell getNextCell(Cell currentPosition) {
        int row = currentPosition.getRow();
        int col = currentPosition.getCol();

        if (direction == DIRECTION_RIGHT) {
            col++;
        } else if (direction == DIRECTION_LEFT) {
            col--;
        } else if (direction == DIRECTION_UP) {
            row--;
        } else if (direction == DIRECTION_DOWN) {
            row++;
        }
        if (board.hitWall(row, col)) {
            setGameOver(true);
            return snake.getHead();
        } else {
            Cell nextCell = board.getCell(row, col);
            return nextCell;
        }
    }
}
