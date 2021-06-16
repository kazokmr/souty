package io.cucumber.shouty.support;

import java.util.Objects;

public class Whereabouts {
    private final String name;
    private final Integer location;

    public Whereabouts(String name, Integer location) {
        this.name = name;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public Integer getLocation() {
        return location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Whereabouts that = (Whereabouts) o;
        return Objects.equals(name, that.name) && Objects.equals(location, that.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, location);
    }

    @Override
    public String toString() {
        return "Whereabouts{" +
                "name='" + name + '\'' +
                ", location=" + location +
                '}';
    }
}
