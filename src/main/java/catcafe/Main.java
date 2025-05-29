package catcafe;


import tree.InOrderVisitor;
import tree.PostOrderVisitor;

public class Main {
    public static void main(String... args) {
        CatCafe cafe = new CatCafe();

        cafe.addCat(new FelineOverLord("Miss Chief Sooky", 2));
        cafe.addCat(new FelineOverLord("Gwenapurr Esmeralda", 3));
        cafe.addCat(new FelineOverLord("Garfield", 1));
        cafe.addCat(new FelineOverLord("Morticia", 4));
        cafe.addCat(new FelineOverLord("Fitzby Darnsworth", 5));

        System.out.println("Es schnurren " + cafe.getCatCount() + " Samtpfötchen.");

        cafe.getCatByNameOptional("Morticia")
            .ifPresent(c -> System.out.println("Name: " + c.name()));

        cafe.getCatByWeight(2, 5)
            .ifPresent(c -> System.out.println("Gewicht: " + c.weight()));

        // ✨ Visitor Pattern Demo
        //Links Wurzel Rechts
        System.out.println("\n-- InOrder Traversierung --");
        System.out.println(cafe.accept(new InOrderVisitor<>()));
        // Links rechts Wurzel
        System.out.println("\n-- PostOrder Traversierung --");
        System.out.println(cafe.accept(new PostOrderVisitor<>()));
    }
}
