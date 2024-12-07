package rami.generic.services.stateMachine;

import lombok.Getter;

import java.util.function.BiFunction;

@Getter
public class Transition<S, R> {
    private final S currentState;
    private final S nextState;
    private final BiFunction<S, Object[], R> action;

    public Transition(S currentState, S nextState, BiFunction<S, Object[], R> action) {
        this.currentState = currentState;
        this.nextState = nextState;
        this.action = action;
    }
}
