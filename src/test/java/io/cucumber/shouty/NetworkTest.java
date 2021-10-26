package io.cucumber.shouty;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

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
        Person sean = new Person(network, 0);
        Person lucy = mock(Person.class);
        network.subscribe(lucy);
        network.broadcast(message, sean);
        verify(lucy).hear(message);
    }

    @Test
    void does_not_broadcast_a_message_to_a_listener_out_of_range() {
        Person sean = new Person(network, 0);
        Person laura = mock(Person.class);
        when(laura.getLocation()).thenReturn(101);
        network.subscribe(laura);
        network.broadcast(message, sean);
        verify(laura, never()).hear(message);
    }

    @Test
    void does_not_broadcast_a_message_to_a_listener_out_of_range_negative_distance() {
        Person sally = new Person(network, 101);
        Person lionel = mock(Person.class);
        when(lionel.getLocation()).thenReturn(0);
        network.subscribe(lionel);
        network.broadcast(message, sally);
        verify(lionel, never()).hear(message);
    }

    @Test
    void does_not_broadcast_a_message_over_180_characters_even_if_listener_is_in_range() {
        Person sean = new Person(network, 0);

        char[] chars = new char[181];
        Arrays.fill(chars, 'x');
        String longMessage = String.valueOf(chars);

        Person lucy = mock(Person.class);
        network.subscribe(lucy);
        network.broadcast(longMessage, sean);

        verify(lucy, never()).hear(longMessage);
    }
}