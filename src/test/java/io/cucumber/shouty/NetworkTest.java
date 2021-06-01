package io.cucumber.shouty;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class NetworkTest {

    @Test
    void broadcasts_a_message_to_all_listeners() {
        Network network = new Network();
        String message = "Free bagels!";
        Person lucy = Mockito.mock(Person.class);
        network.subscribe(lucy);
        network.broadcast(message);
        Mockito.verify(lucy).hear(message);
    }
}
