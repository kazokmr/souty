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

    @Given("Lucy is located {int} metre(s) from Sean")
    public void located_metres(Integer distance) {
        Network network = new Network();
        lucy = new Person(network);
        sean = new Person(network);
        lucy.moveTo(distance);
    }

    @When("Sean shouts {string}")
    public void shouts(String message) {
        sean.shout(message);
        messageFromSean = message;
    }

    @Then("Lucy hears Sean's message")
    public void hears_message() {
        assertThat(lucy.getMessagesHeard()).isEqualTo(Collections.singletonList(messageFromSean));
    }
}
