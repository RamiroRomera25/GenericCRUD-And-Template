package rami.generic;

import rami.generic.services.stateMachine.StateMachine;

public class StateApp {
    public void main(String[] args) {
        StateMachine<String, String> stateMachine = new StateMachine<>("INITIAL");

        // Agregar transiciones con métodos separados
        stateMachine.addTransition("INITIAL", "PROCESSING", (state, params) ->
                this.startProcessing(state, params)
        );

        stateMachine.addTransition("PROCESSING", "COMPLETED", (state, params) ->
                this.completeProcessing(state, params)
        );

        // Ejecutar transiciones
        System.out.println("Current state: " + stateMachine.getCurrentState());
        System.out.println(stateMachine.executeTransition("PROCESSING", "Alice", 42));

        System.out.println("Current state: " + stateMachine.getCurrentState());
        System.out.println(stateMachine.executeTransition("COMPLETED", 99.9));

        System.out.println("Current state: " + stateMachine.getCurrentState());
        System.out.println(stateMachine.executeTransition("INITIAL", 1));
    }

    public static String startProcessing(String currentState, Object... args) {
        String user = (String) args[0];
        int value = (int) args[1];
        return "Processing started in state " + currentState + " by " + user + " with value " + value;
    }

    public static String completeProcessing(String currentState, Object... args) {
        double result = (double) args[0];
        return "Processing completed in state " + currentState + ". Final result: " + result;
    }
}
