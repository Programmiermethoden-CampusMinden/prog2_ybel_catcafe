package catcafe;

import static java.util.Objects.requireNonNull;

import java.util.Optional;
import java.util.stream.StreamSupport;
import tree.Empty;
import tree.Tree;
import tree.TreeVisitor;

public class CatCafe {
    private Tree<FelineOverLord> clowder = new Empty<>();

    /**
     * Adds a cat to the Cat Cafe
     * @param cat the cat to be added, must not be {@code null}
     */
    public void addCat(FelineOverLord cat) {
        clowder = clowder.addData(requireNonNull(cat));
    }

    /**
     * Returns the number of cats in the cafe
     * @return the number of cats
     */
    public long getCatCount() {
        return clowder.size();
    }

    /**
     * Searches for a cat by name
     *
     * @param name the name of the cat to search for -> must not be {@code null}
     * @return an {@link Optional} containing the found cat, or an empty {@link Optional}
     */
    public FelineOverLord getCatByName(String name) {
        if (name == null) return null;

        for (FelineOverLord c : clowder) {
            if (c.name().equals(name)) return c;
        }

        return null;
    }

    /**
     * Searches for a cat with a specific wight range
     * @param minWeight the minimum weight of a cat, must be non-negative
     * @param maxWeight the maximum weight of a cat, must be greater or equal to minWeight
     * @return an {@link Optional} containing the founded cat, or an empty {@link Optional}
     */
    public FelineOverLord getCatByWeight(int minWeight, int maxWeight) {
        if (minWeight < 0) return null;
        if (maxWeight < minWeight) return null;

        for (FelineOverLord c : clowder) {
            if (c.weight() >= minWeight && c.weight() < maxWeight) return c;
        }

        return null;
    }

    /**
     * Accepts a visitior to the cafe
     * @param visitor the visitor to process the tree, must not be {@code null}
     * @return a string representation as a result of the traversal method
     */
    String accept(TreeVisitor<FelineOverLord> visitor) {
        return clowder.accept(visitor);
    }
}
