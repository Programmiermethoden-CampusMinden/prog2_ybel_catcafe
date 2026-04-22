package catcafe;

/**
 * A purring feline overlord.
 *
 */
public final class FelineOverLord implements Comparable<FelineOverLord> {
    private final String name;
    private final int weight;

    /**
     * Create a new cat.
     *
     * @param name   name of the cat
     * @param weight weight of the cat
     */
    public FelineOverLord(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }

    public String name() {
        return name;
    }

    public int weight() {
        return weight;
    }

    @Override
    public int compareTo(FelineOverLord o) {
        return weight - o.weight;
    }

    @Override
    public String toString() {
        return "FelineOverLord[" +
            "name=" + name + ", " +
            "weight=" + weight + ']';
    }

}
