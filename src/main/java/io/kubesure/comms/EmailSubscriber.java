package io.kubesure.comms;

import java.util.logging.Logger;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class EmailSubscriber {

    static Logger logger = Logger.getLogger(CommsApplication.class.getName());

    @KafkaListener(topics="policyissued", groupId = "2")
    public void processMessage(String message) {
        logger.info("sending email for message received " + message);
    }
}