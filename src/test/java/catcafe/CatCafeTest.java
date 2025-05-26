package catcafe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** Testing the {@link CatCafe} class. */
public class CatCafeTest {
    private CatCafe catCafe;
    private FelineOverLord cat1;
    private FelineOverLord cat2;

    /** setup. */
    @BeforeEach
    void setup() {
        catCafe = new CatCafe();
        cat1 = new FelineOverLord("cat1", 10);
        cat2 = new FelineOverLord("cat2", 20);
    }

    /** Test to add cats. */
    @Test
    void addCat() {
        catCafe.addCat(cat1);
        catCafe.addCat(cat2);
    }

    /** this should count 0 then 2. */
    @Test
    void getCatAmount() {
        catCafe.getCatCount();
        catCafe.addCat(cat1);
        catCafe.addCat(cat2);
        catCafe.getCatCount();
    }

    /** should return the cat. */
    @Test
    void getCatByName() {
        catCafe.addCat(cat1);
        catCafe.getCatByName("cat1");
    }

    /** should give an error bc no cat was added. */
    @Test
    void getCatByNameWithNoCat() {
        catCafe.getCatByName("cat1");
    }
}
