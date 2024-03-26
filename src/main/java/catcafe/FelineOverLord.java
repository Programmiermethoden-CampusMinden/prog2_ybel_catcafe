package catcafe;

/**
 * A purring feline overlord.
 *
 * @param name name of the cat
 * @param weight weight of the cat
 */
public record FelineOverLord(String name, int weight) implements Comparable<FelineOverLord> {
    @Override
    public int compareTo(FelineOverLord o) {
        return weight - o.weight;
    }
}
