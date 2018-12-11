package day11;

final class PowerLevelCalculator {
    private PowerLevelCalculator() {
    }

    static int calculatePowerLevel(final int x, final int y, final int serialNo) {
        final int rackId = x + 10;
        int powerLevel = rackId * y;
        powerLevel += serialNo;
        powerLevel *= rackId;
        powerLevel = hundredsDigit(powerLevel);
        powerLevel -= 5;
        return powerLevel;
    }

    private static int hundredsDigit(final int powerLevel) {
        return (powerLevel / 100) % 10;
    }
}
