package rami.generic.services.stateMachine;

import java.util.function.Function;

public class Transition<S, P, R> {
    private final S currentState;
    private final S nextState;
    private final Function<P, R> action;

    public Transition(S currentState, S nextState, Function<P, R> action) {
        this.currentState = currentState;
        this.nextState = nextState;
        this.action = action;
    }

    public S getCurrentState() {
        return currentState;
    }

    public S getNextState() {
        return nextState;
    }

    public Function<P, R> getAction() {
        return action;
    }

}
