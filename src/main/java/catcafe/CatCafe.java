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
     * Adds a cat to the CatCafe.
     *
     * @param cat the cat to be added, must not be {@code null}
     */
    public void addCat(FelineOverLord cat) {
        clowder = clowder.addData(requireNonNull(cat));
    }

    /**
     * Returns the number of cats in the CatCafe.
     *
     * @return the number of cats
     */
    public long getCatCount() {
        return clowder.size();
    }

    /**
     * Searches for a cat by name.
     *
     * @param name the name of the cat to search for, must not be {@code null}
     * @return an {@link Optional} containing the found cat, or an empty {@link Optional} if no cat with the given name is found
     */
    public Optional<FelineOverLord> getCatByName(String name) {
        requireNonNull(name, "name must not be null");
        return StreamSupport.stream(clowder.spliterator(), false)
            .filter(cat -> cat.name().equals(name))
            .findFirst();
    }

    /**
     * Searches for a cat within a specific weight range.
     *
     * @param minWeight the minimum weight of the cat, must be non-negative
     * @param maxWeight the maximum weight of the cat, must be greater than or equal to {@code minWeight}
     * @return an {@link Optional} containing the found cat, or an empty {@link Optional} if no cat within the weight range is found
     * @throws IllegalArgumentException if {@code minWeight} is less than 0 or {@code maxWeight} is less than {@code minWeight}
     */
    public Optional<FelineOverLord> getCatByWeight(int minWeight, int maxWeight) {
        if (minWeight < 0) {
            throw new IllegalArgumentException("Minimum weight must be non-negative.");
        }
        if (maxWeight < minWeight) {
            throw new IllegalArgumentException("Maximum weight must be greater than or equal to minimum weight.");
        }
        return StreamSupport.stream(clowder.spliterator(), false)
            .filter(cat -> cat.weight() >= minWeight && cat.weight() < maxWeight)
            .findFirst();
    }

    /**
     * Accepts a visitor to the CatCafe.
     *
     * @param visitor the visitor to process the tree, must not be {@code null}
     * @return a string representation as a result of the traversal process
     */
    public String accept(TreeVisitor<FelineOverLord> visitor) {
        return clowder.accept(requireNonNull(visitor));
    }
}
