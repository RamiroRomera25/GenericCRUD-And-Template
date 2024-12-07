package rami.generic.services.stateMachine;

import lombok.AccessLevel;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

@Getter
public class StateMachine<S, R> {
    private final Map<S, List<Transition<S, R>>> stateMap = new HashMap<>();
    private S currentState;

    public StateMachine(S initialState) {
        this.currentState = initialState;
    }

    public void addTransition(S fromState, S toState, BiFunction<S, Object[], R> action) {
        stateMap.computeIfAbsent(fromState, k -> new ArrayList<>())
                .add(new Transition<>(fromState, toState, action));
    }

    public R executeTransition(S toState, Object... args) {
        List<Transition<S, R>> transitions = stateMap.get(currentState);
        if (transitions != null) {
            for (Transition<S, R> transition : transitions) {
                if (transition.getNextState().equals(toState)) {
                    R result = transition.getAction().apply(currentState, args);
                    currentState = toState;
                    return result;
                }
            }
        }
        throw new IllegalStateException("No valid transition found for the requested state.");
    }
}
