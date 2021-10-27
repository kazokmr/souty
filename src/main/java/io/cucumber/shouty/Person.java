package io.cucumber.shouty;

import java.util.ArrayList;
import java.util.List;

public class Person {

    private final Network network;
    private final int location;
    private final List<String> messagesHeard = new ArrayList<>();
    private int credits;

    public Person(Network network, int location) {
        this.network = network;
        this.network.subscribe(this);
        this.location = location;
        this.credits = 0;
    }

    public void shout(String message) {
        network.broadcast(message, this);
    }

    public List<String> getMessagesHeard() {
        return messagesHeard;
    }

    public void hear(String message) {
        messagesHeard.add(message);
    }

    public int getLocation() {
        return location;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }
}
