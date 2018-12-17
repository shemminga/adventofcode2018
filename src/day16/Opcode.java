package day16;

import java.util.Arrays;
import java.util.stream.Stream;

enum Opcode {
    ADDR((a, b, c, regs) -> regs[c] = regs[a] + regs[b]),
    ADDI((a, b, c, regs) -> regs[c] = regs[a] + b),

    MULR((a, b, c, regs) -> regs[c] = regs[a] * regs[b]),
    MULI((a, b, c, regs) -> regs[c] = regs[a] * b),

    BANR((a, b, c, regs) -> regs[c] = regs[a] & regs[b]),
    BANI((a, b, c, regs) -> regs[c] = regs[a] & b),

    BORR((a, b, c, regs) -> regs[c] = regs[a] | regs[b]),
    BORI((a, b, c, regs) -> regs[c] = regs[a] | b),

    SETR((a, b, c, regs) -> regs[c] = regs[a]),
    SETI((a, b, c, regs) -> regs[c] = a),

    GTIR((a, b, c, regs) -> regs[c] = a > regs[b] ? 1 : 0),
    GTRI((a, b, c, regs) -> regs[c] = regs[a] > b ? 1 : 0),
    GTRR((a, b, c, regs) -> regs[c] = regs[a] > regs[b] ? 1 : 0),

    EQIR((a, b, c, regs) -> regs[c] = a == regs[b] ? 1 : 0),
    EQRI((a, b, c, regs) -> regs[c] = regs[a] == b ? 1 : 0),
    EQRR((a, b, c, regs) -> regs[c] = regs[a] == regs[b] ? 1 : 0);

    private final OpcodeFunction operation;

    Opcode(final OpcodeFunction operation) {
        this.operation = operation;
    }

    void apply(final int a, final int b, final int c, final int[] regs) {
        operation.apply(a, b, c, regs);
    }

    int[] applyToCopy(final int a, final int b, final int c, final int[] regs) {
        final int[] regsCopy = Arrays.copyOf(regs, regs.length);
        operation.apply(a, b, c, regsCopy);
        return regsCopy;
    }

    static Stream<Opcode> stream() {
        return Arrays.stream(Opcode.values());
    }

    @FunctionalInterface
    interface OpcodeFunction {
        void apply(int a, int b, int c, int[] regs);
    }
}
