package catcafe;

import tree.InOrderVisitor;
import tree.PostOrderVisitor;

/** Starter for the cat-café task. */
public class Main {
    /**
     * And go.
     *
     * @param args command line parameters, not used
     */
    public static void main(String... args) {
        CatCafe cafe = new CatCafe();

        cafe.addCat(new FelineOverLord("Miss Chief Sooky", 2));
        cafe.addCat(new FelineOverLord("Gwenapurr Esmeralda", 3));
        cafe.addCat(new FelineOverLord("Morticia", 3));
        cafe.addCat(new FelineOverLord("Fitzby Darnsworth", 5));

        System.out.println("Es schnurren " + cafe.getCatCount() + " Samtpfötchen.");

        // Suche Katzen nach Gewicht im Intervall [3,4]
        cafe.getCatByWeight(3, 7)
            .ifPresentOrElse(
                cat -> System.out.println("Gewicht [3,4]: " + cat),
                () -> System.out.println("Keine Katze im Gewichtbereich [3,4] gefunden.")
            );

        // Suche Katze mit Name "Morticia"
        cafe.getCatByName("Morticia")
            .ifPresentOrElse(
                cat -> System.out.println("Name 'Morticia': " + cat),
                () -> System.out.println("Keine Katze mit Name 'Morticia' gefunden.")
            );

        // Suche Katze mit Name "Miss Chief Sooky"
        cafe.getCatByName("Miss Chief Sooky")
            .ifPresentOrElse(
                cat -> System.out.println("Name 'Miss Chief Sooky': " + cat),
                () -> System.out.println("Keine Katze mit Name 'Miss Chief Sooky' gefunden.")
            );

        // Visitor-Ausgabe
        InOrderVisitor<FelineOverLord> inorder = new InOrderVisitor<>();
        PostOrderVisitor<FelineOverLord> postorder = new PostOrderVisitor<>();

        System.out.println("InOrder-Ausgabe:");
        System.out.println(cafe.getClowder().accept(inorder));

        System.out.println("PostOrder-Ausgabe:");
        System.out.println(cafe.getClowder().accept(postorder));
    }
}
