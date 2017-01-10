package email_sender_microservice;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConnectionHandlingTest {
    ConnectionHandling connectionHandling;

    @Before
    public void setUp() throws Exception {
        connectionHandling  = new ConnectionHandling();
        System.out.println("Setting up...");
    }

    @After
    public void tearDown() throws Exception {
        connectionHandling = null;
        System.out.println("Tearing down...");
    }

    @Test
    public void getPropValues_Should_Return_Data() throws Exception {

        assertEquals("postgres", connectionHandling.getPropValues().get("database"));

        System.out.println("The getPropValuesOfEmail_Should_Return_Data method ok...");
    }

}