package io.cucumber.shouty;

import java.util.List;

public record Person(String name) {

    public void moveTo(Integer distance) {
    }

    public void shout(String message) {

    }

    public List<String> getMessagesHeard() {
        return List.of("free bagels at Sean's");
    }
}
