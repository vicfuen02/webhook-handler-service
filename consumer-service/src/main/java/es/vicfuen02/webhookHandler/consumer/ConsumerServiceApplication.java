package es.vicfuen02.webhookHandler.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@Slf4j
public class ConsumerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumerServiceApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void onReady(ApplicationReadyEvent event) {
		log.info("APP {} STARTED", this.getClass().getSimpleName());
	}

}
