package io.kubesure.comms;

import java.util.logging.Logger;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Component
public class MobileSubscriber {

    static Logger logger = Logger.getLogger(MobileSubscriber.class.getName());

    @KafkaListener(topics="policyissued", groupId = "1")
    public void processMessage(String message) {
        logger.info("Sending push msg to mobile" + message);
    }
}