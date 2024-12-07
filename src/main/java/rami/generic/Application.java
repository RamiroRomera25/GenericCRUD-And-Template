package rami.generic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import rami.generic.enums.ExampleStates;
import rami.generic.services.stateMachine.StateMachine;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	public static void stateMachine() {
		StateMachine<ExampleStates, Integer, String> stateMachine = new StateMachine<>(ExampleStates.INITIAL);

		// Agregar transiciones
		stateMachine.addTransition(ExampleStates.INITIAL, ExampleStates.IN_PROCESS, input -> {
			System.out.println("Holaaaa");
			return "Started processing with value: " + input;
		});
		stateMachine.addTransition(ExampleStates.IN_PROCESS, ExampleStates.FINISHED, input -> {
			return "Processing completed. Result: " + (input * 2);
		});
		stateMachine.addTransition(ExampleStates.IN_PROCESS, ExampleStates.CANCELLED, input -> {
			return "Processing failed with code: " + input;
		});

		// Ejecutar transiciones
		System.out.println("Current state: " + stateMachine.getCurrentState());
		System.out.println(stateMachine.executeTransition(ExampleStates.FINISHED, 42));
	}

}
