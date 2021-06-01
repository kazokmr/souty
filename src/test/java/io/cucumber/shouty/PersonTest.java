package io.cucumber.shouty;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;

import static org.mockito.Mockito.verify;

public class PersonTest {

    private AutoCloseable closeable;

    @Mock
    private Network network;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void it_subscribes_to_the_network() {
        Person lucy = new Person(network);
        verify(network).subscribe(lucy);
    }

    @Test
    void broadcasts_shouts_to_the_network() {
        String message = "Free bagels!";
        Person sean = new Person(network);
        sean.shout(message);
        verify(network).broadcast(message);
    }

    @Test
    void remembers_messages_heard() {
        String message = "Free bagels!";
        Person lucy = new Person(network);
        lucy.hear(message);
        Assertions.assertThat(lucy.getMessagesHeard()).containsAll(Collections.singletonList(message));
    }

}
