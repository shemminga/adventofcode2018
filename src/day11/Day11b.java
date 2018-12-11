package day11;

final class Day11b {
    private Day11b() {
    }

    public static void main(final String... args) {
        System.out.println(new FuelCellGridB(18).getMaxSquare());
        System.out.println(new FuelCellGridB(42).getMaxSquare());
        System.out.println(new FuelCellGridB(8772).getMaxSquare());
    }
}
