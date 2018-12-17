package day15;

import java.util.stream.Stream;

public final class Soldier {
    private final SoldierType type;
    private int attackPower;
    private int hitpoints;
    private int y;
    private int x;

    Soldier(final int y, final int x, final SoldierType type) {
        this.y = y;
        this.x = x;
        this.type = type;
        hitpoints = 200;
        attackPower = 3;
    }

    SoldierType getType() {
        return type;
    }

    int getAttackPower() {
        return attackPower;
    }

    int getHitpoints() {
        return hitpoints;
    }

    int getY() {
        return y;
    }

    int getX() {
        return x;
    }

    void setY(final int y) {
        this.y = y;
    }

    void setX(final int x) {
        this.x = x;
    }

    Coords getCoords() {
        return new Coords(y, x);
    }

    boolean isDead() {
        return hitpoints <= 0;
    }

    boolean isAlive() {
        return hitpoints > 0;
    }

    Stream<Coords> adjacents() {
        return Stream.of(new Coords(y - 1, x), new Coords(y + 1, x), new Coords(y, x - 1), new Coords(y, x + 1));
    }

    void hit(final int attack) {
        hitpoints -= attack;
    }

    void setAttackPower(final int newAttackPower) {
        attackPower = newAttackPower;
    }
}
