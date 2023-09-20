

public class Board {

    final int ROW_COUNT, COL_COUNT;
    private Cell[][] cells;

    public Board(int rowCount, int columnCount) {
        ROW_COUNT = rowCount;
        COL_COUNT = columnCount;

        cells = new Cell[ROW_COUNT][COL_COUNT];
        for (int row = 0; row < ROW_COUNT; row++) {
            for (int col = 0; col < COL_COUNT; col++) {
                cells[row][col] = new Cell(row, col);
                cells[row][col].setCellType(CellType.EMPTY);
            }
        }
    }

    public Cell[][] getBoard() { return this.cells; }

    public void setCells(Cell[][] cells) {
        this.cells = cells;
    }

    public Cell getCell(int row, int col) {
        return cells[row][col];
    }

    /**
     * Checks if snake has hit the edge of board, or hit a wall cell, which is a type of cell used in harder game modes
     */
    public boolean hitWall(int row, int col) {
        return (row >= ROW_COUNT || row < 0 || col >= COL_COUNT || col < 0) || cells[row][col].getCellType() == CellType.WALL;
    }


    /**
     * Randomly choices cell that is not a snake or wall segment, and sets it to food
     */
    public void generateFood() {
        int row = 0;
        int col = 0;

        // find an empty cell and break from loop
        while(true) {
            row = (int) (Math.random() * ROW_COUNT);
            col = (int) (Math.random() * COL_COUNT);
            CellType type = cells[row][col].getCellType();
            if (type != CellType.SNAKE_SEGMENT && type != CellType.WALL)
                break;
        }
        cells[row][col].setCellType(CellType.FOOD);
    }
}
