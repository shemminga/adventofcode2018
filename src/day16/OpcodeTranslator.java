package day16;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

final class OpcodeTranslator {
    private final Map<Sample, List<Opcode>> sampleOpcodesMap;
    private final Opcode[] opcodes = new Opcode[16];

    OpcodeTranslator(final List<Sample> samples) {
         sampleOpcodesMap = samples.stream()
                .collect(Collectors.toMap(s -> s, s -> Opcode.stream()
                        .filter(opcode -> Arrays.equals(s.getRegsAfter(),
                                opcode.applyToCopy(s.getA(), s.getB(), s.getC(), s.getRegsBefore())))
                        .collect(Collectors.toList())));
    }

    Opcode[] translate() {
        for (int i = 0; i < 16; i++) {
            findOneAndReduce();
        }
        return opcodes;
    }

    private void findOneAndReduce() {
        final Map.Entry<Sample, List<Opcode>> entry = sampleOpcodesMap.entrySet()
                .stream()
                .filter(e -> e.getValue()
                        .size() == 1)
                .findFirst()
                .orElseThrow(AssertionError::new);

        final int opcodeInt = entry.getKey().getOpcode();
        final Opcode opcode = entry.getValue().get(0);
        opcodes[opcodeInt] = opcode;

        sampleOpcodesMap.entrySet().removeIf(e -> e.getKey().getOpcode() == opcodeInt);
        sampleOpcodesMap.forEach((key, value) -> value.remove(opcode));
    }
}
