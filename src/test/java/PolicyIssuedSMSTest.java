import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import io.kubesure.comms.EmailSubscriber;
import io.kubesure.comms.PolicyIssuedEvent;
import io.kubesure.comms.SMSSubscriber;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EmailSubscriber.class)
public class PolicyIssuedSMSTest {
    //@Autowired EmailSubscriber sub; 
    @Test
    public void testPolicyIssuedSMS() throws JsonProcessingException {
        SMSSubscriber sub = new SMSSubscriber();
        PolicyIssuedEvent event = new PolicyIssuedEvent();
        event.setPolicyNumber(12344555);
        event.setQuoteNumber(1234556);
        event.setQuoteNumber(23432424);
        ObjectMapper mapper = new ObjectMapper();
        sub.processMessage(mapper.writeValueAsString(event));
    }
}