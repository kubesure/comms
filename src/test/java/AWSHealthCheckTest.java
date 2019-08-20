import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import io.kubesure.comms.AWSCommsHealthCheck;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AWSHealthCheckTest.class)
public class AWSHealthCheckTest {

    @Test
    public void TestHealth() {
        AWSCommsHealthCheck test = new AWSCommsHealthCheck();
        Health health = test.health();
        assertEquals("UP",health.getStatus().getCode());
    }
}