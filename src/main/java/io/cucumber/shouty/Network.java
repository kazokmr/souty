package io.cucumber.shouty;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Network {

    private static final Pattern BUY_PATTERN = Pattern.compile("buy", Pattern.CASE_INSENSITIVE);
    private final int range;
    private final List<Person> listeners = new ArrayList<>();

    public Network(int range) {
        this.range = range;
    }

    public void subscribe(Person person) {
        listeners.add(person);
    }

    public void broadcast(String message, Person shouter) {
        int shouterLocation = shouter.getLocation();
        boolean shortEnough = message.length() <= 180;
        deductCredits(shortEnough, message, shouter);
        listeners.stream()
                .filter(listener -> Math.abs(listener.getLocation() - shouterLocation) <= range)
                .filter(listener -> shortEnough || shouter.getCredits() >= 0)
                .forEach(listener -> listener.hear(message));
    }

    private void deductCredits(boolean shortEnough, String message, Person shouter) {
        if (!shortEnough) {
            shouter.setCredits(shouter.getCredits() - 2);
        }
        if (BUY_PATTERN.matcher(message).find()) {
            shouter.setCredits(shouter.getCredits() - 5);
        }
    }
}
