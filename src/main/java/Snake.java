import java.util.LinkedList;

public class Snake {

    private LinkedList<Cell> snakeBody = new LinkedList<>();
    private Cell head;

    public Snake(Cell initPos) {
        head = initPos;
        snakeBody.add(head);
        head.setCellType(CellType.SNAKE_SEGMENT);
    }

    public void grow() {
        snakeBody.add(head);
    }

    public void move(Cell nextCell) {
        Cell tail = snakeBody.removeLast();
        tail.setCellType(CellType.EMPTY);

        head = nextCell;
        head.setCellType(CellType.SNAKE_SEGMENT);
        snakeBody.addFirst(head);
    }

    public boolean checkCrash(Cell nextCell) {
        for (Cell cell : snakeBody) {
            if (cell == nextCell) {
                return true;
            }
            //or check if wall ?
        }
        return false;
    }

    public LinkedList<Cell> getSnakeBody() { return snakeBody; }

    public void setSnakeBody(LinkedList<Cell> snakeBody) {
        this.snakeBody = snakeBody;
    }

    public Cell getHead() { return head; }

    public void setHead(Cell head) {
        this.head = head;
    }

}
