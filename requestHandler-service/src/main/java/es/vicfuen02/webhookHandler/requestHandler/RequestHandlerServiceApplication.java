package es.vicfuen02.webhookHandler.requestHandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@Slf4j
@ComponentScan(basePackages = {
		"es.vicfuen02.webhookHandler.requestHandler",
		"es.vicfuen02.webhookHandler.common"
})
public class RequestHandlerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RequestHandlerServiceApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void onReady(ApplicationReadyEvent event) {
		log.info("APP {} STARTED", this.getClass().getSimpleName());
	}

}
