package day8;

import java.util.Deque;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

final class LicenseNode {
    private final List<LicenseNode> childNodes;
    private final List<Integer> metadataEntries;

    private LicenseNode(final List<LicenseNode> childNodes, final List<Integer> metadataEntries) {
        this.childNodes = childNodes;
        this.metadataEntries = metadataEntries;
    }

    int checksumA() {
        final int childSum = childNodes.stream()
                .mapToInt(LicenseNode::checksumA)
                .sum();

        final int metadataSum = metadataEntries.stream()
                .mapToInt(x -> x)
                .sum();

        return childSum + metadataSum;
    }

    int checksumB() {
        if (childNodes.isEmpty()) {
            return metadataEntries.stream()
                    .mapToInt(x -> x)
                    .sum();
        }

        return metadataEntries.stream()
                .mapToInt(x -> x)
                .filter(x -> x > 0)
                .filter(x -> x <= childNodes.size())
                .map(x -> childNodes.get(x - 1).checksumB())
                .sum();
    }

    @Override
    public String toString() {
        return "LicenseNode{" + "childNodes=" + childNodes + ", metadataEntries=" + metadataEntries + '}';
    }

    static LicenseNode of(final Deque<Integer> license) {
        final Integer nrOfChildren = Objects.requireNonNull(license.pollFirst());
        final Integer nrOfMetadataEntries = Objects.requireNonNull(license.pollFirst());

        final List<LicenseNode> childNodes = IntStream.range(0, nrOfChildren)
                .mapToObj(ignore -> of(license))
                .collect(Collectors.toList());

        final List<Integer> metadataEntries = IntStream.range(0, nrOfMetadataEntries)
                .mapToObj(ignore -> Objects.requireNonNull(license.pollFirst()))
                .collect(Collectors.toList());

        return new LicenseNode(childNodes, metadataEntries);
    }
}
