package io.cucumber.shouty;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.DataTableType;
import io.cucumber.java.Transpose;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.shouty.support.Whereabouts;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Given("people are located at")
    public void people_are_located_at(@Transpose List<Whereabouts> whereabouts) {
        whereabouts.forEach(row -> people.put(row.getName(), new Person(network, row.getLocation())));
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

    @When("Sean shouts the following message")
    public void sean_shouts_the_following_message(String message) {
        people.get("Sean").shout(message);
        messageFromSean = message;
//        System.out.println(message);
    }

    @Then("Lucy should hear Sean's message")
    public void hears_message() {
        assertThat(people.get("Lucy").getMessagesHeard()).contains(messageFromSean);
    }


    @Then("{word} should hear a shout")
    public void lucyShouldHear(String name) {
        assertThat(people.get(name).getMessagesHeard().size()).isGreaterThanOrEqualTo(1);
    }

    @Then("{word} should not hear a shout")
    public void lucyShouldNotHear(String name) {
        assertThat(people.get(name).getMessagesHeard()).isEmpty();
    }

    @Then("Lucy hears the following messages:")
    public void lucy_hears_the_following_messages(DataTable expectedMessages) {
        List<List<String>> actualMessages =
                people.get("Lucy").getMessagesHeard().stream().map(List::of).collect(Collectors.toList());
        expectedMessages.diff(DataTable.create(actualMessages));
    }

    @DataTableType
    public Whereabouts defineWhereabouts(Map<String, String> entry) {
        return new Whereabouts(entry.get("name"), Integer.parseInt(entry.get("location")));
    }

}
