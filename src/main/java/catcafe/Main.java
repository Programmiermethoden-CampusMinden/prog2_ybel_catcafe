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

        //getCatByWeight gibt nun Optional zurück
        cafe.getCatByWeight(3, 4).ifPresentOrElse(
            meow -> System.out.println("Gewicht [3,4]: " + meow),
            () -> System.out.println("Keine Katze mit Gewicht [3,4] gefunden")
        );

        //getCatByName gibt nun Optional zurück
        cafe.getCatByName("Morticia").ifPresentOrElse(
            meow -> System.out.println("Name 'Morticia': " + meow),
            () -> System.out.println("Keine Katze mit  Namen 'Morticia' gefunden")
        );

        //getCatByName gibt nun Optional zurück
        cafe.getCatByName("Miss Chief Sooky").ifPresentOrElse(
            meow -> System.out.println("Name 'Miss Chief Sooky': " + meow),
            () -> System.out.println("Keine Katze mit  Namen 'Miss Chief Sooky' gefunden.")
        );

        InOrderVisitor<FelineOverLord> inorder = new InOrderVisitor<>();
        String result = cafe.accept(inorder);
        System.out.println("InOrder-Ausgabe: " + result);

        // Neu: PostOrder Traversierung ebenfalls aufrufen
        PostOrderVisitor<FelineOverLord> postorder = new PostOrderVisitor<>();
        String postorderResult = cafe.accept(postorder);
        System.out.println("PostOrder-Ausgabe: " + postorderResult);
    }
}
