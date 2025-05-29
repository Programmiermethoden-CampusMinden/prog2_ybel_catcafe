package catcafe;

import java.util.Optional;
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


        try {
            Optional<FelineOverLord> meow = cafe.getCatByWeight(3, 4);
            meow.ifPresent(mew -> System.out.println("Gewicht [3,4]: " + mew));

            meow = cafe.getCatByName("Morticia");
            meow.ifPresent(mew-> System.out.println("Name 'Morticia': " + mew));

            meow = cafe.getCatByName("Miss Chief Sooky");
            meow.ifPresent(mew -> System.out.println("Name 'Miss Chief Sooky': " + mew));

        }catch(NullPointerException | IllegalArgumentException e){
            System.out.println("Fehlerhafte Eingabe: " + e.getMessage());
        }
        System.out.println("InOrder: " + cafe.accept(new InOrderVisitor<>()));
        System.out.println("PostOrder: " + cafe.accept(new PostOrderVisitor<>()));

    }
}
