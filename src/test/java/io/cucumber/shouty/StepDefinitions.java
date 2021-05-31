package io.cucumber.shouty;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class StepDefinitions {

    private Person lucy;
    private Person sean;
    private String messageFromSean;

    @Given("{person} is located {int} metre(s) from Sean")
    public void located_metres(Person person, Integer distance) {
        lucy = person;
        person.moveTo(distance);
    }

    @When("{person} shouts {string}")
    public void shouts(Person person, String message) {
        person.shout(message);
        messageFromSean = message;
    }

    @Then("Lucy hears Sean's message")
    public void hears_message() {
        assertThat(lucy.getMessagesHeard()).isEqualTo(Collections.singletonList(messageFromSean));
    }
}
