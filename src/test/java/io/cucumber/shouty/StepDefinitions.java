package io.cucumber.shouty;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class StepDefinitions {

    private static final int DEFAULT_RANGE = 100;
    private String messageFromSean;
    private Network network = new Network(DEFAULT_RANGE);
    private Map<String, Person> people;

    @Before
    public void createNetwork() {
        people = new HashMap<>();
    }

    @Given("the range is {int}")
    public void the_range_is(Integer range) {
        network = new Network(range);
    }

    @Given("a person named {word}")
    public void aPersonNamed(String name) {
        people.put(name, new Person(network, 0));
    }

    @Given("a person named {word} is located at {int}")
    public void a_person_named_sean_is_located_at(String name, Integer location) {
        people.put(name, new Person(network, location));
    }

    @When("Sean shouts {string}")
    public void shouts(String message) {
        people.get("Sean").shout(message);
        messageFromSean = message;
    }

    @When("Sean shouts")
    public void seanShouts() {
        people.get("Sean").shout("Hello Cucumber!");
    }

    @Then("Lucy should hear Sean's message")
    public void hears_message() {
        assertThat(people.get("Lucy").getMessagesHeard()).contains(messageFromSean);
    }

    @Then("Lucy should hear")
    public void lucyShouldHear() {
        assertThat(people.get("Lucy").getMessagesHeard().size()).isGreaterThanOrEqualTo(1);
    }

    @Then("Lucy should not hear")
    public void lucyShouldNotHear() {
        assertThat(people.get("Lucy").getMessagesHeard()).isEmpty();
    }
}
