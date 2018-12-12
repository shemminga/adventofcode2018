package day12;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

final class ConwaysGameOfPlants {
    private static final Pattern INITIAL_STATE_PATTERN = Pattern.compile("^initial state: (.*)$");
    private static final Pattern TRANSITION_PATTERN = Pattern.compile("^([.#]{5}) => ([.#])$");
    private static final Pattern CLEAN_UP_PATTERN = Pattern.compile("^([.]*)(#[.#]*#)[.]*$");

    private final Map<String, Character> transitions;
    private String state;
    private int stateStart;
    private int generation;

    ConwaysGameOfPlants(final String initialState, final Map<String, Character> transitions) {
        state = initialState;
        stateStart = 0;
        generation = 0;
        this.transitions = transitions;

        cleanUpState();
    }

    public String getState() {
        return state;
    }

    void nextGeneration() {
        prepareForNewGeneration();

        final char[] newState = new char[state.length()];
        newState[0] = '.';
        newState[1] = '.';
        newState[newState.length - 2] = '.';
        newState[newState.length - 1] = '.';

        for (int i = 2; i < newState.length - 2; i++) {
            final String substate = state.substring(i - 2, i + 3);
            newState[i] = transitions.getOrDefault(substate, '.');
        }

        state = new String(newState);
        cleanUpState();
    }

    private void prepareForNewGeneration() {
        state = "....." + state + ".....";
        stateStart -= 5;
        generation++;
    }

    private void cleanUpState(){
        final Matcher matcher = CLEAN_UP_PATTERN.matcher(state);
        matcher.find();
        stateStart += matcher.group(1).length();
        state = matcher.group(2);
    }

    @Override
    public String toString() {
        return String.format("%3d: %3d %s (%d)", generation, stateStart, state, getPlantSum());
    }

    private int getPlantSum() {
        int sum = 0;
        for (int i = 0; i < state.length(); i++) {
            if (state.charAt(i) == '#') {
                sum += stateStart + i;
            }
        }
        return sum;
    }

    static ConwaysGameOfPlants of(final List<String> lines) {
        final String initialState = getInitialState(lines);
        lines.remove(0);

        final Map<String, Character> transitions = lines.stream()
                .map(ConwaysGameOfPlants::getTransition)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        return new ConwaysGameOfPlants(initialState, transitions);
    }

    private static Map.Entry<String, Character> getTransition(final String str) {
        final Matcher matcher = TRANSITION_PATTERN.matcher(str);

        matcher.find();
        final String key = matcher.group(1);
        final Character value = matcher.group(2).charAt(0);

        return new AbstractMap.SimpleImmutableEntry<>(key, value);
    }

    private static String getInitialState(final List<String> lines) {
        final String initialStateLine = lines.remove(0);
        final Matcher matcher = INITIAL_STATE_PATTERN.matcher(initialStateLine);
        matcher.find();
        return matcher.group(1);
    }
}
