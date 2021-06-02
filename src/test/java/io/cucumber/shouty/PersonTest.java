package io.cucumber.shouty;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
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
        Person lucy = new Person(network, 100);
        verify(network).subscribe(lucy);
    }

    @Test
    void it_has_a_location() {
        Person person = new Person(network, 100);
        assertThat(person.getLocation()).isEqualTo(100);
    }

    @Test
    void broadcasts_shouts_to_the_network() {
        String message = "Free bagels!";
        Person sean = new Person(network, 0);
        sean.shout(message);
        verify(network).broadcast(message, 0);
    }

    @Test
    void remembers_messages_heard() {
        String message = "Free bagels!";
        Person lucy = new Person(network, 100);
        lucy.hear(message);
        assertThat(lucy.getMessagesHeard()).contains(message);
    }

}
