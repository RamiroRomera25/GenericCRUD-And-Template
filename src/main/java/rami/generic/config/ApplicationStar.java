package rami.generic.config;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStar implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("\u001B[32m\n" +
        " █████  ██████  ██████      ███████ ████████  █████  ██████  ████████ ███████ ██████  \n" +
        "██   ██ ██   ██ ██   ██     ██         ██    ██   ██ ██   ██    ██    ██      ██   ██ \n" +
        "███████ ██████  ██████      ███████    ██    ███████ ██████     ██    █████   ██   ██ \n" +
        "██   ██ ██      ██               ██    ██    ██   ██ ██   ██    ██    ██      ██   ██ \n" +
        "██   ██ ██      ██          ███████    ██    ██   ██ ██   ██    ██    ███████ ██████  \u001B[0m\n");
    }
}

