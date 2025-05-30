package catcafe;

import tree.Empty;
import tree.Tree;

import java.util.Optional;

/**
 * Repräsentiert ein Katzen-Café mit einem binären Baum voller Katzen.
 */
public class CatCafe {

    // 🌳 Der Binärbaum mit den Katzen
    private final Tree<FelineOverLord> clowder = new Empty<>();

    /**
     * Fügt eine Katze dem Café hinzu.
     *
     * @param cat Die hinzuzufügende Katze
     */
    public void addCat(FelineOverLord cat) {
        clowder.add(cat);
    }

    /**
     * Gibt die Anzahl der Katzen zurück.
     */
    public int getCatCount() {
        return (int) clowder.toStream().count();
    }

    /**
     * Gibt eine Katze mit exakt passendem Namen zurück.
     */
    public Optional<FelineOverLord> getCatByName(String name) {
        return clowder.toStream()
            .filter(cat -> cat.name().equals(name))
            .findFirst();
    }

    /**
     * Gibt eine Katze, deren Gewicht im Bereich liegt, zurück.
     */
    public Optional<FelineOverLord> getCatByWeight(int min, int max) {
        return clowder.getCatByWeight(min, max);
    }

    /**
     * Für Visitor-Pattern-Aufrufe (InOrderVisitor, PostOrderVisitor).
     */
    public <R> R accept(tree.TreeVisitor<FelineOverLord, R> visitor) {
        return clowder.accept(visitor);
    }
}
