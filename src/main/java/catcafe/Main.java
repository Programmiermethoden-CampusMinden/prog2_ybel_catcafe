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

        System.out.println("Es schnurren " + cafe.getCatCount() + " Samtpfoetchen.");

        cafe.getCatByWeight(3,4)
            .ifPresent(cat -> System.out.println("Gewicht [3,4]: " + cat));

        cafe.getCatByName("Morticia")
            .ifPresent(cat -> System.out.println("Name 'Morticia': " + cat));

        cafe.getCatByName("Miss Chief Sooky")
            .ifPresent(cat -> System.out.println("Name 'Miss Chief Sooky': " + cat));

        // Visitor-Ausgabe
        InOrderVisitor<FelineOverLord> inorder = new InOrderVisitor<>();
        PostOrderVisitor<FelineOverLord> postorder = new PostOrderVisitor<>();

        System.out.println("InOrder-Ausgabe:");
        System.out.println(cafe.getClowder().accept(inorder));

        System.out.println("PostOrder-Ausgabe:");
        System.out.println(cafe.getClowder().accept(postorder));
    }
}
