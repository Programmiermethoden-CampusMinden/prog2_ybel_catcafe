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
        int cmp = Integer.compare(this.weight, o.weight);
        if (cmp != 0) return cmp;
        return this.name.compareTo(o.name);
    }

}
