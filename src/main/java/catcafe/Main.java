package catcafe;

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
        cafe.addCat(new FelineOverLord("potatoCat", 6));
        cafe.addCat(new FelineOverLord("whiteCat", 1));

        System.out.println("Es schnurren " + cafe.getCatCount() + " Samtpfötchen.");

        // bearbeiteung mit Optional
        // Ergebnis nur ausgeben, wenn ein passender Wert gefunden wurde (mit ifPresent)
        cafe.getCatByWeight(3, 4).ifPresent(cat -> System.out.println("Gewicht [3,4]: " + cat));
        // Optional benutzen, um Morticia auszugeben (aber nur wenn sie gefunden wurde)
        cafe.getCatByName("Morticia").ifPresent(cat -> System.out.println("Name 'Morticia': " + cat));
        // das Selbe fuer Miss Chief Sooky
        cafe.getCatByName("Miss Chief Sooky").ifPresent(cat -> System.out.println("Name 'Miss Chief Sooky': " + cat));

        // Besucher fuer den Baum
        tree.InOrderVisitor inOrderVisitor = new tree.InOrderVisitor();
        System.out.println("In Order:   " + cafe.accept(inOrderVisitor));
        tree.PostOrderVisitor postOrderVisitor = new tree.PostOrderVisitor();
        System.out.println("Post Order: " + cafe.accept(postOrderVisitor));
    }
}