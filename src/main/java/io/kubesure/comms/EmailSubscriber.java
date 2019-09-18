package io.kubesure.comms;

import java.io.IOException;
import java.util.logging.Logger;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/*
 * Listens to policy issued events to send email to the policy holder. 
 */
@Component
public class EmailSubscriber {

    static Logger logger = Logger.getLogger(EmailSubscriber.class.getName());
    // @Autowired ObjectMapper mapper;

    @KafkaListener(topics = "policyissued", groupId = "2")
    public void processMessage(String policyIssused) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            PolicyIssuedEvent data = mapper.readValue(policyIssused, PolicyIssuedEvent.class);
            logger.info("sending email..." + data.getPolicyNumber());
            PolicyEmail email = new PolicyEmail();
            email.setFrom("edakghar@gmail.com");
            email.setTo("pras.p.in@gmail.com");
            email.setSubject("Kubesure: EasyHealth Policy Number " + data.getPolicyNumber());
            email.setBody(
                    "We are thrilled to have you as our customer and look forward to serve you health needs. " +
                    "We have attached the policy document for you reference.");
            email.setSignature("thanks and regards - Kubesure");
            String emailcontent = mapper.writeValueAsString(email);
            logger.info(emailcontent);
            // AmazonS3 s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_1).build();
            AmazonS3 s3 = AmazonS3ClientBuilder.defaultClient();
            PutObjectResult result = s3.putObject("kubesure-cs-1", String.valueOf(data.getPolicyNumber()), emailcontent);
            logger.info(result.getVersionId());
        } catch (IOException e) {
            logger.severe(e.toString());
            e.printStackTrace();
        }
    }
}