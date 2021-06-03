package io.cucumber.hello;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import java.time.DayOfWeek;

public class HelloDefinition {

    private DayOfWeek today;
    private String answer;

    @Given("今日は日曜日")
    public void 今日は日曜日() {
        today = DayOfWeek.SUNDAY;
    }

    @When("今日が金曜日か尋ねたら")
    public void 今日が金曜日か尋ねたら() {
        answer = IsItFriday.ask(today);
    }

    @Then("「{word}」と答える")
    public void いいえ_と答える(String expectedAnswer) {
        Assertions.assertEquals(expectedAnswer, answer);
    }
}

class IsItFriday {
    public static String ask(DayOfWeek today) {
        return today == DayOfWeek.FRIDAY ? "はい" : "いいえ";
    }
}
