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

        System.out.println("Es schnurren " + cafe.getCatCount() + " Samtpfötchen.");

        FelineOverLord meow = cafe.getCatByWeight(3, 4);
        if (meow != null) System.out.println("Gewicht [3,4]: " + meow);

        meow = cafe.getCatByName("Morticia");
        if (meow != null) System.out.println("Name 'Morticia': " + meow);

        meow = cafe.getCatByName("Miss Chief Sooky");
        if (meow != null) System.out.println("Name 'Miss Chief Sooky': " + meow);
    }
}
