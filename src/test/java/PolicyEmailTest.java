import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import io.kubesure.comms.EmailSubscriber;
import io.kubesure.comms.PolicyIssuedEvent;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EmailSubscriber.class)
public class PolicyEmailTest {
    @Test
    public void testPolicyIssuedEmail() throws JsonProcessingException {
        EmailSubscriber sub = new EmailSubscriber();
        PolicyIssuedEvent event = new PolicyIssuedEvent();
        event.setPolicyNumber(12344555);
        event.setQuoteNumber(1234556);
        event.setQuoteNumber(23432424);
        ObjectMapper mapper = new ObjectMapper();
        sub.processMessage(mapper.writeValueAsString(event));
    }
}