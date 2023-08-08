

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
            }
        }
    }

    public Cell[][] getBoard() { return this.cells; }

    public void setCells(Cell[][] cells) {
        this.cells = cells;
    }

    public void generateFood() {
        int row = 0;
        int col = 0;

        // find an empty cell and break from loop
        while(true) {
            row = (int) (Math.random() * ROW_COUNT);
            col = (int) (Math.random() * COL_COUNT);
            if (cells[row][col].getCellType() != CellType.SNAKE_SEGMENT)
                break;
        }
        cells[row][col].setCellType(CellType.FOOD);
    }
}
