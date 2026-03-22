package es.vicfuen02.webhookHandler.requestHandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;


@ComponentScan(basePackages = "es.vicfuen02.webhookHandler")
@SpringBootApplication(scanBasePackages = "es.vicfuen02.webhookHandler")
@EnableJpaRepositories(basePackages = "es.vicfuen02.webhookHandler")
@EntityScan(basePackages = "es.vicfuen02.webhookHandler")
@EnableScheduling
@Slf4j
public class RequestHandlerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RequestHandlerServiceApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void onReady(ApplicationReadyEvent event) {
		log.info("APP {} STARTED", this.getClass().getSimpleName());
	}

}
