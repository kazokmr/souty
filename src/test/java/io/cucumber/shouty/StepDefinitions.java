package io.cucumber.shouty;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.DataTableType;
import io.cucumber.java.Transpose;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.shouty.support.Whereabouts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class StepDefinitions {

    private static final int DEFAULT_RANGE = 100;
    private Network network = new Network(DEFAULT_RANGE);
    private Map<String, Person> people;
    private Map<String, List<String>> messagesShoutedBy;

    @DataTableType
    public Whereabouts defineWhereabouts(Map<String, String> entry) {
        return new Whereabouts(entry.get("name"), Integer.parseInt(entry.get("location")));
    }

    @Before
    public void createNetwork() {
        people = new HashMap<>();
        messagesShoutedBy = new HashMap<>();
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

    @Given("Sean has bought {int} credits")
    public void seanHasBoughtCredits(int credits) {
        people.get("Sean").setCredits(credits);
    }

    @When("Sean shouts")
    public void seanShouts() {
        shout("Hello Cucumber!");
    }

    @When("Sean shouts {string}")
    public void shouts(String message) {
        shout(message);
    }

    @When("Sean shouts a message containing the word {string}")
    public void seanShoutsAMessageContainingTheWord(String word) {
        shout("a message containing the word " + word);
    }

    @When("Sean shouts the following message")
    public void sean_shouts_the_following_message(String message) {
        shout(message);
    }

    private void shout(String message) {
        people.get("Sean").shout(message);
        messagesShoutedBy.computeIfAbsent("Sean", s -> new ArrayList<>());
        messagesShoutedBy.get("Sean").add(message);
    }

    @Then("Lucy should hear Sean's message")
    public void hears_message() {
        List<String> heardByLucy = people.get("Lucy").getMessagesHeard();
        List<String> messagesFromSean = messagesShoutedBy.get("Sean");
        assertThat(heardByLucy).containsAll(messagesFromSean);
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

    @Then("Lucy hears all Sean's messages")
    public void lucyHearsAllSeanSMessages() {
        List<String> heardByLucy = people.get("Lucy").getMessagesHeard();
        List<String> messagesFromSean = messagesShoutedBy.get("Sean");
        assertThat(heardByLucy).containsAll(messagesFromSean);
    }

    @Then("Sean should have {int} credits")
    public void seanShouldHaveCredits(int credits) {
        int seanHasCredits = people.get("Sean").getCredits();
        assertThat(seanHasCredits).isEqualTo(credits);
    }
}
