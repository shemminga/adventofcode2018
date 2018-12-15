package day13;

final class Cart {
    private int x;
    private int y;
    private Turn nextTurn;
    private Direction direction;
    private boolean collided;

    Cart(final int x, final int y, final Direction direction) {
        this.x = x;
        this.y = y;
        nextTurn = Turn.LEFT;
        this.direction = direction;
        collided = false;
    }

    void moveStep() {
        switch (direction) {
        case UP:
            y--;
            break;
        case DOWN:
            y++;
            break;
        case LEFT:
            x--;
            break;
        case RIGHT:
            x++;
            break;
        }
    }

    void updateDirection(final char cell) {
        switch (cell) {
        case '|':
        case '-':
            return;
        case '\\':
        case '/':
            direction = direction.passCurve(cell);
            break;
        case '+':
            direction = passIntersection();
            nextTurn = nextTurn.getNext();
            break;
        default:
            throw new AssertionError(cell);
        }
    }

    private Direction passIntersection() {
        switch (nextTurn) {
        case LEFT:
            return direction.turnLeft();
        case RIGHT:
            return direction.turnRight();
        case STRAIGHT:
            return direction;
        }

        throw new AssertionError();
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    void setCollided() {
        collided = true;
    }

    boolean isCollided() {
        return collided;
    }

    @Override
    public String toString() {
        return "Cart{" + "x=" + x + ", y=" + y + ", nextTurn=" + nextTurn + ", direction=" + direction + ", collided=" +
                collided + '}';
    }
}
