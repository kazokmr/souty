package io.cucumber.shouty;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.HashMap;
import java.util.Map;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

public class StepDefinitions {

    private String messageFromSean;
    private Network network;
    private Map<String, Person> people;

    @Before
    public void createNetwork() {
        network = new Network();
        people = new HashMap<>();
    }

    @Given("a person named {word}")
    public void aPersonNamedLucy(String name) {
        people.put(name, new Person(network));
    }

    @When("Sean shouts {string}")
    public void shouts(String message) {
        people.get("Sean").shout(message);
        messageFromSean = message;
    }

    @Then("Lucy hears Sean's message")
    public void hears_message() {
        assertThat(people.get("Lucy").getMessagesHeard()).isEqualTo(singletonList(messageFromSean));
    }
}
