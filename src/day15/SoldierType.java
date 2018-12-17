package day15;

enum SoldierType {
    ELF,
    GOBLIN;

    static SoldierType ofChar(final char c) {
        if (c == 'E') {
            return ELF;
        }
        if (c == 'G') {
            return GOBLIN;
        }

        throw new AssertionError(c);
    }
}
