package io.cucumber.shouty;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class NetworkTest {

    private final int range = 100;
    private final Network network = new Network(range);
    private final String message = "Free bagels!";

    @Test
    void broadcasts_a_message_to_a_listener_within_range() {
        int locationAtSean = 0;
        Person lucy = mock(Person.class);
        network.subscribe(lucy);
        network.broadcast(message, locationAtSean);
        verify(lucy).hear(message);
    }

    @Test
    void does_not_broadcast_a_message_to_a_listener_out_of_range() {
        int locationAtSean = 0;
        Person laura = mock(Person.class);
        when(laura.getLocation()).thenReturn(101);
        network.subscribe(laura);
        network.broadcast(message, locationAtSean);
        verify(laura, never()).hear(message);
    }

    @Test
    void does_not_broadcast_a_message_to_a_listener_out_of_range_negative_distance() {
        int locationAtSean = 101;
        Person lionel = mock(Person.class);
        when(lionel.getLocation()).thenReturn(0);
        network.subscribe(lionel);
        network.broadcast(message, locationAtSean);
        verify(lionel, never()).hear(message);
    }
}