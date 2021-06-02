package io.cucumber.shouty;

import java.util.ArrayList;
import java.util.List;

public class Network {

    private final int range;
    private final List<Person> listeners = new ArrayList<>();

    public Network(int range) {
        this.range = range;
    }

    public void subscribe(Person person) {
        listeners.add(person);
    }

    public void broadcast(String message, int shouterLocation) {
        if (message.length() > 180) {
            return;
        }
        listeners.stream()
                .filter(listener -> Math.abs(listener.getLocation() - shouterLocation) <= range)
                .forEach(listener -> listener.hear(message));
    }
}
