package rami.generic.services.stateMachine;

import lombok.AccessLevel;
import lombok.Getter;

import java.util.*;
import java.util.function.Function;

@Getter
public class StateMachine<S, P, R> {

    @Getter(AccessLevel.NONE)
    private final Map<S, List<Transition<S, P, R>>> stateMap = new HashMap<>();

    private S currentState;

    public StateMachine(S initialState) {
        this.currentState = initialState;
    }

    public void addTransition(S fromState, S toState, Function<P, R> action) {
        stateMap.computeIfAbsent(fromState, k -> new ArrayList<>())
                .add(new Transition<>(fromState, toState, action));
    }

    public R executeTransition(S toState, P input) {
        List<Transition<S, P, R>> transitions = stateMap.get(currentState);
        if (transitions != null) {
            for (Transition<S, P, R> transition : transitions) {
                if (transition.getNextState().equals(toState)) {
                    R result = transition.getAction().apply(input);
                    currentState = toState;
                    return result;
                }
            }
        }
        throw new IllegalStateException("No valid transition found for the requested state.");
    }
}
