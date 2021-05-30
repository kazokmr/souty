package io.cucumber.shouty;

import io.cucumber.java8.En;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class StepDefinitions implements En {

    private Person lucy;
    private Person sean;
    private String messageFromSean;

    public StepDefinitions() {
        Given("Lucy is located {int} metres from Sean", (Integer distance) -> {
            lucy = new Person();
            sean = new Person();
            lucy.moveTo(distance);
        });
        When("Sean shouts {string}", (String message) -> {
            sean.shout(message);
            messageFromSean = message;
        });
        Then("Lucy hears Sean's message", () -> {
            assertThat(lucy.getMessagesHeard()).isEqualTo(Collections.singletonList(messageFromSean));
        });
    }
}
