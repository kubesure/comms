package io.kubesure.comms;

import java.io.IOException;
import java.util.logging.Logger;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class SMSSubscriber {

    static Logger logger = Logger.getLogger(SMSSubscriber.class.getName());

    @KafkaListener(topics = "policyissued", groupId = "3")
    public void processMessage(String policyIssued) {
        logger.info("Sending SMS...." + policyIssued);
        ObjectMapper mapper = new ObjectMapper();
        try {
            PolicyIssuedEvent data = mapper.readValue(policyIssued, PolicyIssuedEvent.class);
            AmazonSNS sns = AmazonSNSClientBuilder.standard().withRegion(Regions.US_EAST_1).build();
            String msg = "Kubesure: You EasyHealth Policy with number " + data.getPolicyNumber() + " has been issued";
            PublishRequest publishReq = new PublishRequest("arn:aws:sns:us-east-1:168485357055:PolicyIssued", msg);
            PublishResult result = sns.publish(publishReq);
            logger.info(result.getMessageId());
        } catch (IOException e) {
            logger.severe(e.toString());
            e.printStackTrace();
        }
    }
}