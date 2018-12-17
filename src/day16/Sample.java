package day16;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

final class Sample {
    private static final Pattern BEFORE = Pattern.compile("^Before:\\s+\\[(\\d+), (\\d+), (\\d+), (\\d+)]$");
    private static final Pattern INSTRUCTION = Pattern.compile("^(\\d+) (\\d+) (\\d+) (\\d+)$");
    private static final Pattern AFTER = Pattern.compile("^After:\\s+\\[(\\d+), (\\d+), (\\d+), (\\d+)]$");
    private final int opcode;
    private final int a;
    private final int b;
    private final int c;
    private final int[] regsBefore;
    private final int[] regsAfter;

    private Sample(final int opcode, final int a, final int b, final int c, final int[] regsBefore, final int[] regsAfter) {
        this.opcode = opcode;
        this.a = a;
        this.b = b;
        this.c = c;
        this.regsBefore = regsBefore;
        this.regsAfter = regsAfter;
    }

    int getOpcode() {
        return opcode;
    }

    int getA() {
        return a;
    }

    int getB() {
        return b;
    }

    int getC() {
        return c;
    }

    int[] getRegsBefore() {
        return regsBefore;
    }

    int[] getRegsAfter() {
        return regsAfter;
    }

    @Override
    public String toString() {
        return "Sample{" + "opcode=" + opcode + ", a=" + a + ", b=" + b + ", c=" + c + ", regsBefore=" +
                Arrays.toString(regsBefore) + ", regsAfter=" + Arrays.toString(regsAfter) + '}';
    }

    static Sample of(final String beforeLine, final String instructionLine, final String afterLine) {
        final int[] regsBefore = extractRegs(BEFORE.matcher(beforeLine));
        final int[] regsAfter = extractRegs(AFTER.matcher(afterLine));

        // Fun hack
        final int[] instruction = extractRegs(INSTRUCTION.matcher(instructionLine));
        final int opcode = instruction[0];
        final int a = instruction[1];
        final int b = instruction[2];
        final int c = instruction[3];

        return new Sample(opcode, a, b, c, regsBefore, regsAfter);
    }

    private static int[] extractRegs(final Matcher matcher) {
        matcher.find();

        final int[] regs = new int[4];
        regs[0] = Integer.valueOf(matcher.group(1));
        regs[1] = Integer.valueOf(matcher.group(2));
        regs[2] = Integer.valueOf(matcher.group(3));
        regs[3] = Integer.valueOf(matcher.group(4));
        return regs;
    }
}
