package catcafe;

import static java.util.Objects.requireNonNull;

import tree.Empty;
import tree.Tree;
import tree.TreeVisitor;

import java.util.Optional;

public class CatCafe {
    private Tree<FelineOverLord> clowder = new Empty<>();

    public void addCat(FelineOverLord cat) {
        clowder = clowder.addData(requireNonNull(cat));
    }

    public long getCatCount() {
        return clowder.size();
    }

    public Optional<FelineOverLord> getCatByName(String name) {
        if (name == null) return Optional.empty();

        return clowder.stream()
            .filter(c -> c.name().equals(name))
            .findFirst();
    }

    public Optional<FelineOverLord> getCatByWeight(int minWeight, int maxWeight) {
        if (minWeight < 0 || maxWeight < minWeight) return Optional.empty();

        return clowder.stream()
            .filter(c -> c.weight() >= minWeight && c.weight() < maxWeight)
            .findFirst();
    }

    String accept(TreeVisitor<FelineOverLord> visitor) {
        return clowder.accept(visitor);
    }
}
