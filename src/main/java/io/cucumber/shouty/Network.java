package io.cucumber.shouty;

import java.util.ArrayList;
import java.util.List;

public class Network {

    private final List<Person> listeners = new ArrayList<>();

    public void subscribe(Person person) {
        listeners.add(person);
    }

    public void broadcast(String message) {
        listeners.forEach(person -> person.hear(message));
    }
}
