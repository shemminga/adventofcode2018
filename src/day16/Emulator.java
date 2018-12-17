package day16;

import java.util.Arrays;
import java.util.Deque;

final class Emulator {
    static int[] emulate(final Deque<String> instructions, final Opcode[] opcodeTable) {
        final int[] regs = new int[4];

        instructions.stream()
                .filter(s -> !s.isEmpty())
                .map(Emulator::splitLine)
                .forEach(ints -> opcodeTable[ints[0]].apply(ints[1], ints[2], ints[3], regs));

        return regs;
    }

    private static int[] splitLine(final String s) {
        return Arrays.stream(s.split(" "))
                .mapToInt(Integer::valueOf)
                .toArray();
    }
}
