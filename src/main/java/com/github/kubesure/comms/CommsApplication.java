package com.github.kubesure.comms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.apache.log4j.Logger;

@SpringBootApplication
public class CommsApplication {

	//static Logger log = Logger.getLogger(CommsApplication.class.getName());

	public static void main(String[] args) {
		//log.info("comms listener");
		System.out.println("comms server started");
		SpringApplication.run(CommsApplication.class, args);
	}
}
