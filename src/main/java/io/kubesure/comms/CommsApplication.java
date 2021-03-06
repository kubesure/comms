package io.kubesure.comms;

import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * Start the comms app. Comms app has various event listeners to business events occuring in kubesure.
 */

@SpringBootApplication
public class CommsApplication {

	static Logger logger = Logger.getLogger(CommsApplication.class.getName());

	public static void main(String[] args) {
		logger.info("comms listener started");
		SpringApplication.run(CommsApplication.class, args);		
	}
}
