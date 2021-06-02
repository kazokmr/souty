package io.cucumber.shouty.support;

import io.cucumber.java.ParameterType;
import io.cucumber.shouty.Network;
import io.cucumber.shouty.Person;

public class ParameterTypes {

    @ParameterType("0|100")
    public Person person(String range) {
        return new Person(new Network(Integer.parseInt(range)), 0);
    }
}
