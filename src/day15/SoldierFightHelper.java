package day15;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

final class SoldierFightHelper {
    private SoldierFightHelper() {
    }

    static void letAttack(final Soldier soldier, final List<Soldier> soldiers) {
        final Optional<Soldier> optTarget = soldiers.stream()
                .filter(Soldier::isAlive)
                .filter(s -> s.getType() != soldier.getType())
                .filter(s1 -> Math.abs(soldier.getY() - s1.getY()) + Math.abs(soldier.getX() - s1.getX()) == 1)
                .min(Comparator.comparingInt(Soldier::getHitpoints)
                        .thenComparingInt(Soldier::getY)
                        .thenComparingInt(Soldier::getX));

        optTarget.ifPresent(target -> {
            target.hit(soldier.getAttackPower());
        });
    }
}
