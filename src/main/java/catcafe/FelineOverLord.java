package catcafe;

import java.util.Objects;

/**
 * Repräsentiert eine Katze mit Name und Gewicht.
 */
public record FelineOverLord(String name, int weight) implements Comparable<FelineOverLord> {

    @Override
    public int compareTo(FelineOverLord other) {
        return Integer.compare(this.weight, other.weight);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof FelineOverLord other)) return false;
        return name.equals(other.name) && weight == other.weight;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, weight);
    }

    @Override
    public String toString() {
        return "FelineOverLord[name=%s, weight=%d]".formatted(name, weight);
    }
}
