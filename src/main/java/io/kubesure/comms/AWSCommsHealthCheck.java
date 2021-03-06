package io.kubesure.comms;

import java.util.logging.Logger;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.PropertySource;

/*
*AWSCommsHealthCheck is a k8s readiness probe api implemented by Spring boot acutator api to check
*if service is able to connect to and read S3 bucket key. 
*/
@Component
@PropertySource("classpath:application.properties")
public class AWSCommsHealthCheck implements HealthIndicator {

    static Logger logger = Logger.getLogger(AWSCommsHealthCheck.class.getName());
    @Value("${ping.bucket}")
    private String bucketName;
    @Value("${ping.key}")
    private String objectName;

    @Override
    public Health health() {
        logger.info("bucketName.." + bucketName);
        logger.info("objectName.." + objectName);

        AmazonS3 s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_1).build();
        try {
            S3Object obj = s3.getObject(new GetObjectRequest(bucketName, objectName));
            if (obj.getKey().equals(objectName)) {
                return Health.up().build();
            }
        } catch (Exception e) {
            logger.info(e.toString());
            return Health.down().build();
        }
        return null;

    }

}
