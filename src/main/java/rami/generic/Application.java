package rami.generic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import rami.generic.enums.ExampleStates;
import rami.generic.services.stateMachine.StateMachine;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
//		SpringApplication.run(Application.class, args);
		new StateApp().main(null);
	}
}
