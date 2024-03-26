package catcafe;

import static java.util.Objects.requireNonNull;

import tree.Empty;
import tree.Tree;
import tree.TreeVisitor;

public class CatCafe {
    private Tree<FelineOverLord> clowder = new Empty<>();

    public void addCat(FelineOverLord cat) {
        clowder = clowder.addData(requireNonNull(cat));
    }

    public long getCatCount() {
        return clowder.size();
    }

    public FelineOverLord getCatByName(String name) {
        if (name == null) return null;

        for (FelineOverLord c : clowder) {
            if (c.name().equals(name)) return c;
        }

        return null;
    }

    public FelineOverLord getCatByWeight(int minWeight, int maxWeight) {
        if (minWeight < 0) return null;
        if (maxWeight < minWeight) return null;

        for (FelineOverLord c : clowder) {
            if (c.weight() >= minWeight && c.weight() < maxWeight) return c;
        }

        return null;
    }

    String accept(TreeVisitor<FelineOverLord> visitor) {
        return clowder.accept(visitor);
    }
}
