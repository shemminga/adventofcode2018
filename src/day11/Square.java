package day11;

final class Square {
    private final int x;
    private final int y;
    private final int size;
    private final int totalPower;

    Square(final int x, final int y, final int size, final int totalPower) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.totalPower = totalPower;
    }

    @Override
    public String toString() {
        return x + "," + y + ',' + size + " (total power: " + totalPower + ')';
    }
}
